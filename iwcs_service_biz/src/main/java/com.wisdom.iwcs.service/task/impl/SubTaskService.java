package com.wisdom.iwcs.service.task.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.constant.ConditionMetStatus;
import com.wisdom.iwcs.common.utils.constant.CondtionTriger;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.common.utils.exception.TaskConditionException;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.domain.task.dto.SubTaskDTO;
import com.wisdom.iwcs.domain.task.dto.SubTaskInfo;
import com.wisdom.iwcs.mapper.task.SubTaskConditionMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapstruct.task.SubTaskMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.conditions.conditonHandler.IConditionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class SubTaskService {
    private final Logger logger = LoggerFactory.getLogger(SubTaskService.class);

    private final SubTaskMapper subTaskMapper;

    private final SubTaskMapStruct subTaskMapStruct;
    @Autowired
    private SubTaskConditionMapper subTaskConditionMapper;

    @Autowired
    public SubTaskService(SubTaskMapStruct SubTaskMapStruct, SubTaskMapper SubTaskMapper) {
        this.subTaskMapStruct = SubTaskMapStruct;
        this.subTaskMapper = SubTaskMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link SubTaskDTO }
     *
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
     *
     * @param records {@link List<SubTaskDTO> }
     *
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
     *
     * @param id {@link Integer }
     *
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
     *
     * @param record {@link SubTaskDTO }
     *
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
     *
     * @param record {@link SubTaskDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(SubTaskDTO record) {
        SubTask SubTask = subTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = subTaskMapper.updateByPrimaryKey(SubTask);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link SubTaskDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(SubTaskDTO record) {
        SubTask SubTask = subTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = subTaskMapper.updateByPrimaryKeySelective(SubTask);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     *
     * @param id {@link Integer }
     *
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
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    public int deleteMore(List<String> ids){
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
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<SubTaskDTO> }
     */
    public GridReturnData<SubTaskDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<SubTaskDTO> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map<String, Object> map = new HashMap<>(2);
        filterList.forEach(gridFilterInfo -> {
            if(gridFilterInfo.getFilterKey() != null && gridFilterInfo.getFilterValue() != null){
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
                    throw new TaskConditionException(-1, "子任务前置条件不满足", c.getSubTaskNum(), conditonHandleName);
                }
            }
        });
        return true;
    }

    /**
     * 回滚执行的子任务前置条件并还原相关数据的操作
     * @param subTask
     * @return
     */
    public boolean preConditionsRallback(SubTask subTask) {
        List<SubTaskCondition> preTaskRelConditionsList =
                subTaskConditionMapper.selectByTaskNumAndTrigerType(subTask.getSubTaskNum(), CondtionTriger.PRE_CONDITION.getCode());
        preTaskRelConditionsList.forEach(c -> {
            //如果条件状态为符合,则执行子任务前置条件回滚操作
            if (ConditionMetStatus.CONFORMITY.getCode().equals(c.getConditionMetStatus())) {
                String conditonHandleName = c.getConditonHandler();
                IConditionHandler conditonHandler = (IConditionHandler) AppContext.getBean(conditonHandleName);
                boolean met = conditonHandler.rollbackCondition(c);
                if (!met) {
                    //抛出异常
                    throw new TaskConditionException(-1, "子任务前置条件回滚失败", c.getSubTaskNum(), conditonHandleName);
                }
            }
        });
        return true;
    }


    public SubTask getCurrentPendingSubtask(String mainTaskNum) {
        List<SubTask> subTasks = subTaskMapper.selectByMainTaskNum(mainTaskNum);
        subTasks = subTasks.stream().sorted(Comparator.comparing(SubTask::getSubTaskSeq)).collect(Collectors.toList());

        return subTasks.get(0);
    }


}
