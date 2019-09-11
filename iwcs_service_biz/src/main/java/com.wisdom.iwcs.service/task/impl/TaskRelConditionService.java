package com.wisdom.iwcs.service.task.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.TaskRelCondition;
import com.wisdom.iwcs.domain.task.dto.TaskRelConditionDTO;
import com.wisdom.iwcs.mapper.task.TaskRelConditionMapper;
import com.wisdom.iwcs.mapstruct.task.TaskRelConditionsMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.task.intf.ITaskRelConditionsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskRelConditionService implements ITaskRelConditionsService {

    private final Logger logger = LoggerFactory.getLogger(TaskRelConditionService.class);

    private final TaskRelConditionMapper taskRelConditionMapper;

    private final TaskRelConditionsMapStruct taskRelConditionsMapStruct;

    @Autowired
    public TaskRelConditionService(TaskRelConditionsMapStruct taskRelConditionsMapStruct, TaskRelConditionMapper taskRelConditionMapper) {
        this.taskRelConditionsMapStruct = taskRelConditionsMapStruct;
        this.taskRelConditionMapper = taskRelConditionMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TaskRelConditionDTO }
     *
     * @return int
     */
    @Override
    public int insert(TaskRelConditionDTO record) {
        TaskRelCondition tsTaskRelCondition = taskRelConditionsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        tsTaskRelCondition.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//        tsTaskRelCondition.setCreatedTime(new Date());
//        tsTaskRelCondition.setCreatedBy(userId);
//        tsTaskRelCondition.setLastModifiedBy(userId);
//        tsTaskRelCondition.setLastModifiedTime(new Date());

        int num = taskRelConditionMapper.insert(tsTaskRelCondition);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List< TaskRelConditionDTO > }
     *
     * @return int
     */
    @Override
    public int insertBatch(List<TaskRelConditionDTO> records) {
        List<TaskRelCondition> recordList = taskRelConditionsMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
//        recordList.forEach(record -> {
//            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//            record.setCreatedTime(new Date());
//            record.setCreatedBy(userId);
//            record.setLastModifiedBy(userId);
//            record.setLastModifiedTime(new Date());
//        });

        int num = taskRelConditionMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link TaskRelConditionDTO }
     */
    @Override
    public TaskRelConditionDTO selectByPrimaryKey(Integer id) {

        TaskRelCondition tsTaskRelCondition = taskRelConditionMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(tsTaskRelCondition, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return taskRelConditionsMapStruct.toDto(tsTaskRelCondition);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TaskRelConditionDTO }
     *
     * @return {@link List< TaskRelConditionDTO > }
     */
    @Override
    public List<TaskRelConditionDTO> selectSelective(TaskRelConditionDTO record) {
        TaskRelCondition tsTaskRelCondition = taskRelConditionsMapStruct.toEntity(record);

        List<TaskRelCondition> tsTaskRelConditionList = taskRelConditionMapper.select(tsTaskRelCondition);
        return taskRelConditionsMapStruct.toDto(tsTaskRelConditionList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TaskRelConditionDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKey(TaskRelConditionDTO record) {
        TaskRelCondition tsTaskRelCondition = taskRelConditionsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        tsTaskRelCondition.setLastModifiedBy(userId);
//        tsTaskRelCondition.setLastModifiedTime(new Date());

        int num = taskRelConditionMapper.updateByPrimaryKey(tsTaskRelCondition);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TaskRelConditionDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(TaskRelConditionDTO record) {
        TaskRelCondition tsTaskRelCondition = taskRelConditionsMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        tsTaskRelCondition.setLastModifiedBy(userId);
//        tsTaskRelCondition.setLastModifiedTime(new Date());

        int num = taskRelConditionMapper.updateByPrimaryKeySelective(tsTaskRelCondition);
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
        int num = taskRelConditionMapper.deleteByPrimaryKey(id);
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
//        return taskRelConditionMapper.deleteLogicByPrimaryKey(id);
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
        return taskRelConditionMapper.deleteByIds(String.join(",", ids));
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
//        return taskRelConditionMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData< TaskRelConditionDTO > }
     */
    @Override
    public GridReturnData<TaskRelConditionDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TaskRelConditionDTO> mGridReturnData = new GridReturnData<>();
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

        List<TaskRelCondition> list = taskRelConditionMapper.selectPage(map);

        PageInfo<TaskRelCondition> pageInfo = new PageInfo<>(list);
        PageInfo<TaskRelConditionDTO> pageInfoFinal = new PageInfo<>(taskRelConditionsMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    @Override
    public List<TaskRelCondition> selectTaskConditionByTemplCode(TaskRelCondition record) {
        List<TaskRelCondition> taskRelCondition = taskRelConditionMapper.selectTaskConditionByTemplCode(record);
        return taskRelCondition;
    }

    @Override
    public int handleRelConditionData(List<TaskRelConditionDTO> record) {

        record.forEach(item -> {
            // 如果id为空就插入
            if (StringUtils.isEmpty(item.getId())) {
                insert(item);
            } else {
                // 不为空就更新
                updateByPrimaryKey(item);
            }
        });
        return 1;
    }
}
