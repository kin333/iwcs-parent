package com.wisdom.iwcs.service.task.impl;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.constant.ConditionMetStatus;
import com.wisdom.iwcs.common.utils.constant.CondtionTriger;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.exception.TaskConditionException;
import com.wisdom.iwcs.common.utils.idUtils.CodeBuilder;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.domain.task.*;
import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;
import com.wisdom.iwcs.domain.task.dto.SubTaskDTO;
import com.wisdom.iwcs.domain.task.dto.SubTaskInfo;
import com.wisdom.iwcs.domain.task.dto.SubTaskStatusEnum;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.task.*;
import com.wisdom.iwcs.mapstruct.task.SubTaskMapStruct;
import com.wisdom.iwcs.service.log.logImpl.RabbitMQPublicService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.conditions.conditonHandler.IConditionHandler;
import com.wisdom.iwcs.service.task.conditions.conditonHandler.IRelConditionHandler;
import com.wisdom.iwcs.service.task.conditions.pod.IGetPodStrategic;
import com.wisdom.iwcs.service.task.conditions.point.IGetPointStrategic;
import com.wisdom.iwcs.service.task.conditions.robot.IGetRobotStrategic;
import com.wisdom.iwcs.service.task.conditions.workercode.IGetWorkerCodeStrategic;
import com.wisdom.iwcs.service.task.scheduler.WcsTaskScheduler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskStatus.SUB_NOT_ISSUED;

@Service
public class SubTaskService {
    private final Logger logger = LoggerFactory.getLogger(SubTaskService.class);
    @Autowired
    private final SubTaskMapper subTaskMapper;

    private final SubTaskMapStruct subTaskMapStruct;
    @Autowired
    private SubTaskConditionMapper subTaskConditionMapper;

    @Autowired
    PlatformTransactionManager transactionManager;
    @Autowired
    TransactionTemplate transactionTemplate;
    @Autowired
    TaskRelMapper taskRelMapper;
    @Autowired
    TaskRelConditionMapper taskRelConditionMapper;

    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    TaskCreateService taskCreateService;
    @Autowired
    MainTaskMapper mainTaskMapper;
    @Autowired
    BasePodDetailMapper basePodDetailMapper;
    @Autowired
    WcsTaskScheduler wcsTaskScheduler;

    @Autowired
    public SubTaskService(SubTaskMapStruct SubTaskMapStruct, SubTaskMapper SubTaskMapper) {
        this.subTaskMapStruct = SubTaskMapStruct;
        this.subTaskMapper = SubTaskMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link SubTaskDTO }
     * @return int
     */
    public int insert(SubTaskDTO record) {
        SubTask SubTask = subTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = subTaskMapper.insert(SubTask);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<SubTaskDTO> }
     * @return int
     */
    public int insertBatch(List<SubTaskDTO> records) {
        List<SubTask> recordList = subTaskMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = subTaskMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link SubTaskDTO }
     */
    public SubTaskDTO selectByPrimaryKey(Integer id) {

        SubTask SubTask = subTaskMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(SubTask, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return subTaskMapStruct.toDto(SubTask);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link SubTaskDTO }
     * @return {@link List<SubTaskDTO> }
     */
    public List<SubTaskDTO> selectSelective(SubTaskDTO record) {
        SubTask SubTask = subTaskMapStruct.toEntity(record);

        List<SubTask> SubTaskList = subTaskMapper.select(SubTask);
        return subTaskMapStruct.toDto(SubTaskList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link SubTaskDTO }
     * @return int
     */
    public int updateByPrimaryKey(SubTaskDTO record) {
        SubTask SubTask = subTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = subTaskMapper.updateByPrimaryKey(SubTask);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link SubTaskDTO }
     * @return int
     */
    public int updateByPrimaryKeySelective(SubTaskDTO record) {
        SubTask SubTask = subTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = subTaskMapper.updateByPrimaryKeySelective(SubTask);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    public int deleteByPrimaryKey(Integer id) {
        int num = subTaskMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     *
     * @param id {@link Integer }
     *
     * @return int
     */
//    public int deleteLogicByPrimaryKey(Integer id) {
//        return subTaskMapper.deleteLogicByPrimaryKey(id);
//    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return subTaskMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
//    public int deleteMoreLogic(List<String> ids){
//        return subTaskMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<SubTaskDTO> }
     */
    public GridReturnData<SubTaskDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<SubTaskDTO> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map<String, Object> map = new HashMap<>(2);
        filterList.forEach(gridFilterInfo -> {
            if (gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null) {
                map.put(gridFilterInfo.getFilterKey(), gridFilterInfo.getFilterValue());
            }
        });
        map.put("searchKey", gridPageRequest.getSearchKey());
        // 对map中的参数的合法性进行校验

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);

        List<SubTask> list = subTaskMapper.selectPage(map);

        PageInfo<SubTask> pageInfo = new PageInfo<>(list);
        PageInfo<SubTaskDTO> pageInfoFinal = new PageInfo<>(subTaskMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 执行子任务前置条件检查并锁定/修改相关数据
     *
     * @return
     */
    public boolean preConditionsCheckAndExec(SubTaskInfo subTaskInfo) {
        List<SubTaskCondition> preTaskRelConditionsList = subTaskInfo.getPreTaskRelConditionsList();
        preTaskRelConditionsList.stream().forEach(c -> {
            String conditonHandleName = c.getConditonHandler();
            IConditionHandler conditonHandler = (IConditionHandler) AppContext.getBean(conditonHandleName);
            boolean met = conditonHandler.handleCondition(c);
            if (!met) {
                //抛出异常
                throw new TaskConditionException(-1, "子任务前置条件不满足", c.getSubTaskNum(), conditonHandleName);
            }
        });
        return true;
    }

    /**
     * 执行子任务前置条件检查并锁定/修改相关数据
     *
     * @return
     */
    @Transactional
    public boolean preConditionsCheckAndExec(SubTask subTask) {
        List<SubTaskCondition> preTaskRelConditionsList =
                subTaskConditionMapper.selectByTaskNumAndTrigerType(subTask.getSubTaskNum(), CondtionTriger.PRE_CONDITION.getCode());
        preTaskRelConditionsList.stream().forEach(c -> {
            //如果条件状态为不符合,则执行子任务前置条件检查操作
            if (ConditionMetStatus.IN_CONFORMITY.getCode().equals(c.getConditionMetStatus())) {
                String conditonHandleName = c.getConditonHandler();
                IConditionHandler conditonHandler = (IConditionHandler) AppContext.getBean(conditonHandleName);
                boolean met = conditonHandler.handleCondition(c);
                if (!met) {
                    //抛出异常
                    logger.info("{}子任务前置条件不满足,条件处理器{}", c.getSubTaskNum(), conditonHandleName);
                    //向消息队列发送消息
                    String message = "子任务前置条件不满足,主任务号:" + subTask.getMainTaskNum()
                            + ",条件处理器:" + conditonHandleName;
                    RabbitMQPublicService.failureTaskLog(new TaskOperationLog(subTask.getSubTaskNum(), TaskConstants.operationStatus.PRE_CONDITION_FAILURE,message));
                    throw new TaskConditionException(-1, "子任务前置条件不满足", c.getSubTaskNum(), conditonHandleName);
                }
            }
        });
        //将子任务的状态改为正在执行
        subTaskMapper.updateTaskStatusByNum(subTask.getSubTaskNum(), SubTaskStatusEnum.Executing.getStatusCode());
        //将子任务条件表中的条件状态改为已符合
        subTaskConditionMapper.updateMetStatusBySubTaskNum(subTask.getSubTaskNum(), TaskConstants.metStatus.CONFORM, CondtionTriger.PRE_CONDITION.getCode());

        logger.info("子任务{}前置条件已全部满足", subTask.getSubTaskNum());

        SubTask tmpSubTask = subTaskMapper.selectBySubTaskNum(subTask.getSubTaskNum());
        //向消息队列发送消息
        String message = "子任务前置条件已全部满足,主任务号:" + subTask.getMainTaskNum() + ",货架号:" + tmpSubTask.getPodCode()
                + ",起始地码:" + tmpSubTask.getStartBercode() + ",终点地码:" + tmpSubTask.getEndBercode();
        RabbitMQPublicService.successTaskLog(new TaskOperationLog(subTask.getSubTaskNum(), TaskConstants.operationStatus.PRE_CONDITION_SUCCESS,message));
        return true;
    }

    /**
     * 回滚执行的子任务前置条件并还原相关数据的操作
     *
     * @param
     * @return
     */
    @Transactional
    public boolean rollbackPreCondition(String subTaskNum) {
        List<SubTaskCondition> preTaskRelConditionsList =
                subTaskConditionMapper.selectByTaskNumAndTrigerType(subTaskNum, CondtionTriger.PRE_CONDITION.getCode());
        preTaskRelConditionsList.forEach(c -> {
            //如果条件状态为符合,则执行子任务前置条件回滚操作
            if (ConditionMetStatus.CONFORMITY.getCode().equals(c.getConditionMetStatus())) {
                String conditonHandleName = c.getConditonHandler();
                IConditionHandler conditonHandler = (IConditionHandler) AppContext.getBean(conditonHandleName);
                boolean met = conditonHandler.rollbackCondition(c);
                if (!met) {
                    logger.error("子任务{}前置条件回滚失败,失败Handler:{}", c.getSubTaskNum(), conditonHandleName);
                    //抛出异常
                    throw new TaskConditionException(-1, "子任务前置条件回滚失败" + conditonHandleName, c.getSubTaskNum(), conditonHandleName);
                }
            }
        });
        //将子任务条件表中的条件状态回滚为不符合
        subTaskConditionMapper.updateMetStatusBySubTaskNum(subTaskNum, TaskConstants.metStatus.NOT_CONFORM, CondtionTriger.PRE_CONDITION.getCode());
        return true;
    }

    @Transactional
    public SubTask getCurrentPendingSubtask(String mainTaskNum) {
        List<SubTask> subTasks = subTaskMapper.selectByMainTaskNum(mainTaskNum);
        subTasks = subTasks.stream().sorted(Comparator.comparing(SubTask::getSubTaskSeq)).filter(t -> !TaskConstants.subTaskStatus.SUB_FINISHED.equals(t.getTaskStatus())).collect(Collectors.toList());
        if (subTasks != null && subTasks.size() != 0) {
            return subTasks.get(0);
        } else {
            return dynamicCreateNextSubtask(mainTaskNum);
        }

    }


//    /**
//     * 取消子任务
//     * @param subtaskNum
//     * @return
//     */
//    public SubTask cancleSubtask(String subtaskNum) {
//        logger.info("开始取消子任务{}",subtaskNum);
//        //取消子任务
//        SubTask subTask = subTaskMapper.selectBySubTaskNum(subtaskNum);
//
//        Preconditions.checkArgument(null != subTask,"子任务不存在"+subtaskNum);
//        SubTask subTaskTmp = new SubTask();
//        subTaskTmp.setId(subTask.getId());
//        String curTaskStatus = subTask.getTaskStatus();
//        //已完成及取消状态下的任务不可取消
//        if(TaskConstants.subTaskStatus.SUB_FINISHED.equals(curTaskStatus) || TaskConstants.subTaskStatus.SUB_CANCELED.equals(curTaskStatus) ){
//            logger.info("取消失败，子任务{}处于{}状态下的任务不可取消",subtaskNum,SubTaskStatusEnum.fromCode(curTaskStatus));
//            throw new BusinessException("取消失败，处于"+SubTaskStatusEnum.fromCode(curTaskStatus)+"状态下的任务不可取消");
//        }else{
//            subTaskTmp.setCancelOperator(SecurityUtils.getCurrentUserId());
//            subTaskTmp.setCancelTime(new Date());
//            subTaskTmp.setCancelSceneRecoveryStatus(TaskConstants.CancelSceneRecoveryStatus.PENDING);
//            subTaskTmp.setTaskStatus(TaskConstants.subTaskStatus.SUB_CANCELED);
//            subTaskMapper.updateByPrimaryKeySelective(subTaskTmp);
//            MainTask mainTask = mainTaskMapper.selectByMainTaskNum(subTask.getMainTaskNum());
//            MainTask mainTaskTmp = new MainTask();
//            mainTaskTmp.setTaskStatus(TaskConstants.mainTaskStatus.MAIN_CANCELED);
//            mainTaskTmp.setId(mainTask.getId());
//            mainTaskMapper.updateByPrimaryKeySelective(mainTaskTmp);
//            logger.info("尝试取消子任务{}执行器线程",subtaskNum);
//            wcsTaskScheduler.stopMainTaskThread(subTask.getMainTaskNum(),subtaskNum);
//            CancelTaskRequestDTO cancelTaskRequestDTO = new CancelTaskRequestDTO();
//            cancelTaskRequestDTO.setTaskCode(subTask.getWorkerTaskCode());
//            logger.info("调用取消任务接口,海康任务编号：{}",subTask.getWorkerTaskCode());
//            Result result = cancelTaskService.cancelTask(cancelTaskRequestDTO);
//            logger.info("取消海康任务结果：{}", JSON.toJSONString(result));
//            if(200 == result.getReturnCode()){
//                logger.info("取消海康成功，{}",subTask.getWorkerTaskCode());
//            }else{
//                logger.info("取消海康失败，{},回滚数据库",subTask.getWorkerTaskCode());
//                throw  new BusinessException("取消AGV任务失败，请确认任务状态正确后重试！");
//            }
//
//        }
//
//        return subTask;
//    }

    /**
     * 判断是否有未创建的下一子任务单,动态创建下一子任务
     *
     * @param mainTaskNum
     * @return
     */
    public SubTask dynamicCreateNextSubtask(String mainTaskNum) {
        logger.info("开始尝试获取/创建下一子任务，主任务号{}", mainTaskNum);
        //获取当前主任务最后一个执行完成的子任务，并根据子任务查找到其配置的下一任务路由
        List<SubTask> subTasks = subTaskMapper.selectByMainTaskNum(mainTaskNum);
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(mainTaskNum);
        String finalNextSubtaskTemplCode = "";
        if (TaskConstants.mainTaskStatus.MAIN_FINISHED.equals(mainTask.getTaskStatus())) {
            logger.warn("主任务状态值为一结束，主任务号{}，不应再调用获取、生成下一步子任务方法", mainTaskNum);
            return null;
        }
        //如果子任务单未空，需要创建第一个子任务
        if (subTasks.isEmpty()) {
            String mainTaskTypeCode = mainTask.getMainTaskTypeCode();
            logger.info("当前主任务类型编号为{}", mainTaskTypeCode);
            //获取第一个子任务模板编号
            List<TaskRel> taskRels = taskRelMapper.selectByMainTaskType(mainTaskTypeCode);
            if (taskRels.isEmpty()) {
                logger.warn("{}主任务类型下未配置子任务", mainTaskTypeCode);
                return null;
            } else {
                Optional<TaskRel> firstTaskRel = taskRels.stream().sorted(Comparator.comparing(TaskRel::getSubTaskSeq)).findFirst();
                finalNextSubtaskTemplCode = firstTaskRel.get().getTemplCode();
                return autoCreateSubTask(finalNextSubtaskTemplCode, mainTaskNum);
            }
        } else {
            //非第一个子任务的情况下，根据最后一个执行的子任务创建下一子任务
            List<SubTask> subtasksSortedBySeqAsc = subTasks.stream().sorted(Comparator.comparing(SubTask::getSubTaskSeq)).collect(Collectors.toList());
            Optional<SubTask> nextUnFinishedSubtaskOpt = subtasksSortedBySeqAsc.stream().filter(s -> !TaskConstants.subTaskStatus.SUB_FINISHED.equals(s.getTaskStatus())).findFirst();
            if (nextUnFinishedSubtaskOpt.isPresent()) {
                SubTask nextUnfishedTask = nextUnFinishedSubtaskOpt.get();
                logger.info("主任务{}存在未完成的子任务，下一待执行子任务号{}", mainTaskNum, nextUnfishedTask.getSubTaskNum());
                return nextUnfishedTask;
            } else {
                //没有已创建的待执行子任务，动态创建下一子任务： 根据
                Optional<SubTask> lastFinishedSubtaskOpt = subtasksSortedBySeqAsc.stream().max(Comparator.comparing(SubTask::getSubTaskSeq));
                if (lastFinishedSubtaskOpt.isPresent()) {
                    //根据下游任务模板号查询并依次判断创建条件是否满足，找到第一个满足的任务模板，并根据模板配置信息及上下文创建子任务单
                    SubTask lastFinishedSubtask = lastFinishedSubtaskOpt.get();
                    logger.info("主任务最后一个执行完成的子任务编号{}，任务模板号{}", lastFinishedSubtask.getSubTaskNum(), lastFinishedSubtask.getTemplCode());
                    String templCode = lastFinishedSubtask.getTemplCode();
                    //获取最后一个子任务在模板中配置的下游任务列表
                    TaskRel lastFinishedSubtaskTaskRel = taskRelMapper.selectByTemplCode(templCode);
                    String nextTaskRouter = lastFinishedSubtaskTaskRel.getOutflow();
                    String[] nextTaskRouters = nextTaskRouter.split(";");
                    if (nextTaskRouters.length > 0) {

                        //有下一步,遍历检查下一步创建条件是否满足
                        for (int i = 0; i < nextTaskRouters.length; i++) {
                            String tmpTemplCode = nextTaskRouters[i];
                            List<TaskRelCondition> taskRelConditions = taskRelConditionMapper.selectByTemplCodeAndConType(tmpTemplCode, CondtionTriger.CREATED_CONDITION.getCode());
                            if (taskRelConditions.isEmpty()) {
                                //没有创建前置条件直接创建
                                finalNextSubtaskTemplCode = tmpTemplCode;
                                break;
                            } else {
                                AtomicReference<Boolean> createConAllMet = new AtomicReference<>(new Boolean(true));
                                //判断前置条件是否满足
                                taskRelConditions.stream().forEachOrdered(t -> {
                                    logger.info("检查子任务模板{}的条件是否满足，条件：{}", t.getTemplCode(), JSON.toJSONString(t));
                                    String conditonHandler = t.getConditonHandler();
                                    IRelConditionHandler iRelConditionHandler = (IRelConditionHandler) ApplicationContextUtils.getBean(conditonHandler);
                                    boolean met = iRelConditionHandler.handleCondition(mainTaskNum, t);
                                    if (!met) {
                                        createConAllMet.set(false);
                                        return;
                                    }
                                });
                                if (createConAllMet.get()) {
                                    finalNextSubtaskTemplCode = tmpTemplCode;
                                    break;
                                }
                            }

                        }

                        //
                        if (StringUtils.isNotBlank(finalNextSubtaskTemplCode)) {
                            return autoCreateSubTask(finalNextSubtaskTemplCode, mainTaskNum);
                        } else {
                            return null;
                        }

                    } else {
                        //没有下一步
                        return null;
                    }
                }
            }


        }
        return null;

    }


    public Result setPriority(List<SubTaskDTO> subTaskDTO) {
        List<String> subTaskList = new ArrayList<>();
        String subTaskNum = subTaskDTO.get(0).getSubTaskNum();
        Integer priority = subTaskDTO.get(0).getPriority();
        if (StringUtils.isEmpty(subTaskNum) || priority == null || priority < 0) {
            return new Result(400, "缺少参数(子任务单号或优先级)");
        }
        subTaskDTO.forEach(item -> {
            subTaskList.add(item.getSubTaskNum());
        });
        int changeRow = subTaskMapper.updatePriority(subTaskList, priority);
        if (changeRow <= 0) {
            return new Result(400, "优先级未修改");
        }
        return new Result();
    }
    /**
     * 根据任务号将任务状态置为已完成
     *
     * @param subTaskNum
     */
    public void finishTask(String subTaskNum) {
        subTaskMapper.updateTaskStatusByNum(subTaskNum, SubTaskStatusEnum.Finished.getStatusCode());
    }

    /**
     * 执行子任务后置条件检查并锁定/修改相关数据
     *
     * @param subTask
     * @return
     */
    public boolean postConditionsCheckAndExec(SubTask subTask) {
        AtomicBoolean resultFlag = new AtomicBoolean(true);

        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {

                List<SubTaskCondition> postTaskRelConditionsList =
                        subTaskConditionMapper.selectByTaskNumAndTrigerType(subTask.getSubTaskNum(), CondtionTriger.POST_CONDITION.getCode());
                postTaskRelConditionsList.stream().forEach(c -> {
                    //如果条件状态为不符合,则执行子任务后置条件检查操作
                    if (ConditionMetStatus.IN_CONFORMITY.getCode().equals(c.getConditionMetStatus())) {
                        String conditonHandleName = c.getConditonHandler();
                        IConditionHandler conditonHandler = (IConditionHandler) AppContext.getBean(conditonHandleName);
                        boolean met = conditonHandler.handleCondition(c);
                        if (!met) {
                            //抛出异常
                            logger.info("{}子任务后置条件暂时不满足不满足,条件名称{}", subTask.getSubTaskNum(), conditonHandleName);
                            //向消息队列发送消息
                            String message = "子任务后置条件不满足,主任务号:" + subTask.getMainTaskNum()
                                     + ",条件名称" + conditonHandleName;
                            RabbitMQPublicService.failureTaskLog(new TaskOperationLog(subTask.getSubTaskNum(), TaskConstants.operationStatus.POST_CONDITION_FAILURE,message));
                            transactionStatus.setRollbackOnly();
                            resultFlag.set(false);
                        }
                    }
                });
                if (!resultFlag.get()) {
                    return false;
                }
                //将子任务的状态改为已结束
                finishTask(subTask.getSubTaskNum());
                //将子任务条件表中的条件状态改为已符合
                subTaskConditionMapper.updateMetStatusBySubTaskNum(subTask.getSubTaskNum(), TaskConstants.metStatus.CONFORM, CondtionTriger.POST_CONDITION.getCode());
                logger.info("子任务{}后置条件已全部满足", subTask.getSubTaskNum());
                //向消息队列发送消息
                String message = "子任务后置条件已全部满足,主任务号:" + subTask.getMainTaskNum();
                RabbitMQPublicService.successTaskLog(new TaskOperationLog(subTask.getSubTaskNum(), TaskConstants.operationStatus.POST_CONDITION_SUCCESS,message));
                return resultFlag;
            }
        });


        return resultFlag.get();
    }

    /**
     * 动态生成任务
     */
    public SubTask autoCreateSubTask(String templateCode, String mainTaskNum) {
        TaskRel taskRel = taskRelMapper.selectByTemplCode(templateCode);
        Preconditions.checkBusinessError(taskRel == null, "模板配置出错" + templateCode);
        SubTask subTask = new SubTask();
        //添加基础数据
        String subTaskNum = CodeBuilder.codeBuilder("S");
        subTask.setSubTaskNum(subTaskNum);
        subTask.setMainTaskNum(mainTaskNum);
        subTask.setSubTaskTyp(taskRel.getSubTaskTypeCode());
        subTask.setMainTaskType(taskRel.getMainTaskTypeCode());
        subTask.setCreateDate(new Date());
        subTask.setThirdType(taskRel.getThirdType());
        subTask.setAppCode(taskRel.getAppCode());
        subTask.setThirdInvokeType(taskRel.getThirdInvokeType());
        subTask.setThirdUrl(taskRel.getThirdUrl());
        subTask.setThirdStartMethod(taskRel.getThirdStartMethod());
        subTask.setThirdEndMethod(taskRel.getThirdEndMethod());
        subTask.setTaskStatus(SUB_NOT_ISSUED);
        subTask.setSendStatus(SUB_NOT_ISSUED);
        subTask.setNeedTrigger(taskRel.getNeedTrigger());
        subTask.setNeedConfirm(taskRel.getNeedConfirm());
        subTask.setNeedInform(taskRel.getNeedInform());
        subTask.setNeedTrigger(taskRel.getNeedTrigger());
        subTask.setNeedInform(taskRel.getNeedInform());
        subTask.setNeedConfirm(taskRel.getNeedConfirm());
        subTask.setWorkerTaskCode(subTaskNum);
        subTask.setTemplCode(templateCode);
        //添加子任务顺序
        List<SubTask> subTaskList = subTaskMapper.selectByMainTaskNum(mainTaskNum);
        subTask.setSubTaskSeq(subTaskList.size() + 1);
        //添加任务起始点
        if (StringUtils.isNotBlank(taskRel.getStartPointAccess())) {
            if (("calculateByPropStrategic").equals(taskRel.getStartPointAccess())) {
                subTask.setMapCode("AB");
            }else {
                IGetPointStrategic getPointStrategic = AppContext.getBean(taskRel.getStartPointAccess());
                String startPoint = getPointStrategic.getPoint(new AutoCreateBaseInfo(mainTaskNum, taskRel.getStartPointAccessValue(), taskRel));
                subTask.setStartBercode(startPoint);
                if (StringUtils.isNotEmpty(startPoint)) {
                    BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(startPoint);
                    subTask.setMapCode(baseMapBerth.getMapCode());
                    subTask.setStartAlias(baseMapBerth.getPointAlias());
                    subTask.setStartX(baseMapBerth.getCoox().doubleValue());
                    subTask.setStartY(baseMapBerth.getCooy().doubleValue());
                }
            }
        }



        //添加任务终点
        if (StringUtils.isNotBlank(taskRel.getEndPointAccess())) {
                IGetPointStrategic getPointStrategic = AppContext.getBean(taskRel.getEndPointAccess());
                String endPoint = getPointStrategic.getPoint(new AutoCreateBaseInfo(mainTaskNum, taskRel.getEndPointAccessValue(), taskRel));
                subTask.setEndBercode(endPoint);
                if (StringUtils.isNotEmpty(endPoint)) {
                    BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(endPoint);
                    Preconditions.checkBusinessError(baseMapBerth == null, endPoint + "地图信息不存在");
                    subTask.setEndMapCode(baseMapBerth.getMapCode());
                    subTask.setEndAlias(baseMapBerth.getPointAlias());
                    subTask.setEndX(baseMapBerth.getCoox().doubleValue());
                    subTask.setEndY(baseMapBerth.getCooy().doubleValue());
                    //锁定终点
                    int rows = baseMapBerthMapper.updateLockSourceByBercode(endPoint, subTask.getSubTaskNum());
                    if(rows > 0) {
                        logger.info("子任务{}添加地码{}的锁定源成功", subTaskNum, endPoint);
                    } else {
                        logger.info("子任务{}添加地码{}的锁定源失败", subTaskNum, endPoint);
                    }
                }

        }
        //添加货架
        if (StringUtils.isNotBlank(taskRel.getPodAccess())) {
            IGetPodStrategic getPointStrategic = AppContext.getBean(taskRel.getPodAccess());
            String podCode = getPointStrategic.getPod(new AutoCreateBaseInfo(mainTaskNum, taskRel.getPodAccessValue(), taskRel));
            subTask.setPodCode(podCode);
            //锁定货架
            int rows = basePodDetailMapper.updateLockSourceByBercode(podCode, subTaskNum);
            if(rows > 0) {
                logger.info("子任务{}添加货架{}的锁定源成功", subTaskNum, podCode);
            } else {
                logger.info("子任务{}添加货架{}的锁定源失败", subTaskNum, podCode);
            }
        }
        //添加机器人编号
        if (StringUtils.isNotBlank(taskRel.getRobotAccess())) {
            IGetRobotStrategic getPointStrategic = AppContext.getBean(taskRel.getRobotAccess());
            String robotCode = getPointStrategic.getRobotCode(new AutoCreateBaseInfo(mainTaskNum, taskRel.getRobotAccessValue()));
            subTask.setRobotCode(robotCode);
        }
        //添加任务编号(下发给Hik的任务编号)
        if (StringUtils.isNotBlank(taskRel.getWorkerTaskCodeAccess())) {
            IGetWorkerCodeStrategic getPointStrategic = AppContext.getBean(taskRel.getWorkerTaskCodeAccess());
            String workerCode = getPointStrategic.getWorkerCode(new AutoCreateBaseInfo(mainTaskNum, taskRel.getWorkerTaskCodeAccessValue()));
            subTask.setWorkerTaskCode(workerCode);
        }

        subTaskMapper.insertSelective(subTask);

        //向消息队列发送消息
        String message = templateCode + "任务创建完成,主任务号:" + mainTaskNum;
        RabbitMQPublicService.successTaskLog(new TaskOperationLog(subTaskNum, TaskConstants.operationStatus.CREATE_TASK,message));


        //创建子任务前置后置条件
        taskCreateService.addSubTaskCondition(templateCode, subTaskNum);

        return subTask;
    }
}
