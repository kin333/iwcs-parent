package com.wisdom.iwcs.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.common.utils.taskUtils.TaskContextUtils;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.domain.control.CancelTaskRequestDTO;
import com.wisdom.iwcs.domain.control.GenAgvSchedulingRequestDTO;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskAction;
import com.wisdom.iwcs.domain.task.TaskContext;
import com.wisdom.iwcs.domain.task.dto.ContextDTO;
import com.wisdom.iwcs.domain.task.dto.MainTaskStatusEnum;
import com.wisdom.iwcs.domain.task.dto.SubTaskStatusEnum;
import com.wisdom.iwcs.domain.upstream.mes.*;
import com.wisdom.iwcs.domain.upstream.mes.chaoyue.ModifyPodStatus;
import com.wisdom.iwcs.domain.upstream.mes.chaoyue.ReportEmptyContainerNumber;
import com.wisdom.iwcs.domain.upstream.mes.chaoyue.StartSupllyAndRecyles;
import com.wisdom.iwcs.domain.upstream.mes.chaoyue.SupllyUnload;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskActionMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskContextMapper;
import com.wisdom.iwcs.service.callHik.IContinueTaskService;
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

import static com.wisdom.iwcs.common.utils.InspurBizConstants.SupllyNodeType.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_HIK;
import static com.wisdom.iwcs.common.utils.TaskConstants.SendStatus.SENDED;
import static com.wisdom.iwcs.common.utils.TaskConstants.actionStatus.RESULT_CANCEL;
import static com.wisdom.iwcs.common.utils.TaskConstants.actionStatus.RESULT_ERROR;
import static com.wisdom.iwcs.common.utils.TaskConstants.bizProcess.*;
import static com.wisdom.iwcs.common.utils.TaskConstants.executeMode.PROMISE_ARRIVE;
import static com.wisdom.iwcs.common.utils.TaskConstants.mainTaskStatus.MAIN_FINISHED;
import static com.wisdom.iwcs.common.utils.TaskConstants.notifyAgvLeaveStatus.*;
import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskStatus.SUB_ISSUED;
import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskType.ROLLER_CONTINUE;
import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskType.ROLLER_MOVE;
import static com.wisdom.iwcs.common.utils.TaskConstants.workTaskStatus.END;
import static com.wisdom.iwcs.common.utils.YZConstants.LOCK;
import static com.wisdom.iwcs.domain.task.dto.MainTaskStatusEnum.Canceled;
import static com.wisdom.iwcs.domain.task.dto.MainTaskStatusEnum.Init;

/**
 * Mes系统请求的业务逻辑
 * @author han
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MesRequestService {
    private final Logger logger = LoggerFactory.getLogger(MesRequestService.class);
    @Autowired
    TaskContextMapper taskContextMapper;
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private IContinueTaskService iContinueTaskService;
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
     * 接收 Mes 通知 AGV 接料点目的地的请求
     * @param supplyInfoNotify
     * @return
     */
    public MesResult supplyUnloadWbNotify(SupplyInfoNotify supplyInfoNotify, String reqCode) {
        logger.info("接收到Mes通知AGV接料点目的地的请求,请求体:{}", supplyInfoNotify);
        String taskCode = supplyInfoNotify.getTaskCode();
        String unLoadWbFirst = supplyInfoNotify.getSupplyUnLoadWbFirst();
        String unLoadWbSecond = supplyInfoNotify.getSupplyUnLoadWbSecond();
        Integer loadWbFirstCount = supplyInfoNotify.getSupplyUnLoadWbFirstCount();
        Integer loadWbSecondCount = supplyInfoNotify.getSupplyUnLoadWbSecondCount();
        //1.参数校验
        publicCheck(taskCode, reqCode);
        if (StringUtils.isBlank(unLoadWbFirst)) {
            throw new MesBusinessException(reqCode, "第一个接料点不能为空");
        }
        Integer firstCount = loadWbFirstCount;
        countCheck(firstCount, reqCode);
        Integer secondCount = loadWbSecondCount;
        Integer totalNum = firstCount;
        if (secondCount != null) {
            countCheckCanZero(secondCount, reqCode);
            totalNum = firstCount + secondCount;
            countCheck(totalNum, reqCode);
        }

        //将点位信息转换为berCode
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(unLoadWbFirst);
        Preconditions.checkMesBusinessError(baseMapBerth == null, unLoadWbFirst + "找不到别名对应的地图编码", reqCode);
        supplyInfoNotify.setSupplyUnLoadWbFirst(baseMapBerth.getBerCode());

        if (StringUtils.isNotBlank(unLoadWbSecond)) {
            baseMapBerth = baseMapBerthMapper.selectByPointAlias(unLoadWbSecond);
            Preconditions.checkMesBusinessError(baseMapBerth == null, unLoadWbSecond + "找不到别名对应的地图编码", reqCode);
            supplyInfoNotify.setSupplyUnLoadWbSecond(baseMapBerth.getBerCode());
        }

        //2.获取原context
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(taskCode);
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(taskContext.getContext(), ContextDTO.class);

        if (!contextDTO.getSupplyLoadNum().equals(totalNum)) {
            throw new MesBusinessException(reqCode, "上下料数量不一致");
        }

        //3.更新context的json字符串
        contextDTO.setSupplyUnLoadWbFirst(supplyInfoNotify.getSupplyUnLoadWbFirst());
        contextDTO.setSupplyUnLoadWbFirstCount(supplyInfoNotify.getSupplyUnLoadWbFirstCount());
        if (StringUtils.isNotEmpty(supplyInfoNotify.getSupplyUnLoadWbSecond())) {
            contextDTO.setSupplyUnLoadWbSecond(supplyInfoNotify.getSupplyUnLoadWbSecond());
        }
        Integer wbSecondCount = supplyInfoNotify.getSupplyUnLoadWbSecondCount();
        if (wbSecondCount != null && wbSecondCount != 0) {
            contextDTO.setSupplyUnLoadWbSecondCount(wbSecondCount);
        }
        String jsonStr = TaskContextUtils.objectToJson(contextDTO);
        taskContextMapper.updateByPrimaryKeySelective(new TaskContext(taskContext.getId(), jsonStr));

        logger.info("Mes通知AGV接料点目的地的请求处理结束,任务编号:{}", taskCode);
        return new MesResult();
    }

    /**
     * 接收 Mes 接料点通知供料及回收空框信息的请求
     * @param startSupllyAndRecyle
     * @return
     */
    public MesResult startSupllyAndRecyle(StartSupllyAndRecyle startSupllyAndRecyle, String reqCode) {
        logger.info("接收到Mes接料点通知供料及回收空框信息的请求,请求体:{}", startSupllyAndRecyle);
        String taskCode = startSupllyAndRecyle.getTaskCode();
        Integer emptyRecyleNum = startSupllyAndRecyle.getEmptyRecyleNum();
        Integer supplyUnLoadNum = startSupllyAndRecyle.getSupplyUnLoadNum();
        String emptyRecyleWb = startSupllyAndRecyle.getEmptyRecyleWb();
        //1.参数校验
        publicCheck(taskCode, reqCode);
        //校验回收数量
        if (emptyRecyleNum != null) {
            countCheckCanZero(emptyRecyleNum, reqCode);
        }


        //校验送料点和送料数量

        //将点位信息转换为berCode
        if (StringUtils.isNotBlank(emptyRecyleWb)) {
            BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(emptyRecyleWb);
            Preconditions.checkMesBusinessError(baseMapBerth == null,
                    emptyRecyleWb + "找不到别名对应的地图编码", reqCode);
            startSupllyAndRecyle.setEmptyRecyleWb(baseMapBerth.getBerCode());
        } else if (emptyRecyleNum != null && emptyRecyleNum > 0){
            //此时回收数量不为0,回收点不能为空
            throw new MesBusinessException(reqCode, "回收点为空,但是回收数量不为0,请指定回收点");
        }

        //将点位信息转换为berCode
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(startSupllyAndRecyle.getSupplyLoadWb());
        Preconditions.checkMesBusinessError(baseMapBerth == null, startSupllyAndRecyle.getSupplyLoadWb() + "找不到别名对应的地图编码", reqCode);
        startSupllyAndRecyle.setSupplyLoadWb(baseMapBerth.getBerCode());


        //2.获取原context
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(taskCode);
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(taskContext.getContext(), ContextDTO.class);
        //校验下箱数量
        if (supplyUnLoadNum != null) {
            if (contextDTO.getSupplyUnLoadWbFirst() == null) {
                throw new MesBusinessException(reqCode, "未通知第一下料点");
            }
            if (contextDTO.getSupplyUnLoadWbFirst().equals(startSupllyAndRecyle.getSupplyLoadWb())) {
                //第一下料点请求
                if (!supplyUnLoadNum.equals(contextDTO.getSupplyUnLoadWbFirstCount())) {
                    throw new MesBusinessException(reqCode, "下料点一的下箱数量与第一次通知不匹配");
                }
            } else {
                //第二下料点请求
                if (!supplyUnLoadNum.equals(contextDTO.getSupplyUnLoadWbSecondCount())) {
                    throw new MesBusinessException(reqCode, "下料点二的下箱数量与第一次通知不匹配");
                }
            }
        }
        //校验下箱地址
        if (contextDTO.getEndBerCodeReady() == null) {
            //第一下箱点的请求校验
            if (!startSupllyAndRecyle.getSupplyLoadWb().equals(contextDTO.getSupplyUnLoadWbFirst())) {
                throw new MesBusinessException(reqCode, "下料点一位置错误");
            }
        } else {
            //第二下箱点的请求校验
            if (!startSupllyAndRecyle.getSupplyLoadWb().equals(contextDTO.getSupplyUnLoadWbSecond())) {
                throw new MesBusinessException(reqCode, "下料点二位置错误");
            }
        }
        //校验允许下箱请求是否已发送
        if (contextDTO.getEmptyRecyleNumTwo() != null) {
            throw new MesBusinessException(reqCode, "已通知AGV下料,请勿重复提交");
        }

        //3.更新context的json字符串
        if (StringUtils.isBlank(emptyRecyleWb) && emptyRecyleNum == null) {
            //如果没有传回收点
            if (contextDTO.getEndBerCodeReady() == null) {
                contextDTO.setEndBerCodeReady(true);
            } else {
                contextDTO.setEndBerCodeTwoReady(true);
            }
        } else {
            //如果有传回收点,更新回收点点位和数量
            if (StringUtils.isNotBlank(emptyRecyleWb)) {
                contextDTO.setEmptyRecyleWb(startSupllyAndRecyle.getEmptyRecyleWb());
            }
            if (contextDTO.getEmptyRecyleNum() == null) {
                contextDTO.setEmptyRecyleNum(emptyRecyleNum);
            } else {
                contextDTO.setEmptyRecyleNum(contextDTO.getEmptyRecyleNum() + emptyRecyleNum);
            }
            //更新MES的滚筒状态
            if (contextDTO.getEndBerCodeReady() == null) {
                contextDTO.setEndBerCodeReady(true);
                contextDTO.setEmptyRecyleNumOne(emptyRecyleNum);
            } else {
                contextDTO.setEndBerCodeTwoReady(true);
                contextDTO.setEmptyRecyleNumTwo(emptyRecyleNum);
            }
        }

        if (contextDTO.getEmptyRecyleNum() != null && contextDTO.getEmptyRecyleNum() > 2) {
            throw new MesBusinessException(reqCode, "回收空料箱的总数量不能超过2");
        }
        String jsonStr = TaskContextUtils.objectToJson(contextDTO);
        taskContextMapper.updateByPrimaryKeySelective(new TaskContext(taskContext.getId(), jsonStr));

        logger.info("Mes接料点通知供料及回收空框信息的请求处理结束,任务编号:{}", taskCode);
        return new MesResult();
    }

    /**
     * 接收 Mes 通知可出空料框的请求
     * @param startRecyle
     * @return
     */
    public MesResult startRecyle(StartRecyle startRecyle, String reqCode) {
        logger.info("接收到Mes通知可出空料框的请求,请求体:{}", startRecyle);
        String taskCode = startRecyle.getTaskCode();
        Integer recyleCount = startRecyle.getRecyleCount();
        String emptyRecyleWb = startRecyle.getEmptyRecyleWb();
        //1.参数校验
        publicCheck(taskCode, reqCode);

        //将点位信息转换为berCode
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(emptyRecyleWb);
        Preconditions.checkMesBusinessError(baseMapBerth == null, emptyRecyleWb + "找不到别名对应的地图编码", reqCode);
        startRecyle.setEmptyRecyleWb(baseMapBerth.getBerCode());

        //2.获取原context
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(taskCode);
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(taskContext.getContext(), ContextDTO.class);
        //校验回收点和回收数量是否匹配
        if (recyleCount != null) {
            countCheck(recyleCount, reqCode);
            if (!recyleCount.equals(contextDTO.getEmptyRecyleNum())) {
                throw new MesBusinessException(reqCode, "空料框上箱数量和下箱数量不匹配");
            }
        }
        if (emptyRecyleWb != null && !startRecyle.getEmptyRecyleWb().equals(contextDTO.getEmptyRecyleWb())) {
            throw new MesBusinessException(reqCode, "回收点与上空箱时的传参不匹配");
        }


        //3.修改标记位
        contextDTO.setEmptyRecycleReady(true);
        String jsonStr = TaskContextUtils.objectToJson(contextDTO);
        taskContextMapper.updateByPrimaryKeySelective(new TaskContext(taskContext.getId(), jsonStr));

        logger.info("Mes通知可出空料框的请求处理结束,任务编号:{}", taskCode);
        return new MesResult();
    }

    /**
     * 通知Agv可从等待点前往终点
     * @param  conWaitToDestWbRequest
     * @return 
     */
    public MesResult conWaitToDestWb(ConWaitToDestWbRequest conWaitToDestWbRequest, String reqCode) {
        //1.参数校验
        publicCheck(conWaitToDestWbRequest.getTaskCode(), reqCode);

        //查询对应的主任务
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(conWaitToDestWbRequest.getTaskCode());
        MainTask tmpMainTask = new MainTask();
        tmpMainTask.setId(mainTask.getId());
        //判断任务状态
        if (TaskConstants.doorStatus.OPEN.equals(conWaitToDestWbRequest.getDoorStatus())) {
            if (ENTER_ARRIVED_OUT.equals(mainTask.getBizProcess())) {
                tmpMainTask.setBizProcess(ENTER_ALLOW_LEAVE_OUT_WAIT);
            } else if (COME_ARRIVED_IN.equals(mainTask.getBizProcess())) {
                tmpMainTask.setBizProcess(COME_ALLOW_LEAVE_IN_WAIT);
            } else {
                throw new MesBusinessException("任务节点未在等待开门状态", reqCode);
            }
        } else if (TaskConstants.doorStatus.CLOSE.equals(conWaitToDestWbRequest.getDoorStatus())) {
            if (ENTER_ARRIVED_IN.equals(mainTask.getBizProcess())) {
                tmpMainTask.setBizProcess(ENTER_ALLOW_LEAVE_IN_WAIT);
            } else if (COME_ARRIVED_OUT.equals(mainTask.getBizProcess())) {
                tmpMainTask.setBizProcess(COME_ALLOW_LEAVE_OUT_WAIT);
            } else {
                throw new MesBusinessException("任务节点未在等待关门状态", reqCode);
            }
        } else {
            throw new MesBusinessException("开关门标记位错误:" + conWaitToDestWbRequest.getDoorStatus(), reqCode);
        }

        mainTaskMapper.updateByPrimaryKeySelective(tmpMainTask);
        return new MesResult(reqCode);
    }
    /**
     * 取消MES任务
     * @param  mesCancelTaskRequest
     * @return
     */
    public MesResult cancelMesTask(MesCancelTaskRequest mesCancelTaskRequest) {
        String reqCode = mesCancelTaskRequest.getReqcode();
        MesResult mesResult = new MesResult(mesCancelTaskRequest.getReqcode());
        //1.参数校验
        publicCheck(mesCancelTaskRequest.getTaskCode(), reqCode);

        //查询对应的主任务
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(mesCancelTaskRequest.getTaskCode());
        if(mainTask == null ){
            mesResult.setCode(MesResult.NG);
            mesResult.setMessage(mesCancelTaskRequest.getTaskCode() + messageService.getByRequest("no_task"));
            return mesResult;
        }
        String mainTaskNum = mainTask.getMainTaskNum();
        String curTaskStatus = mainTask.getTaskStatus();
        if(TaskConstants.mainTaskStatus.MAIN_FINISHED.equals(curTaskStatus) || TaskConstants.mainTaskStatus.MAIN_CANCELED.equals(curTaskStatus) ){
            logger.info("取消失败，主任务{}处于{}状态下的任务不可取消",mainTaskNum, MainTaskStatusEnum.fromCode(curTaskStatus));
            mesResult.setCode(MesResult.NG);
            mesResult.setMessage(messageService.getByRequest("cancel_fail") + SubTaskStatusEnum.fromCode(curTaskStatus)
                    + messageService.getByRequest("cancel_fail_2"));
            return mesResult;
        }else{
            //更新主任务单号
            MainTask mainTaskTmp = new MainTask();
            mainTaskTmp.setId(mainTask.getId());
            mainTaskTmp.setTaskStatus(TaskConstants.mainTaskStatus.MAIN_CANCELED);
            mainTaskTmp.setCancelOperator(SecurityUtils.getCurrentUserId());
            mainTaskTmp.setCancelTime(new Date());
            mainTaskTmp.setCancelSceneRecoveryStatus(TaskConstants.CancelSceneRecoveryStatus.PENDING);
            mainTaskMapper.updateByPrimaryKeySelective(mainTaskTmp);

            List<SubTask> subTasks = subTaskMapper.selectByMainTaskNum(mainTaskNum);
            // TODO 需要区分不同执行方，目前写死，只有海康rcs
            List<SubTask> isusedSubTasks = subTasks.stream().filter(s -> SUB_ISSUED.equals(s.getTaskStatus()) && SENDED.equals(s.getSendStatus())).distinct().collect(Collectors.toList());
            logger.debug("筛选{}中所有进行中的子任务{}", mainTaskNum, isusedSubTasks);

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

            List<String> isusedWorkTaskCodes = isusedSubTasks.stream().filter(t -> SRC_HIK.equals(t.getThirdType())).map(t -> t.getWorkerTaskCode()).distinct().collect(Collectors.toList());
            List<String> isusedRobotsCodes = isusedSubTasks.stream().filter(t -> SRC_HIK.equals(t.getThirdType())).map(t -> t.getRobotCode()).distinct().collect(Collectors.toList());
            logger.debug("筛选所有进行中的子任务workercod ",isusedWorkTaskCodes);

            logger.debug("批量更新子任务状态");

            isusedSubTasks.stream().forEach(t->{
                SubTask subTaskTmp = new SubTask();
                subTaskTmp.setId(t.getId());
                subTaskTmp.setCancelOperator(SecurityUtils.getCurrentUserId());
                subTaskTmp.setCancelTime(new Date());
                subTaskTmp.setCancelSceneRecoveryStatus(TaskConstants.CancelSceneRecoveryStatus.PENDING);
                subTaskTmp.setTaskStatus(TaskConstants.subTaskStatus.SUB_CANCELED);
                subTaskMapper.updateByPrimaryKeySelective(subTaskTmp);

                //取消action消息发送
                Map<String, String> map = new HashMap<>(5);
                map.put("subTaskNum", t.getSubTaskNum());
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
            });

            logger.debug("循环调用执行者接口，取消任务");
            isusedWorkTaskCodes.stream().forEach(workTaskCode->{
                CancelTaskRequestDTO cancelTaskRequestDTO = new CancelTaskRequestDTO();
                cancelTaskRequestDTO.setTaskCode(workTaskCode);
                logger.info("调用取消任务接口,海康任务编号：{}",workTaskCode);
                Result result = cancelTaskService.cancelTask(cancelTaskRequestDTO);
                logger.info("取消海康任务结果：{}", JSON.toJSONString(result));
                if(200 == result.getReturnCode()){
                    logger.info("取消海康成功，{}",workTaskCode);
                }else{
                    logger.info("取消海康失败，{},回滚数据库",workTaskCode);
                    throw  new BusinessException(messageService.getByRequest("cancel_fail_3"));
                }
            });
            logger.info("尝试取消子任务{}执行器线程",isusedSubTasks);
            isusedSubTasks.stream().forEach(subTask->{
                wcsTaskScheduler.stopMainTaskThread(subTask.getMainTaskNum(),subTask.getSubTaskNum());
            });

            //释放小车
            logger.info("尝试取消子任务占用的robot",isusedRobotsCodes);
            try {
                isusedRobotsCodes.stream().forEach(robot -> {
                    GenAgvSchedulingRequestDTO genAgvSchedulingRequestDTO = new GenAgvSchedulingRequestDTO();
                    genAgvSchedulingRequestDTO.setAgvCode(robot);
                    genAgvSchedulingRequestDTO.setRobotCode(robot);
                    freeRobotService.freeRobot(genAgvSchedulingRequestDTO);
                });
            } catch (Exception e) {
                logger.warn("取消任务时释放小车异常{},{}", subTasks, e);
            }

        }
        mesResult.setMessage("任务"+mesCancelTaskRequest.getTaskCode()+"取消成功");
        return mesResult;
    }

    /**
     * 顶升式通用的取消任务
     * @param  mesCancelTaskRequest
     * @return
     */
    public MesResult publicCancelTask(MesCancelTaskRequest mesCancelTaskRequest) {
        //取消WCS和HIK任务
        MesResult mesResult = cancelMesTask(mesCancelTaskRequest);

        // TODO 需要区分不同执行方，目前写死，只有海康rcs
        List<SubTask> subTasks = subTaskMapper.selectByMainTaskNum(mesCancelTaskRequest.getTaskCode());
        List<SubTask> isusedSubTasks = subTasks.stream().filter(s -> SUB_ISSUED.equals(s.getTaskStatus()) && SENDED.equals(s.getSendStatus())).distinct().collect(Collectors.toList());
        logger.debug("筛选{}中所有进行中子任务{}", mesCancelTaskRequest.getTaskCode(), isusedSubTasks);

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
            //解锁货架和恢复货架
            BaseMapBerth startMapBerth = baseMapBerthMapper.selectOneByBercode(isusedSubTask.getStartBercode());
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
        if (needBindingPods.size() > 0) {
            mesResult.setMessage(JSONArray.toJSON(needBindingPods) + "货架需人工绑定");
        }

        return mesResult;
    }

    /**
     * 检查并解锁地码
     * @param berCode
     * @param lockSource
     */
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
     * 数量校验
     * @param count
     * @param reqCode
     */
    public void countCheck(Integer count, String reqCode) {
        if (count == null || count <= 0 || count >= 3) {
            throw new MesBusinessException(reqCode, "上下箱(总)数量只能为1或2");
        }
    }
    /**
     * 数量校验
     * @param count
     * @param reqCode
     */
    public void countCheckCanZero(Integer count, String reqCode) {
        if (count == null || count < 0 || count >= 3) {
            throw new MesBusinessException(reqCode, "回收箱数量只能为0,1或2");
        }
    }

    /**
     * 通知上料数量接口
     * @param supplyLoadNumNotify
     * @param reqCode
     * @return
     */
    public MesResult supplyLoadNum(SupplyLoadNumNotify supplyLoadNumNotify, String reqCode) {
        //校验
        publicCheck(supplyLoadNumNotify.getTaskCode(), reqCode);
        countCheck(supplyLoadNumNotify.getSupplyLoadNum(), reqCode);

        //查询context,把数量加入到context里
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(supplyLoadNumNotify.getTaskCode());
        String context = taskContext.getContext();
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(context, ContextDTO.class);
        contextDTO.setSupplyLoadNum(supplyLoadNumNotify.getSupplyLoadNum());
        String jsonStr = TaskContextUtils.objectToJson(contextDTO);
        taskContext.setContext(jsonStr);
        taskContextMapper.updateByMainTaskNum(taskContext);

        return new MesResult(reqCode);
    }

    /**
     * Mes通知小车可离开机台
     * @param data
     * @param reqCode
     * @return
     */
    public MesResult checkSuccess(NotifyAgvLeave data, String reqCode) {
        //1.参数校验
        publicCheck(data.getTaskCode(), reqCode);

        //2.获取原context
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(data.getTaskCode());
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(taskContext.getContext(), ContextDTO.class);

        //3.保存通知信息
        switch (data.getFlag()) {
            case LEAVE_UP: contextDTO.setCanLeaveUp(true); break;
            case LEAVE_DOWN_FIRST: contextDTO.setCanLeaveDownFirst(true); break;
            case LEAVE_DOWN_SECOND: contextDTO.setCanLeaveDownSecond(true); break;
            case LEAVE_UP_EMPTY: contextDTO.setCanLeaveUpEmpty(true); break;
            case LEAVE_DOWN_EMPTY: contextDTO.setCanLeaveDownEmpty(true); break;
            default:  throw new MesBusinessException(reqCode, "点位标识(flag)找不到对应的标记字符:" + data.getFlag());
        }

        //4.保存到数据库
        String jsonStr = TaskContextUtils.objectToJson(contextDTO);
        taskContextMapper.updateByPrimaryKeySelective(new TaskContext(taskContext.getId(), jsonStr));

        return new MesResult(reqCode);
    }

    /**
     * 通知上空框数量
     * @param emptyRecyleNotify
     * @param reqCode
     * @return
     */
    public MesResult emptyRecyleNum(EmptyRecyleNotify emptyRecyleNotify, String reqCode) {
        //校验
        publicCheck(emptyRecyleNotify.getTaskCode(), reqCode);
        countCheck(emptyRecyleNotify.getEmptyRecyleNum(), reqCode);

        //查询context,把数量加入到context里
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(emptyRecyleNotify.getTaskCode());
        String context = taskContext.getContext();
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(context, ContextDTO.class);
        contextDTO.setEmptyRecyleNum(emptyRecyleNotify.getEmptyRecyleNum());

        String jsonStr = TaskContextUtils.objectToJson(contextDTO);
        taskContext.setContext(jsonStr);
        taskContextMapper.updateByMainTaskNum(taskContext);

        return new MesResult(reqCode);
    }

    /**
     * 超越 上报已下料数量及已接收空框数量
     * @param emptyContainerNumber
     * @param reqCode
     * @return
     */
    public MesResult supllyAndRecyleResult(ReportEmptyContainerNumber emptyContainerNumber, String reqCode) {

        // 校验
        publicCheck(emptyContainerNumber.getTaskCode(), reqCode);
        if (StringUtils.isEmpty(emptyContainerNumber.getSupplyUnLoadWb())) {
            throw new MesBusinessException(reqCode, "请填写接料点");
        }

        //
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(emptyContainerNumber.getTaskCode());
        String context = taskContext.getContext();
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(context, ContextDTO.class);
//        contextDTO.setEmptyRecyleNum(emptyContainerNumber.getEmptyRecyleNum());

        if (emptyContainerNumber.getEmptyRecyleNum() != null) {
            Preconditions.checkMesBusinessError(contextDTO.getSupplyUnLoadNum() != emptyContainerNumber.getSupplyUnLoadWb(),
                    "下料数量与当前下料数量不符", reqCode);
        }
        if (emptyContainerNumber.getEmptyRecyleNum() != null) {
            Preconditions.checkMesBusinessError(contextDTO.getEmptyRecyleNum() != emptyContainerNumber.getEmptyRecyleNum(),
                    "空料箱回收数量与所回收数不符", reqCode);
        }

//        String jsonStr = TaskContextUtils.objectToJson(contextDTO);
//        taskContext.setContext(jsonStr);
//        taskContextMapper.updateByMainTaskNum(taskContext);

        return new MesResult(reqCode);
    }

    /**
     *  超越 通知AGV是否可以离开
     * @param supllyUnload
     * @param reqCode
     * @return
     */
    public MesResult supllyUnload(SupllyUnload supllyUnload, String reqCode) {
        // 校验
        publicCheck(supllyUnload.getTaskCode(), reqCode);

        if (StringUtils.isEmpty(supllyUnload.getCurrentWb())) {
            throw new MesBusinessException(reqCode, "当前点位必填");
        }

        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(supllyUnload.getTaskCode());
        String context = taskContext.getContext();
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(context, ContextDTO.class);

        //判断离开的类型
        switch (supllyUnload.getTaskSta()) {
            case LEAVE_GET_GOOD:
                contextDTO.setChaLeaveGood(true);
                break;
            case LEAVE_DOWN_GOOD:
                contextDTO.setChaLeaveUpGood(true);
                break;
            case LEAVE_GOOD_EMPTY:
                contextDTO.setChaLeaveDownEmpty(true);
                break;
            case LEAVE_UP_GOOD_EMPTY:
                contextDTO.setChaLeaveUpEmpty(true);
                break;
            default:
                throw new MesBusinessException(reqCode, "点位标识(taskSta)找不到对应的标记字符:" + supllyUnload.getTaskSta());
        }

        String jsonStr = TaskContextUtils.objectToJson(contextDTO);
        taskContextMapper.updateByPrimaryKeySelective(new TaskContext(taskContext.getId(), jsonStr));
        return new MesResult(reqCode);
    }

    /**
     * 滚筒是否滚动
     * @param startSupllyAndRecyle
     * @param reqCode
     * @return
     */
    public MesResult startSupllyAndRecyles(StartSupllyAndRecyles startSupllyAndRecyle, String reqCode) {

        // 校验
        publicCheck(startSupllyAndRecyle.getTaskCode(), reqCode);

        if (StringUtils.isEmpty(startSupllyAndRecyle.getCurrentWb())) {
            throw new MesBusinessException(reqCode, "供料点必填");
        }

        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(startSupllyAndRecyle.getTaskCode());
        String context = taskContext.getContext();
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(context, ContextDTO.class);
        contextDTO.setCurrentWb(startSupllyAndRecyle.getCurrentWb());


        // 如果同时回收空料箱 更新站点集合
        if (SEND_TYPE.equals(startSupllyAndRecyle.getNodeType()) && StringUtils.isNotEmpty(startSupllyAndRecyle.getRecyleWb())) {

            MainTask mainTask = mainTaskMapper.selectByMainTaskNum(startSupllyAndRecyle.getTaskCode());
            String staticViaPaths = mainTask.getStaticViaPaths();
            List<String> startPoint = JSONArray.parseArray(staticViaPaths, String.class);

            //将点位信息转换为berCode
            BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(startSupllyAndRecyle.getRecyleWb());
            Preconditions.checkMesBusinessError(baseMapBerth == null,
                    startSupllyAndRecyle.getRecyleWb() + "找不到别名对应的地图编码",reqCode);

            String endPoint = baseMapBerth.getBerCode();
            String jsonString = JSONArray.toJSONString(Arrays.asList(startPoint.get(1),endPoint));
            mainTask.setStaticViaPaths(jsonString);

            mainTaskMapper.updateByPrimaryKey(mainTask);
            contextDTO.setRecyleWb(startSupllyAndRecyle.getRecyleWb());
            contextDTO.setRollerDownGoodEmpty(true);
            contextDTO.setEmptyRecyleNum(1);
            contextDTO.setAgvProcessNotify("8");
            contextDTO.setRollerDownGoodNOEmpty(false);
        } else if (SEND_TYPE.equals(startSupllyAndRecyle.getNodeType())) {
            contextDTO.setAgvProcessNotify("4");
            contextDTO.setChaLeaveUpEmpty(false);
        }

        if (RECEIVE_TYPE.equals(startSupllyAndRecyle.getNodeType())){
            contextDTO.setRollerUpGood(true);
        }else if (SEND_TYPE.equals(startSupllyAndRecyle.getNodeType()) && StringUtils.isEmpty(startSupllyAndRecyle.getRecyleWb())){
            contextDTO.setRollerDownGoodEmpty(false);
            contextDTO.setRollerDownGoodNOEmpty(true);
        }else if (RECYLE_TYPE.equals(startSupllyAndRecyle.getNodeType())){
            contextDTO.setRollerUpEmpty(true);
        }else if (RECOVERY_TYPE.equals(startSupllyAndRecyle.getNodeType())){
            contextDTO.setRollerRecyleEmpty(true);
        }

        String jsonStr = TaskContextUtils.objectToJson(contextDTO);
        taskContextMapper.updateByPrimaryKeySelective(new TaskContext(taskContext.getId(), jsonStr));

        return new MesResult(reqCode);
    }

    public MesResult modifyPodStatus(ModifyPodStatus data, String reqCode) {

        if (StringUtils.isEmpty(data.getPodCode())) {
            throw new MesBusinessException(reqCode, "货架号必填");
        }
        if (StringUtils.isEmpty(data.getPodStatus())) {
            throw new MesBusinessException(reqCode, "货架状态必填");
        }

        BasePodDetail basePodDetailList = basePodDetailMapper.selectByPodCode(data.getPodCode());

        Preconditions.checkMesBusinessError(basePodDetailList == null, "该货架" + data.getPodCode() + "不存在",reqCode);

        BasePodDetail basePodDetail = new BasePodDetail();
        basePodDetail.setPodCode(data.getPodCode());
        basePodDetail.setPodProp4(data.getPodStatus());
        basePodDetail.setPodProp5(data.getModifyDate());
        int num = basePodDetailMapper.updatePodStatus(basePodDetail);
        return new MesResult();
    }

    /**
     * Mes 滚筒换车任务
     * @param mesCancelTaskRequest
     * @return
     */
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
