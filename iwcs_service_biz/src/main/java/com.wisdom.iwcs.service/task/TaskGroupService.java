package com.wisdom.iwcs.service.task;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.TaskGroup;
import com.wisdom.iwcs.domain.task.dto.TaskGroupDTO;
import com.wisdom.iwcs.mapper.task.TaskGroupMapper;
import com.wisdom.iwcs.mapstruct.task.TaskGroupMapStruct;
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

@Service
@Transactional(rollbackFor = Exception.class)
public class TaskGroupService {
    private final Logger logger = LoggerFactory.getLogger(TaskGroupService.class);

    private final TaskGroupMapper TaskGroupMapper;

    private final TaskGroupMapStruct TaskGroupMapStruct;

    @Autowired
    public TaskGroupService(TaskGroupMapStruct TaskGroupMapStruct, TaskGroupMapper TaskGroupMapper) {
        this.TaskGroupMapStruct = TaskGroupMapStruct;
        this.TaskGroupMapper = TaskGroupMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TaskGroupDTO }
     *
     * @return int
     */
    public int insert(TaskGroupDTO record) {
        TaskGroup TaskGroup = TaskGroupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskGroupMapper.insert(TaskGroup);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<TaskGroupDTO> }
     *
     * @return int
     */
    public int insertBatch(List<TaskGroupDTO> records) {
        List<TaskGroup> recordList = TaskGroupMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskGroupMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link TaskGroupDTO }
     */
    public TaskGroupDTO selectByPrimaryKey(Integer id) {

        TaskGroup TaskGroup = TaskGroupMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(TaskGroup, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return TaskGroupMapStruct.toDto(TaskGroup);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TaskGroupDTO }
     *
     * @return {@link List<TaskGroupDTO> }
     */
    public List<TaskGroupDTO> selectSelective(TaskGroupDTO record) {
        TaskGroup TaskGroup = TaskGroupMapStruct.toEntity(record);

        List<TaskGroup> TaskGroupList = TaskGroupMapper.select(TaskGroup);
        return TaskGroupMapStruct.toDto(TaskGroupList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TaskGroupDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(TaskGroupDTO record) {
        TaskGroup TaskGroup = TaskGroupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskGroupMapper.updateByPrimaryKey(TaskGroup);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TaskGroupDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(TaskGroupDTO record) {
        TaskGroup TaskGroup = TaskGroupMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskGroupMapper.updateByPrimaryKeySelective(TaskGroup);
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
        int num = TaskGroupMapper.deleteByPrimaryKey(id);
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
//        return TaskGroupMapper.deleteLogicByPrimaryKey(id);
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
        return TaskGroupMapper.deleteByIds(String.join(",", ids));
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
//        return TaskGroupMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<TaskGroupDTO> }
     */
    public GridReturnData<TaskGroupDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TaskGroupDTO> mGridReturnData = new GridReturnData<>();
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

        List<TaskGroup> list = TaskGroupMapper.selectPage(map);

        PageInfo<TaskGroup> pageInfo = new PageInfo<>(list);
        PageInfo<TaskGroupDTO> pageInfoFinal = new PageInfo<>(TaskGroupMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
