package com.wisdom.iwcs.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.domain.control.CancelTaskRequestDTO;
import com.wisdom.iwcs.domain.control.GenAgvSchedulingRequestDTO;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskAction;
import com.wisdom.iwcs.domain.task.dto.MainTaskStatusEnum;
import com.wisdom.iwcs.domain.task.dto.SubTaskStatusEnum;
import com.wisdom.iwcs.domain.upstream.mes.MesCancelTaskRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskActionMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskContextMapper;
import com.wisdom.iwcs.service.callHik.callHikImpl.CancelTaskService;
import com.wisdom.iwcs.service.callHik.callHikImpl.ContinueTaskService;
import com.wisdom.iwcs.service.callHik.callHikImpl.FreeRobotService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.scheduler.WcsTaskScheduler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_HIK;
import static com.wisdom.iwcs.common.utils.TaskConstants.SendStatus.NOT_SEND;
import static com.wisdom.iwcs.common.utils.TaskConstants.SendStatus.SENDED;
import static com.wisdom.iwcs.common.utils.TaskConstants.actionStatus.RESULT_CANCEL;
import static com.wisdom.iwcs.common.utils.TaskConstants.actionStatus.RESULT_ERROR;
import static com.wisdom.iwcs.common.utils.TaskConstants.executeMode.PROMISE_ARRIVE;
import static com.wisdom.iwcs.common.utils.TaskConstants.mainTaskStatus.MAIN_FINISHED;
import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskStatus.*;
import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskType.ROLLER_CONTINUE;
import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskType.ROLLER_MOVE;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.EMPTYRECYCLETASK;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.SUPPLYANDRECYCLE;
import static com.wisdom.iwcs.common.utils.TaskConstants.workTaskStatus.END;
import static com.wisdom.iwcs.common.utils.YZConstants.LOCK;
import static com.wisdom.iwcs.domain.task.dto.MainTaskStatusEnum.Canceled;
import static com.wisdom.iwcs.domain.task.dto.MainTaskStatusEnum.Init;
import static com.wisdom.iwcs.domain.upstream.mes.MesResult.NG;
import static com.wisdom.iwcs.domain.upstream.mes.MesResult.OK;

/**
 * Mes系统请求的业务逻辑
 * @author han
 */
@Service
public class MesRequestService {
    private final Logger logger = LoggerFactory.getLogger(MesRequestService.class);
    @Autowired
    TaskContextMapper taskContextMapper;
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    MainTaskMapper mainTaskMapper;
    @Autowired
    ContinueTaskService continueTaskService;
    @Autowired
    CancelTaskService cancelTaskService;
    @Autowired
    WcsTaskScheduler wcsTaskScheduler;
     @Autowired
    FreeRobotService freeRobotService;
    @Autowired
    SubTaskActionMapper subTaskActionMapper;

    @Autowired
    BasePodDetailMapper basePodDetailMapper;
    @Autowired
    MessageService messageService;
    @Autowired
    MapResouceService mapResouceService;

    /**
     * 取消MES任务
     * @param  mesCancelTaskRequest
     * @return
     */
    public MesResult cancelMesTask(MesCancelTaskRequest mesCancelTaskRequest) {
        logger.info("开始取消主任务{}", mesCancelTaskRequest.getTaskCode());
        MesResult mesResult = cancelCheckAndDoSomething(mesCancelTaskRequest);
        logger.info("开始关闭主任务{}的线程", mesCancelTaskRequest.getTaskCode());
        if (mesResult.getData() != null && mesResult.getData() instanceof List<?>) {
            //取消第三方的任务,并释放小车
            MesResult cancelResult = cancelThirdTaskAndRobot((List<SubTask>) mesResult.getData());
            mesResult.setMessage(cancelResult.getMessage());
        } else {
            //再次确认
            List<SubTask> subTasks = subTaskMapper.selectByMainTaskNum(mesCancelTaskRequest.getTaskCode());
            List<SubTask> isusedSubTasks = subTasks.stream().filter(s -> SUB_ISSUED.equals(s.getTaskStatus())
                    && SENDED.equals(s.getSendStatus())).distinct().collect(Collectors.toList());
            if (isusedSubTasks.size() > 0) {
                MesResult cancelResult = cancelThirdTaskAndRobot(isusedSubTasks);
                mesResult.setMessage(cancelResult.getMessage());
            }

        }
        cancelTaskThreadAndUpdateDatabase(mesCancelTaskRequest);
        logger.info("主任务{}通用取消完成", mesCancelTaskRequest.getTaskCode());
        return mesResult;
    }

    /**
     * 取消任务前的校验和一些数据库修改
     * @param mesCancelTaskRequest
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public MesResult cancelCheckAndDoSomething(MesCancelTaskRequest mesCancelTaskRequest) {
        String reqCode = mesCancelTaskRequest.getReqcode();
        MesResult mesResult = new MesResult(mesCancelTaskRequest.getReqcode());
        //1.参数校验
        publicCheck(mesCancelTaskRequest.getTaskCode(), reqCode);

        //查询对应的主任务
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(mesCancelTaskRequest.getTaskCode());
        if(mainTask == null ){
            mesResult.setCode(NG);
            mesResult.setMessage(mesCancelTaskRequest.getTaskCode() + messageService.getByRequest("no_task"));
            return mesResult;
        }
        String mainTaskNum = mainTask.getMainTaskNum();
        String curTaskStatus = mainTask.getTaskStatus();
        if(TaskConstants.mainTaskStatus.MAIN_FINISHED.equals(curTaskStatus) || TaskConstants.mainTaskStatus.MAIN_CANCELED.equals(curTaskStatus) ){
            logger.info("取消失败，主任务{}处于{}状态下的任务不可取消",mainTaskNum, MainTaskStatusEnum.fromCode(curTaskStatus));
            mesResult.setCode(NG);
            mesResult.setMessage(messageService.getByRequest("cancel_fail") + SubTaskStatusEnum.fromCode(curTaskStatus)
                    + messageService.getByRequest("cancel_fail_2"));
            return mesResult;
        }else{
            List<SubTask> subTasks = subTaskMapper.selectByMainTaskNum(mainTaskNum);
            // TODO 需要区分不同执行方，目前写死，只有海康rcs
            List<SubTask> isusedSubTasks = subTasks.stream().filter(s -> SUB_ISSUED.equals(s.getTaskStatus()) && SENDED.equals(s.getSendStatus())).distinct().collect(Collectors.toList());
            List<SubTask> notStartSubTasks = subTasks.stream().filter(s -> SUB_NOT_ISSUED.equals(s.getTaskStatus())).distinct().collect(Collectors.toList());
            List<SubTask> startNoSendSubTasks = subTasks.stream().filter(s -> SUB_ISSUED.equals(s.getTaskStatus()) && NOT_SEND.equals(s.getSendStatus())).distinct().collect(Collectors.toList());
            logger.debug("筛选{}中所有进行中的子任务{}", mainTaskNum, isusedSubTasks.size());
            logger.debug("筛选{}中所有未开始的子任务{}", mainTaskNum, notStartSubTasks.size());
            logger.debug("筛选{}中所有已开始但未发送的子任务{}", mainTaskNum, startNoSendSubTasks.size());

            //如果没有搜索到Hik的任务,再进行一次确认,防止偶现的Hik任务没取消的问题
            if (isusedSubTasks.size() == 0 && subTasks.size() != 0) {
                //检查是否是Hik的请求,如果是,判断是否已发送,且是否未完成,如果已发送且未完成,则加入到取消队列里
                SubTask lastSubTask = subTasks.get(subTasks.size() - 1);
                if (SRC_HIK.equals(lastSubTask.getThirdType())) {
                    List<SubTask> listTask = subTaskMapper.selectAllByTaskCode(lastSubTask.getWorkerTaskCode());
                    //判断是否已发送,且最后一个子任务是否未完成,如果已发送且未完成,则加入到取消队列里
                    if (SENDED.equals(listTask.get(0).getSendStatus()) && !END.equals(listTask.get(listTask.size() - 1).getWorkTaskStatus())) {
                        isusedSubTasks.add(lastSubTask);
                    }
                } else {
                    //检查其他第三方请求(如MES)
                    isusedSubTasks.add(lastSubTask);
                }
            }
            logger.debug("批量更新子任务状态");

            //取消子任务和action消息发送
            isusedSubTasks.forEach(this::cancelActionAndSubTask);
            notStartSubTasks.forEach(this::cancelActionAndSubTask);
            startNoSendSubTasks.forEach(this::cancelActionAndSubTask);
            if (isusedSubTasks.size() > 0) {
                mesResult.setData(isusedSubTasks);
            }
        }
        if (OK.equals(mesResult.getCode())) {
            mesResult.setMessage("任务" + mesCancelTaskRequest.getTaskCode() + "取消成功");
        }
        return mesResult;
    }

    /**
     * 取消第三方的任务,并释放小车
     * @param isusedSubTasks
     */
    private MesResult cancelThirdTaskAndRobot(List<SubTask> isusedSubTasks) {
        MesResult mesResult = new MesResult();
        List<String> isusedWorkTaskCodes = isusedSubTasks.stream().filter(t -> SRC_HIK.equals(t.getThirdType())).map(t -> t.getWorkerTaskCode()).distinct().collect(Collectors.toList());
        List<String> isusedRobotsCodes = isusedSubTasks.stream().filter(t -> SRC_HIK.equals(t.getThirdType())).map(t -> t.getRobotCode()).distinct().collect(Collectors.toList());
        logger.debug("筛选所有进行中的子任务{}",isusedWorkTaskCodes.size());
        logger.debug("循环调用执行者接口，取消任务");
        isusedWorkTaskCodes.stream().forEach(workTaskCode->{
            CancelTaskRequestDTO cancelTaskRequestDTO = new CancelTaskRequestDTO();
            cancelTaskRequestDTO.setTaskCode(workTaskCode);
            logger.info("调用取消任务接口,海康任务编号：{}",workTaskCode);
            Result result = new Result();
            try {
                result = cancelTaskService.cancelTask(cancelTaskRequestDTO);
            } catch (BusinessException e) {
                logger.error("取消海康失败，{}, 错误信息:{}",workTaskCode, e.getMsg());
                mesResult.setMessage("WCS任务取消成功,Hik任务取消异常:" + e.getMsg());
                //异常处理
                result = cancelErrorHandler(e.getMsg(), cancelTaskRequestDTO);
            }
            logger.info("取消海康任务结果：{}", JSON.toJSONString(result));
            if(200 == result.getReturnCode()){
                logger.info("取消海康成功，{}",workTaskCode);
            }else{
                logger.info("取消海康失败，{},回滚数据库",workTaskCode);
            }
        });
        logger.info("尝试取消子任务{}执行器线程",isusedSubTasks);

        //释放小车
        freeRobot(isusedRobotsCodes);
        return mesResult;
    }

    /**
     * 第三方取消任务异常处理方法
     * @param errorMsg
     * @param cancelTaskRequestDTO
     */
    public Result cancelErrorHandler(String errorMsg, CancelTaskRequestDTO cancelTaskRequestDTO) {
        Result result = new Result();
        for (int i = 0; i < 5; i++) {
            if (errorMsg.contains("UDP连接失败")) {
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                result = cancelTaskService.cancelTask(cancelTaskRequestDTO);
                errorMsg = result.getReturnMsg();
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * 取消action消息发送,和子任务
     * @param subTask
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancelActionAndSubTask(SubTask subTask) {
        //取消子任务
        SubTask subTaskTmp = new SubTask();
        subTaskTmp.setId(subTask.getId());
        subTaskTmp.setCancelOperator(SecurityUtils.getCurrentUserId());
        subTaskTmp.setCancelTime(new Date());
        subTaskTmp.setCancelSceneRecoveryStatus(TaskConstants.CancelSceneRecoveryStatus.PENDING);
        subTaskTmp.setTaskStatus(SUB_CANCELED);
        subTaskMapper.updateByPrimaryKeySelective(subTaskTmp);

        //取消action消息发送
        Map<String, String> map = new HashMap<>(5);
        map.put("subTaskNum", subTask.getSubTaskNum());
        map.put("actionStatus", RESULT_ERROR);
        map.put("executeMode", PROMISE_ARRIVE);
        List<SubTaskAction> subTaskActions = subTaskActionMapper.selectPage(map);
        if(subTaskActions != null && subTaskActions.size() > 0) {
            SubTaskAction tmpAction = new SubTaskAction();
            for (SubTaskAction subTaskAction : subTaskActions) {
                tmpAction.setId(subTaskAction.getId());
                tmpAction.setActionStatus(RESULT_CANCEL);
                subTaskActionMapper.updateByPrimaryKeySelective(tmpAction);
            }
        }
    }

    /**
     * 释放小车
     */
    public void freeRobot(List<String> isusedRobotsCodes) {
        logger.info("尝试取消子任务占用的robot{}",isusedRobotsCodes);
        if (isusedRobotsCodes == null || isusedRobotsCodes.size() == 0) {
            logger.info("小车号为空,跳过释放小车");
            return;
        }
        try {
            isusedRobotsCodes.stream().forEach(robot -> {
                GenAgvSchedulingRequestDTO genAgvSchedulingRequestDTO = new GenAgvSchedulingRequestDTO();
                genAgvSchedulingRequestDTO.setAgvCode(robot);
                genAgvSchedulingRequestDTO.setRobotCode(robot);
                freeRobotService.freeRobot(genAgvSchedulingRequestDTO);
            });
        } catch (Exception e) {
            logger.warn("取消任务时释放小车异常{}", e);
        }
    }

    /**
     * 关闭任务线程,并更新数据库
     */
    @Transactional(rollbackFor = Exception.class)
    public void cancelTaskThreadAndUpdateDatabase(MesCancelTaskRequest mesCancelTaskRequest) {
        //查询对应的主任务
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(mesCancelTaskRequest.getTaskCode());
        //更新主任务单号
        MainTask mainTaskTmp = new MainTask();
        mainTaskTmp.setId(mainTask.getId());
        mainTaskTmp.setTaskStatus(TaskConstants.mainTaskStatus.MAIN_CANCELED);
        mainTaskTmp.setCancelOperator(SecurityUtils.getCurrentUserId());
        mainTaskTmp.setCancelTime(new Date());
        mainTaskTmp.setCancelSceneRecoveryStatus(TaskConstants.CancelSceneRecoveryStatus.PENDING);
        mainTaskMapper.updateByPrimaryKeySelective(mainTaskTmp);
        //暂停系统线程
        wcsTaskScheduler.stopMainTaskThread(mesCancelTaskRequest.getTaskCode());
    }

    /**
     * 顶升式通用的取消任务
     * @param  mesCancelTaskRequest
     * @return
     */
    public MesResult publicCancelTask(MesCancelTaskRequest mesCancelTaskRequest) {
        //取消WCS和HIK任务
        MesResult mesResult = cancelMesTask(mesCancelTaskRequest);
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(mesCancelTaskRequest.getTaskCode());
        //如果是滚筒任务
        if (SUPPLYANDRECYCLE.equals(mainTask.getMainTaskTypeCode()) || EMPTYRECYCLETASK.equals(mainTask.getMainTaskTypeCode())) {
            return mesResult;
        }
        return publicCancelTask(mesCancelTaskRequest, mesResult);
    }

    /**
     * 顶升式取消任务数据处理
     * @param mesCancelTaskRequest
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public MesResult publicCancelTask(MesCancelTaskRequest mesCancelTaskRequest, MesResult mesResult) {
        // TODO 需要区分不同执行方，目前写死，只有海康rcs
        List<SubTask> subTasks = subTaskMapper.selectByMainTaskNum(mesCancelTaskRequest.getTaskCode());
        List<SubTask> isusedSubTasks = subTasks.stream().filter(s -> SUB_CANCELED.equals(s.getTaskStatus())).distinct().collect(Collectors.toList());
        logger.debug("筛选{}中所有已取消的子任务{}", mesCancelTaskRequest.getTaskCode(), isusedSubTasks.size());

        //如果没有搜索到Hik的任务,再进行一次确认,防止偶现的Hik任务没取消的问题
        if (isusedSubTasks.size() == 0 && subTasks.size() != 0) {
            //检查是否是Hik的请求,如果是,判断是否已发送,且是否未完成,如果已发送且未完成,则加入到取消队列里
            SubTask lastSubTask = subTasks.get(subTasks.size() - 1);
            if (SRC_HIK.equals(lastSubTask.getThirdType())) {
                List<SubTask> listTask = subTaskMapper.selectAllByTaskCode(lastSubTask.getWorkerTaskCode());
                //判断是否已发送,且最后一个子任务是否未完成,如果已发送且未完成,则加入到取消队列里
                if (SENDED.equals(listTask.get(0).getSendStatus()) && !END.equals(listTask.get(listTask.size() - 1).getWorkTaskStatus())) {
                    isusedSubTasks.add(lastSubTask);
                }
            }
        }
        if (isusedSubTasks.size() <= 0) {
            //主任务未开始执行的情况
            MainTask mainTask = mainTaskMapper.selectByMainTaskNum(mesCancelTaskRequest.getTaskCode());
            String staticPodCode = mainTask.getStaticPodCode();
            String staticViaPaths = mainTask.getStaticViaPaths();
            //解锁地码
            if (StringUtils.isNotEmpty(staticViaPaths)) {
                List<String> berCodes = JSONArray.parseArray(staticViaPaths, String.class);
                if (berCodes != null && berCodes.size() > 0) {
                    for (String berCode : berCodes) {
                        checkAndUnlockBerCode(berCode, mainTask.getMainTaskNum());
                    }
                }
            }
            //解锁货架
            if (StringUtils.isNotEmpty(staticPodCode)) {
                BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(staticPodCode);
                if (basePodDetail != null && LOCK.equals(basePodDetail.getInLock()) && mainTask.getMainTaskNum().equals(basePodDetail.getLockSource())) {
                    mapResouceService.unlockPodByCode(staticPodCode);
                }
            }
            return mesResult;
        }

        List<String> needBindingPods = new ArrayList<>();
        for (SubTask isusedSubTask : isusedSubTasks) {
            logger.info("任务取消开始处理子任务{}", isusedSubTask.getSubTaskNum());
            //恢复货架,解锁终点
            //解锁终点: 检查终点是否被锁定,且是这个任务锁定的
            BaseMapBerth endMapBerth = baseMapBerthMapper.selectOneByBercode(isusedSubTask.getEndBercode());
            //如果点位存在,且被锁定,且锁定源是这个子任务
            if (endMapBerth != null && LOCK.equals(endMapBerth.getInLock())
                    && isusedSubTask.getWorkerTaskCode().equals(endMapBerth.getLockSource())) {
                LockStorageDto lockStorageDto = new LockStorageDto();
                lockStorageDto.setBerCode(endMapBerth.getBerCode());
                lockStorageDto.setMapCode(endMapBerth.getBerCode().substring(6,8));
                Result result = mapResouceService.unlockMapBerth(lockStorageDto);
                logger.info("子任务{}的终点{}解锁结果:{}", isusedSubTask.getSubTaskNum(), isusedSubTask.getEndBercode(), result.getReturnMsg());
            }
            if (endMapBerth != null && endMapBerth.getPodCode() != null
                    && endMapBerth.getPodCode().equals(isusedSubTask.getPodCode())) {
                //如果货架已到终点,则不进行恢复操作,仅进行货架解锁
                mapResouceService.unlockPodByCode(isusedSubTask.getPodCode());
                return mesResult;
            }
            //解锁货架和恢复货架
            BaseMapBerth startMapBerth = baseMapBerthMapper.selectOneByBercode(isusedSubTask.getStartBercode());
            if (startMapBerth != null) {
                //如果起点上的货架已经不是子任务的货架了,则需要开始恢复货架
                if (StringUtils.isEmpty(startMapBerth.getPodCode())
                        || !startMapBerth.getPodCode().equals(isusedSubTask.getPodCode())) {
                    int row = basePodDetailMapper.updateCleanPodLocation(isusedSubTask.getPodCode());
                    if (row > 0) {
                        needBindingPods.add(isusedSubTask.getPodCode());
                        logger.info("{}子任务的{}货架清空完成", isusedSubTask.getSubTaskNum(), isusedSubTask.getPodCode());
                    }
                } else if (StringUtils.isNotEmpty(startMapBerth.getPodCode())
                        && startMapBerth.getPodCode().equals(isusedSubTask.getPodCode())) {
                    mapResouceService.unlockPodByCode(isusedSubTask.getPodCode());
                }
            }
        }
        if (needBindingPods.size() > 0) {
            mesResult.setMessage(mesResult.getMessage() + "     " + JSONArray.toJSON(needBindingPods) + "货架需人工绑定");
            mesResult.setData(needBindingPods);
        }
        return mesResult;
    }

    /**
     * 检查并解锁地码
     * @param berCode
     * @param lockSource
     */
    @Transactional(rollbackFor = Exception.class)
    public void checkAndUnlockBerCode(String berCode, String lockSource) {
        BaseMapBerth endMapBerth = baseMapBerthMapper.selectOneByBercode(berCode);
        //如果点位存在,且被锁定,且锁定源是这个子任务
        if (endMapBerth != null && LOCK.equals(endMapBerth.getInLock())
                && lockSource.equals(endMapBerth.getLockSource())) {
            LockStorageDto lockStorageDto = new LockStorageDto();
            lockStorageDto.setBerCode(endMapBerth.getBerCode());
            lockStorageDto.setMapCode(endMapBerth.getBerCode().substring(6,8));
            Result result = mapResouceService.unlockMapBerth(lockStorageDto);
            logger.info("任务{}的终点{}解锁结果:{}", lockSource, berCode, result.getReturnMsg());
        }
    }



    /**
     * 除创建任务之外的其他接口的校验
     * @param mainTaskNum
     */
    public void publicCheck(String mainTaskNum, String reqCode) {
        if (StringUtils.isBlank(mainTaskNum)) {
            throw new MesBusinessException(reqCode, "任务号不能为空");
        }
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(mainTaskNum);
        if (mainTask == null) {
            throw new MesBusinessException(reqCode, mainTaskNum + "任务不存在");
        }
        if (MAIN_FINISHED.equals(mainTask.getTaskStatus())) {
            throw new MesBusinessException(reqCode, mainTaskNum + "任务已结束");
        }
    }



    /**
     * Mes 滚筒换车任务
     * @param mesCancelTaskRequest
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public MesResult changeAgv(MesCancelTaskRequest mesCancelTaskRequest) {
        String robotCode = mesCancelTaskRequest.getRobotCode();
        Preconditions.checkMesBusinessError(StringUtils.isEmpty(robotCode), messageService.getByRequest("change_robot_error"));

        //1.校验任务是否可以换车
        String mainTaskNum = mesCancelTaskRequest.getTaskCode();
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(mainTaskNum);
        Preconditions.checkMesBusinessError(mainTask == null, messageService.getByRequest("no_task"));
        //只有已取消的任务才可以换车
        if (!Canceled.getStatusCode().equals(mainTask.getTaskStatus())) {
            throw new MesBusinessException(messageService.getByRequest("change_robot_error_2"));
        }

        //2.获取最后一组滚筒任务,判断节点是移动任务还是滚动任务
        List<SubTask> subTaskList = subTaskMapper.selectByMainTaskNum(mainTaskNum);
        Preconditions.checkMesBusinessError(subTaskList.size() <= 0, messageService.getByRequest("change_robot_error_3"));
        SubTask lastSubTask = subTaskList.get(subTaskList.size() - 1);
        List<Long> changeIds = new ArrayList<>();

        if (ROLLER_CONTINUE.equals(lastSubTask.getSubTaskTyp())) {
            //最后一个任务是滚筒AGV滚动任务
            changeIds.add(subTaskList.get(subTaskList.size() - 2).getId());
            changeIds.add(lastSubTask.getId());
        } else if (ROLLER_MOVE.equals(lastSubTask.getSubTaskTyp())) {
            //最后一个任务是滚筒AGV移动任务
            changeIds.add(lastSubTask.getId());
        } else {
            throw new MesBusinessException(messageService.getByRequest("change_robot_error_4"));
        }
        //新的第三方任务号
        String newWorkTaskNum = CodeBuilder.codeBuilder("S");

        //3.更新最后一组滚筒任务状态:任务状态,下发状态,第三方任务号,小车号
        subTaskMapper.updateInitById(changeIds, newWorkTaskNum, robotCode);

        //4.更新主任务状态
        MainTask tmpMainTask = new MainTask();
        tmpMainTask.setId(mainTask.getId());
        tmpMainTask.setTaskStatus(Init.getStatusCode());
        mainTaskMapper.updateByPrimaryKeySelective(tmpMainTask);

        return new MesResult();
    }
}
