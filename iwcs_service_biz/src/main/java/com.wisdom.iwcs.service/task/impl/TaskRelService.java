package com.wisdom.iwcs.service.task.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.dto.MenuTreeDto;
import com.wisdom.iwcs.domain.task.*;
import com.wisdom.iwcs.domain.task.dto.RelAndConditionDTO;
import com.wisdom.iwcs.domain.task.dto.TaskRelConditionDTO;
import com.wisdom.iwcs.domain.task.dto.TaskRelDTO;
import com.wisdom.iwcs.mapper.task.MainTaskTypeMapper;
import com.wisdom.iwcs.mapper.task.TaskRelActionMapper;
import com.wisdom.iwcs.mapper.task.TaskRelConditionMapper;
import com.wisdom.iwcs.mapper.task.TaskRelMapper;
import com.wisdom.iwcs.mapstruct.task.TaskRelConditionsMapStruct;
import com.wisdom.iwcs.mapstruct.task.TaskRelMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskRelService {
    private final Logger logger = LoggerFactory.getLogger(TaskRelService.class);

    private final TaskRelMapper TaskRelMapper;

    private final TaskRelMapStruct TaskRelMapStruct;


    private final TaskRelConditionMapper TaskRelConditionMapper;

    private final TaskRelConditionsMapStruct taskRelConditionsMapStruct;

    private final TaskRelActionMapper taskRelActionMapper;

    private final MainTaskTypeMapper mainTaskTypeMapper;

    @Autowired
    public TaskRelService(TaskRelMapStruct TaskRelMapStruct, TaskRelMapper TaskRelMapper, TaskRelConditionMapper TaskRelConditionMapper, TaskRelConditionsMapStruct taskRelConditionsMapStruct, TaskRelActionMapper taskRelActionMapper, MainTaskTypeMapper mainTaskTypeMapper) {
        this.TaskRelMapStruct = TaskRelMapStruct;
        this.TaskRelMapper = TaskRelMapper;
        this.TaskRelConditionMapper = TaskRelConditionMapper;
        this.taskRelConditionsMapStruct = taskRelConditionsMapStruct;
        this.taskRelActionMapper = taskRelActionMapper;
        this.mainTaskTypeMapper = mainTaskTypeMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link TaskRelDTO }
     * @return int
     */
    public int insert(TaskRelDTO record) {
        TaskRel TaskRel = TaskRelMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskRelMapper.insert(TaskRel);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    public List<TaskRelSubMain> selectSubMainByMainCode(TaskRelSubMain taskRelSubMain) {

        List<TaskRelSubMain> taskRelSubMainList = TaskRelMapper.selectSubMainByMainCode(taskRelSubMain.getMainTaskTypeCode());
        if (taskRelSubMainList.size() == 0) {
            return null;
        }
        taskRelSubMainList.get(0).setFloor(1);

        List<TaskRelSubMain> taskRelSubMainData = formatterData(taskRelSubMainList.get(0), taskRelSubMainList);

        return taskRelSubMainData;
    }

    public List<TaskRelSubMain> formatterData(TaskRelSubMain taskRelSubMain, List<TaskRelSubMain> taskRelSubMainList) {

        List<TaskRelSubMain> dataList = new ArrayList<TaskRelSubMain>();
        if (!StringUtils.isEmpty(taskRelSubMain.getOutflow())) {
            String[] outflow = taskRelSubMain.getOutflow().split(";");
            for (int num = 0; num < outflow.length; num++) {
                for (int idx = 0; idx < taskRelSubMainList.size(); idx++) {
                    if (outflow[num].equals(taskRelSubMainList.get(idx).getTemplCode())) {
                        if (taskRelSubMainList.get(idx).getFloor() == null) {
                            taskRelSubMainList.get(idx).setFloor(taskRelSubMain.getFloor() + 1);
                            dataList.add(taskRelSubMainList.get(idx));
                        }
                    }

                }

            }
        }
        if (dataList.size() != 0) {
            for (int i = 0; i < dataList.size(); i++) {
                formatterData(dataList.get(i), taskRelSubMainList);
            }
        }
        // 排序
//        taskRelSubMainList.sort(Comparator.comparing(TaskRelSubMain::getFloor));
        return  taskRelSubMainList;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<TaskRelDTO> }
     * @return int
     */
    public int insertBatch(List<TaskRelDTO> records) {
        List<TaskRel> recordList = TaskRelMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskRelMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link TaskRelDTO }
     */
    public TaskRelDTO selectByPrimaryKey(Long id) {

        TaskRel TaskRel = TaskRelMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(TaskRel, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return TaskRelMapStruct.toDto(TaskRel);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link TaskRelDTO }
     * @return {@link List<TaskRelDTO> }
     */
    public List<TaskRelDTO> selectSelective(TaskRelDTO record) {
        TaskRel TaskRel = TaskRelMapStruct.toEntity(record);

        List<TaskRel> TaskRelList = TaskRelMapper.select(TaskRel);
        return TaskRelMapStruct.toDto(TaskRelList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link TaskRelDTO }
     * @return int
     */
    public int updateByPrimaryKey(TaskRelDTO record) {
        TaskRel TaskRel = TaskRelMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskRelMapper.updateByPrimaryKey(TaskRel);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link TaskRelDTO }
     * @return int
     */
    public int updateByPrimaryKeySelective(TaskRelDTO record) {
        TaskRel TaskRel = TaskRelMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskRelMapper.updateByPrimaryKeySelective(TaskRel);
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
        int num = TaskRelMapper.deleteByPrimaryKey(id);
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
//        return TaskRelMapper.deleteLogicByPrimaryKey(id);
//    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    public int deleteMore(List<String> ids) {
        return TaskRelMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param  {@link List<String> }
     * @return int
     */
//    public int deleteMoreLogic(List<String> ids){
//        return TaskRelMapper.deleteLogicByIds(String.join(",", ids));
//    }
    public List<MenuTreeDto> selectByGroup() {

        List<MainTaskType> mainTaskTypeList = getMainTask();
        List<MenuTreeDto> menuTreeDtos = new ArrayList<>();
        int sizw = mainTaskTypeList.size();

        for (int i = 0; i < sizw; i++) {
            MainTaskType mainTaskType = mainTaskTypeList.get(i);
            MenuTreeDto firstParentMenuTemp = new MenuTreeDto();
            firstParentMenuTemp.setId(mainTaskType.getId().intValue());
            firstParentMenuTemp.setLabel("main");
            firstParentMenuTemp.setType("");
            firstParentMenuTemp.setTitle(mainTaskType.getMainTaskTypeName());
            firstParentMenuTemp.setValue(mainTaskType.getMainTaskTypeCode());
            firstParentMenuTemp.setTemplCode("");

            List<TaskRel> taskRelList = TaskRelMapper.selectByMainTaskType(mainTaskType.getMainTaskTypeCode());
            int siez = taskRelList.size();
            for (int j = 0; j < siez; j++) {
                TaskRel taskRel = taskRelList.get(j);
                MenuTreeDto secondParentMenuTemp = new MenuTreeDto();
                secondParentMenuTemp.setId(taskRel.getId().intValue());
                secondParentMenuTemp.setTemplCode(taskRel.getTemplCode());
                secondParentMenuTemp.setValue(taskRel.getSubTaskTypeCode());
                secondParentMenuTemp.setTitle(taskRel.getTemplName());
                secondParentMenuTemp.setType(taskRel.getMainTaskTypeCode());
                secondParentMenuTemp.setLabel("sub");
                secondParentMenuTemp.setChildren(null);
                firstParentMenuTemp.getChildren().add(secondParentMenuTemp);
            }
            menuTreeDtos.add(firstParentMenuTemp);
        }

        return menuTreeDtos;
    }

    // 获取主任务
    public List<MainTaskType> getMainTask() {

        List<MainTaskType> mainTaskTypeList = mainTaskTypeMapper.selectAll();

        return mainTaskTypeList;
    }

    public List<TaskRel> selectDataByTaskCode(TaskModal taskData) {
        List<TaskRel> taskRelList = null;
        if (taskData.getTaskType().equals("main")) {
            taskRelList = TaskRelMapper.selectByMainCode(taskData.getSearchMainCode());
        } else if (taskData.getTaskType().equals("sub")) {
            taskRelList = TaskRelMapper.selectBySubCode(taskData);
        }
        return taskRelList;
    }

    public List<TaskRelSubMain> selectSubTaskByMainCode(TaskRel taskRel) {
        List<TaskRel> taskRelList = TaskRelMapper.selectByMainCode(taskRel.getMainTaskTypeCode());
        if (taskRelList.size() == 0) {
            return null;
        }
        List<TaskRelSubMain> subTaskTypList = TaskRelMapper.selectSubMainByMainCode(taskRel.getMainTaskTypeCode());
        return subTaskTypList;

    }

    public List<TaskRel> selectByMainCode(TaskRel taskRel) {
        List<TaskRel> taskRelList = TaskRelMapper.selectByMainCode(taskRel.getMainTaskTypeCode());

        return taskRelList;
    }

    /**
     * 生成或更新任务模板
     */
    public int insertTaskModal(List<TaskRelSubMain> taskRelSubMains) {

        taskRelSubMains.forEach(item -> {
            TaskRelDTO taskRel = new TaskRelDTO();
            TaskRelCondition taskRelCondition = new TaskRelCondition();

            taskRel.setMainTaskTypeCode(item.getMainTaskTypeCode());
            taskRel.setSubTaskTypeCode(item.getSubTaskTypeCode());
            taskRel.setSubTaskSeq(item.getSubTaskSeq());
//            taskRelCondition.setMainTaskTypeCode(item.getMainTaskTypeCode());
//            taskRelCondition.setSubTaskTypeCode(item.getSubTaskTypeCode());
//            taskRelCondition.setSubTaskSeq(item.getSubTaskSeq());

            if (item.getDeleteFlag()) {
                TaskRelMapper.deleteByTemplCode(item.getId());
                TaskRelConditionMapper.deleteByTemplCode(item.getTemplCode());
            } else {
                if (StringUtils.isEmpty(item.getTemplCode())) {
                    // 插入
                    String templCode = item.getMainTaskTypeCode().substring(0, 3) + "_" + item.getSubTaskTypeCode() + "_" + item.getSubTaskSeq();
                    taskRel.setTemplCode(templCode);
                    taskRel.setCreateDate(new Date());
//                    taskRelCondition.setTemplCode(templCode);
                    taskRel.setMainTaskSeq(1);
                    insert(taskRel);
//                    TaskRelConditionMapper.insert(taskRelCondition);
                } else {
                    // 根据templCode更新
                    taskRel.setTemplCode(item.getTemplCode());
//                    taskRelCondition.setTemplCode(item.getTemplCode());
                    TaskRelMapper.updateTaskByTemplCode(taskRel);
//                    TaskRelConditionMapper.updateByTemplCode(taskRelCondition);
                }
            }
        });
        return 1;
    }

    public int updateRelAndConditionDate(RelAndConditionDTO recode) {
        TaskRelDTO taskRel = recode.getTaskRel();
        List<TaskRelConditionDTO>  taskRelConditionDTO = recode.getTaskRelCondition();
        int num = updateByPrimaryKey(taskRel);
        taskRelConditionDTO.forEach(item -> {
            TaskRelCondition taskRelCondition = taskRelConditionsMapStruct.toEntity(item);
            taskRelCondition.setCreateDate(new Date());
            if (org.springframework.util.StringUtils.isEmpty(taskRelCondition.getId())) {
                int insertNum = TaskRelConditionMapper.insert(taskRelCondition);
            } else {
                // 不为空就更新
                int updateNum = TaskRelConditionMapper.updateByPrimaryKey(taskRelCondition);
            }
        });
        return num;
    }

    /**
     * 查询非本身子任务的任务模板信息
     */
    public List<TaskRelSubMain> selectSubTaskTypeByCode(TaskRel taskRel) {
        List<TaskRelSubMain> taskRelList = TaskRelMapper.selectSubTaskTypeByCode(taskRel);
        return taskRelList;
    }

    /**
     * 通过任务模板编号查询信息
     */
    public TaskRel selectDataByTemplCode(TaskRel templCode) {
        TaskRel taskRel = TaskRelMapper.selectDataByTemplCode(templCode);
        return taskRel;
    }
/**
 * 通过templCode删除rel数据 同时通过templCode删除relCondition中相对的数据
 */
    public int deleteByTemplCodes(List<TaskRel> taskRelList) {
        List<String> templCodeList = new ArrayList<String>();
        taskRelList.forEach(item -> {
            templCodeList.add(item.getTemplCode());
        });
        int rellNum = TaskRelMapper.deleteByTemplCodes(templCodeList);
        int conNum = TaskRelConditionMapper.deleteByTemplCodes(templCodeList);
        return rellNum;
    }
    /**
     * 前端模板复制
     */
    public Result copyTemplByMainCode(TaskRel taskRel) {

        // 1. 先根据mainCode查询所有的template
        List<TaskRel> templateList = TaskRelMapper.selectByMainTaskType(taskRel.getMainTaskTypeCode());
        // 2. 获取全部的action数据
        List<TaskRelAction> taskRelActionList = taskRelActionMapper.selectAll();
        // 3. 获取全部的relCondition数据
        List<TaskRelCondition> taskRelConditionList = TaskRelConditionMapper.selectAll();
        // 4. 遍历templateList数据获取相应的action喝condition数据
        List<TaskRelAction> taskRelActions = new ArrayList<>();
        List<TaskRelCondition> taskRelConditions = new ArrayList<>();
        templateList.forEach(item -> {
            // 遍历action数据
            taskRelActionList.forEach(action -> {
                if (action.getTemplCode().equals(item.getTemplCode())) {
                    action.setTemplCode("copy" + action.getTemplCode());
                    taskRelActions.add(action);
                }
            });
            taskRelConditionList.forEach(condition -> {
                if (condition.getTemplCode().equals(item.getTemplCode())) {
                    condition.setTemplCode("copy" + condition.getTemplCode());
                    taskRelConditions.add(condition);
                }
            });
            item.setTemplCode("copy" + item.getTemplCode());
            item.setMainTaskTypeCode(taskRel.getMainTaskTypeCode() + "_copy");
        });
        // 复制主任务
        MainTaskType mainTaskTypeCopy = new MainTaskType();
        MainTaskType mainTaskType = mainTaskTypeMapper.selectByMainTaskTypeCode(taskRel.getMainTaskTypeCode());
        mainTaskTypeCopy.setMainTaskTypeCode(taskRel.getMainTaskTypeCode() + "_copy");
        mainTaskTypeCopy.setCreateDate(new Date());
        mainTaskTypeCopy.setIsDefault(mainTaskType.getIsDefault());
        mainTaskTypeCopy.setDecompNum(mainTaskType.getDecompNum());
        mainTaskTypeCopy.setExecBean(mainTaskType.getExecBean());
        mainTaskTypeCopy.setIntervalTime(mainTaskType.getIntervalTime());
        mainTaskTypeCopy.setLoopExec(mainTaskType.getLoopExec());
        mainTaskTypeCopy.setPriority(mainTaskType.getPriority());
        mainTaskTypeCopy.setMultiTargetTask(mainTaskType.getMultiTargetTask());
        mainTaskTypeCopy.setMainTaskTypeName("Copy" + mainTaskType.getMainTaskTypeName());
        mainTaskTypeCopy.setRelViceTaskTyp(mainTaskType.getRelViceTaskTyp());
        mainTaskTypeCopy.setRemark(mainTaskType.getRemark());
        // 将各自数据存到表中
        if (taskRelActions.size() != 0) {
            taskRelActionMapper.insertList(taskRelActions);
        }
        if (taskRelConditions.size() != 0) {
            TaskRelConditionMapper.insertList(taskRelConditions);
        }
        mainTaskTypeMapper.insert(mainTaskTypeCopy);
        TaskRelMapper.insertList(templateList);

        return new Result();
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<TaskRelDTO> }
     */
    public GridReturnData<TaskRelDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<TaskRelDTO> mGridReturnData = new GridReturnData<>();
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

        List<TaskRel> list = TaskRelMapper.selectPage(map);

        PageInfo<TaskRel> pageInfo = new PageInfo<>(list);
        PageInfo<TaskRelDTO> pageInfoFinal = new PageInfo<>(TaskRelMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
