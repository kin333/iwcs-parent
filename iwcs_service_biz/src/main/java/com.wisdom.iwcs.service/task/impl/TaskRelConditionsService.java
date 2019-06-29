package com.wisdom.iwcs.service.task.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.TaskRelConditions;
import com.wisdom.iwcs.domain.task.dto.TaskRelConditionsDTO;
import com.wisdom.iwcs.mapper.task.TaskRelConditionsMapper;
import com.wisdom.iwcs.mapstruct.task.TaskRelConditionsMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.intf.ITaskRelConditionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskRelConditionsService implements ITaskRelConditionsService {

    private final Logger logger = LoggerFactory.getLogger(TaskRelConditionsService.class);

    private final TaskRelConditionsMapper taskRelConditionsMapper;

    private final TaskRelConditionsMapStruct taskRelConditionsMapStruct;

    @Autowired
    public TaskRelConditionsService(TaskRelConditionsMapStruct taskRelConditionsMapStruct, TaskRelConditionsMapper taskRelConditionsMapper) {
        this.taskRelConditionsMapStruct = taskRelConditionsMapStruct;
        this.taskRelConditionsMapper = taskRelConditionsMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TaskRelConditionsDTO }
     *
     * @return int
     */
    @Override
    public int insert(TaskRelConditionsDTO record) {
        TaskRelConditions tsTaskRelConditions = taskRelConditionsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        tsTaskRelConditions.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//        tsTaskRelConditions.setCreatedTime(new Date());
//        tsTaskRelConditions.setCreatedBy(userId);
//        tsTaskRelConditions.setLastModifiedBy(userId);
//        tsTaskRelConditions.setLastModifiedTime(new Date());

        int num = taskRelConditionsMapper.insert(tsTaskRelConditions);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<TaskRelConditionsDTO> }
     *
     * @return int
     */
    @Override
    public int insertBatch(List<TaskRelConditionsDTO> records) {
        List<TaskRelConditions> recordList = taskRelConditionsMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
//        recordList.forEach(record -> {
//            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//            record.setCreatedTime(new Date());
//            record.setCreatedBy(userId);
//            record.setLastModifiedBy(userId);
//            record.setLastModifiedTime(new Date());
//        });

        int num = taskRelConditionsMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link TaskRelConditionsDTO }
     */
    @Override
    public TaskRelConditionsDTO selectByPrimaryKey(Integer id) {

        TaskRelConditions tsTaskRelConditions = taskRelConditionsMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(tsTaskRelConditions, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return taskRelConditionsMapStruct.toDto(tsTaskRelConditions);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TaskRelConditionsDTO }
     *
     * @return {@link List<TaskRelConditionsDTO> }
     */
    @Override
    public List<TaskRelConditionsDTO> selectSelective(TaskRelConditionsDTO record) {
        TaskRelConditions tsTaskRelConditions = taskRelConditionsMapStruct.toEntity(record);

        List<TaskRelConditions> tsTaskRelConditionsList = taskRelConditionsMapper.select(tsTaskRelConditions);
        return taskRelConditionsMapStruct.toDto(tsTaskRelConditionsList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TaskRelConditionsDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKey(TaskRelConditionsDTO record) {
        TaskRelConditions tsTaskRelConditions = taskRelConditionsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        tsTaskRelConditions.setLastModifiedBy(userId);
//        tsTaskRelConditions.setLastModifiedTime(new Date());

        int num = taskRelConditionsMapper.updateByPrimaryKey(tsTaskRelConditions);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TaskRelConditionsDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(TaskRelConditionsDTO record) {
        TaskRelConditions tsTaskRelConditions = taskRelConditionsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        tsTaskRelConditions.setLastModifiedBy(userId);
//        tsTaskRelConditions.setLastModifiedTime(new Date());

        int num = taskRelConditionsMapper.updateByPrimaryKeySelective(tsTaskRelConditions);
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
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int num = taskRelConditionsMapper.deleteByPrimaryKey(id);
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
//        return taskRelConditionsMapper.deleteLogicByPrimaryKey(id);
//    }

    /**
     * 根据主键删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids){
        return taskRelConditionsMapper.deleteByIds(String.join(",", ids));
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
//        return taskRelConditionsMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<TaskRelConditionsDTO> }
     */
    @Override
    public GridReturnData<TaskRelConditionsDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TaskRelConditionsDTO> mGridReturnData = new GridReturnData<>();
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

        List<TaskRelConditions> list = taskRelConditionsMapper.selectPage(map);

        PageInfo<TaskRelConditions> pageInfo = new PageInfo<>(list);
        PageInfo<TaskRelConditionsDTO> pageInfoFinal = new PageInfo<>(taskRelConditionsMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
