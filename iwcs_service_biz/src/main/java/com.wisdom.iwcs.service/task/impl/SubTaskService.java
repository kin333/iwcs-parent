package com.wisdom.iwcs.service.task.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.constant.ConditionMetStatus;
import com.wisdom.iwcs.common.utils.constant.CondtionTriger;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.exception.TaskConditionException;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.domain.task.dto.SubTaskDTO;
import com.wisdom.iwcs.domain.task.dto.SubTaskInfo;
import com.wisdom.iwcs.domain.task.dto.SubTaskStatusEnum;
import com.wisdom.iwcs.mapper.task.SubTaskConditionMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapstruct.task.SubTaskMapStruct;
import com.wisdom.iwcs.service.log.logImpl.RabbitMQPublicService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.conditions.conditonHandler.IConditionHandler;
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
import java.util.stream.Collectors;

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


    public SubTask getCurrentPendingSubtask(String mainTaskNum) {
        List<SubTask> subTasks = subTaskMapper.selectByMainTaskNum(mainTaskNum);
        subTasks = subTasks.stream().sorted(Comparator.comparing(SubTask::getSubTaskSeq)).filter(t -> !TaskConstants.subTaskStatus.SUB_FINISHED.equals(t.getTaskStatus())).collect(Collectors.toList());
        if (subTasks != null && subTasks.size() != 0) {
            return subTasks.get(0);
        } else {
            return null;
        }

    }

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
        //如果子任务单未空，需要创建第一个子任务
        if (subTasks.isEmpty()) {

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
                    SubTask lastFinishedSubtask = lastFinishedSubtaskOpt.get();
                    //TODO 替换子任务模板号
                    logger.info("主任务最后一个执行完成的子任务编号{}，任务模板号{}", lastFinishedSubtask.getSubTaskNum(), "");

                } else {
                    logger.error("动态创建子任务数据异常，主任务" + mainTaskNum + "找不到最后一个执行完结的子任务");
                    throw new BusinessException("动态创建子任务数据异常，主任务" + mainTaskNum + "找不到最后一个执行完结的子任务");
                }


            }


        }
        //根据任务路由查找对应的子任务创建条件

        //按顺序校验子任务的创建条件是否满足，若满足，则创建子任务
        subTasks = subTasks.stream().sorted(Comparator.comparing(SubTask::getSubTaskSeq)).collect(Collectors.toList());
        if (subTasks != null && !subTasks.isEmpty()) {
            return subTasks.get(0);
        } else {
            return null;
        }
    }


    public Result setPriority(SubTaskDTO subTaskDTO) {
        String subTaskNum = subTaskDTO.getSubTaskNum();
        Integer priority = subTaskDTO.getPriority();
        if (StringUtils.isEmpty(subTaskNum) || priority == null || priority < 0) {
            return new Result(400, "缺少参数(子任务单号或优先级)");
        }
        int changeRow = subTaskMapper.updatePriority(subTaskNum, priority);
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
}
