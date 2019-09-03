package com.wisdom.iwcs.service.task.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.SubTaskTyp;
import com.wisdom.iwcs.domain.task.TaskModal;
import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.domain.task.dto.TaskRelDTO;
import com.wisdom.iwcs.mapper.task.SubTaskTypMapper;
import com.wisdom.iwcs.mapper.task.TaskRelMapper;
import com.wisdom.iwcs.mapstruct.task.TaskRelMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskRelService {
    private final Logger logger = LoggerFactory.getLogger(TaskRelService.class);

    private final TaskRelMapper TaskRelMapper;

    private final TaskRelMapStruct TaskRelMapStruct;

    private final SubTaskTypMapper SubTaskTypMapper;

    @Autowired
    public TaskRelService(TaskRelMapStruct TaskRelMapStruct, TaskRelMapper TaskRelMapper, SubTaskTypMapper SubTaskTypMapper) {
        this.TaskRelMapStruct = TaskRelMapStruct;
        this.TaskRelMapper = TaskRelMapper;
        this.SubTaskTypMapper = SubTaskTypMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TaskRelDTO }
     *
     * @return int
     */
    public int insert(TaskRelDTO record) {
        TaskRel TaskRel = TaskRelMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskRelMapper.insert(TaskRel);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<TaskRelDTO> }
     *
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
     *
     * @param id {@link Integer }
     *
     * @return {@link TaskRelDTO }
     */
    public TaskRelDTO selectByPrimaryKey(Integer id) {

        TaskRel TaskRel = TaskRelMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(TaskRel, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return TaskRelMapStruct.toDto(TaskRel);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TaskRelDTO }
     *
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
     *
     * @param record {@link TaskRelDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(TaskRelDTO record) {
        TaskRel TaskRel = TaskRelMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskRelMapper.updateByPrimaryKey(TaskRel);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TaskRelDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(TaskRelDTO record) {
        TaskRel TaskRel = TaskRelMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskRelMapper.updateByPrimaryKeySelective(TaskRel);
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
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    public int deleteMore(List<String> ids){
        return TaskRelMapper.deleteByIds(String.join(",", ids));
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
//        return TaskRelMapper.deleteLogicByIds(String.join(",", ids));
//    }


    public List<TaskRel> selectByGroup() {
        return TaskRelMapper.selectPageByGroup();
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

    public List<SubTaskTyp> selectSubTaskByMainCode(TaskRel taskRel) {

        List<String> subTaskCode = new ArrayList<String>();
        List<TaskRel> taskRelList = TaskRelMapper.selectByMainCode(taskRel.getMainTaskTypeCode());
        if (taskRelList.size() == 0) {
            return null;
        }
        for (TaskRel item : taskRelList) {
            subTaskCode.add(item.getSubTaskTypeCode());
        }
        List<SubTaskTyp> subTaskTypList = SubTaskTypMapper.selectByMainCode(subTaskCode);
        return subTaskTypList;
    }

    /**
     * 通过任务模板编号查询信息
     */
    public TaskRel selectDataByTemplCode(TaskRel templCode){
        TaskRel taskRel = TaskRelMapper.selectDataByTemplCode(templCode);
        return taskRel;
    }
    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<TaskRelDTO> }
     */
    public GridReturnData<TaskRelDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TaskRelDTO> mGridReturnData = new GridReturnData<>();
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

        List<TaskRel> list = TaskRelMapper.selectPage(map);

        PageInfo<TaskRel> pageInfo = new PageInfo<>(list);
        PageInfo<TaskRelDTO> pageInfoFinal = new PageInfo<>(TaskRelMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
