package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.domain.task.TaskRelAction;
import com.wisdom.iwcs.domain.task.dto.TaskRelActionDTO;
import com.wisdom.iwcs.mapper.task.TaskRelActionMapper;
import com.wisdom.iwcs.mapstruct.task.TaskRelActionMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.exception.Preconditions;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskRelActionService {
    private final Logger logger = LoggerFactory.getLogger(TaskRelActionService.class);

    private final TaskRelActionMapper taskRelActionMapper;

    private final TaskRelActionMapStruct taskRelActionMapStruct;

    @Autowired
    public TaskRelActionService(TaskRelActionMapStruct taskRelActionMapStruct, TaskRelActionMapper taskRelActionMapper) {
        this.taskRelActionMapStruct = taskRelActionMapStruct;
        this.taskRelActionMapper = taskRelActionMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TaskRelActionDTO }
     *
     * @return int
     */
    public int insert(TaskRelActionDTO record) {
        TaskRelAction taskRelAction = taskRelActionMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        taskRelAction.setCreateTime(new Date());
        taskRelAction.setLastModifiedTime(new Date());

        int num = taskRelActionMapper.insert(taskRelAction);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<TaskRelActionDTO> }
     *
     * @return int
     */
    public int insertBatch(List<TaskRelActionDTO> records) {
        List<TaskRelAction> recordList = taskRelActionMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreateTime(new Date());
            record.setLastModifiedTime(new Date());
        });

        int num = taskRelActionMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link TaskRelActionDTO }
     */
    public TaskRelActionDTO selectByPrimaryKey(Integer id) {

        TaskRelAction taskRelAction = taskRelActionMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(taskRelAction, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return taskRelActionMapStruct.toDto(taskRelAction);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TaskRelActionDTO }
     *
     * @return {@link List<TaskRelActionDTO> }
     */
    public List<TaskRelActionDTO> selectSelective(TaskRelActionDTO record) {
        TaskRelAction taskRelAction = taskRelActionMapStruct.toEntity(record);

        List<TaskRelAction> taskRelActionList = taskRelActionMapper.select(taskRelAction);
        return taskRelActionMapStruct.toDto(taskRelActionList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TaskRelActionDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(TaskRelActionDTO record) {
        TaskRelAction taskRelAction = taskRelActionMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        taskRelAction.setLastModifiedTime(new Date());

        int num = taskRelActionMapper.updateByPrimaryKey(taskRelAction);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TaskRelActionDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(TaskRelActionDTO record) {
        TaskRelAction taskRelAction = taskRelActionMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        taskRelAction.setLastModifiedTime(new Date());

        int num = taskRelActionMapper.updateByPrimaryKeySelective(taskRelAction);
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
        int num = taskRelActionMapper.deleteByPrimaryKey(id);
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
//        return taskRelActionMapper.deleteLogicByPrimaryKey(id);
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
        return taskRelActionMapper.deleteByIds(String.join(",", ids));
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
//        return taskRelActionMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<TaskRelActionDTO> }
     */
    public GridReturnData<TaskRelActionDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TaskRelActionDTO> mGridReturnData = new GridReturnData<>();
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

        List<TaskRelAction> list = taskRelActionMapper.selectPage(map);

        PageInfo<TaskRelAction> pageInfo = new PageInfo<>(list);
        PageInfo<TaskRelActionDTO> pageInfoFinal = new PageInfo<>(taskRelActionMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
