package com.wisdom.iwcs.service.task.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.TaskGroupItem;
import com.wisdom.iwcs.domain.task.dto.TaskGroupItemDTO;
import com.wisdom.iwcs.mapper.task.TaskGroupItemMapper;
import com.wisdom.iwcs.mapstruct.task.TaskGroupItemMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
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
public class TaskGroupItemService {
    private final Logger logger = LoggerFactory.getLogger(TaskGroupItemService.class);

    private final TaskGroupItemMapper TaskGroupItemMapper;

    private final TaskGroupItemMapStruct TaskGroupItemMapStruct;

    @Autowired
    public TaskGroupItemService(TaskGroupItemMapStruct TaskGroupItemMapStruct, TaskGroupItemMapper TaskGroupItemMapper) {
        this.TaskGroupItemMapStruct = TaskGroupItemMapStruct;
        this.TaskGroupItemMapper = TaskGroupItemMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link TaskGroupItemDTO }
     *
     * @return int
     */
    public int insert(TaskGroupItemDTO record) {
        TaskGroupItem TaskGroupItem = TaskGroupItemMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskGroupItemMapper.insert(TaskGroupItem);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<TaskGroupItemDTO> }
     *
     * @return int
     */
    public int insertBatch(List<TaskGroupItemDTO> records) {
        List<TaskGroupItem> recordList = TaskGroupItemMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskGroupItemMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link TaskGroupItemDTO }
     */
    public TaskGroupItemDTO selectByPrimaryKey(Integer id) {

        TaskGroupItem TaskGroupItem = TaskGroupItemMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(TaskGroupItem, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return TaskGroupItemMapStruct.toDto(TaskGroupItem);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link TaskGroupItemDTO }
     *
     * @return {@link List<TaskGroupItemDTO> }
     */
    public List<TaskGroupItemDTO> selectSelective(TaskGroupItemDTO record) {
        TaskGroupItem TaskGroupItem = TaskGroupItemMapStruct.toEntity(record);

        List<TaskGroupItem> TaskGroupItemList = TaskGroupItemMapper.select(TaskGroupItem);
        return TaskGroupItemMapStruct.toDto(TaskGroupItemList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link TaskGroupItemDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(TaskGroupItemDTO record) {
        TaskGroupItem TaskGroupItem = TaskGroupItemMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskGroupItemMapper.updateByPrimaryKey(TaskGroupItem);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link TaskGroupItemDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(TaskGroupItemDTO record) {
        TaskGroupItem TaskGroupItem = TaskGroupItemMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();

        int num = TaskGroupItemMapper.updateByPrimaryKeySelective(TaskGroupItem);
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
        int num = TaskGroupItemMapper.deleteByPrimaryKey(id);
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
//        return TaskGroupItemMapper.deleteLogicByPrimaryKey(id);
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
        return TaskGroupItemMapper.deleteByIds(String.join(",", ids));
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
//        return TaskGroupItemMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<TaskGroupItemDTO> }
     */
    public GridReturnData<TaskGroupItemDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<TaskGroupItemDTO> mGridReturnData = new GridReturnData<>();
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

        List<TaskGroupItem> list = TaskGroupItemMapper.selectPage(map);

        PageInfo<TaskGroupItem> pageInfo = new PageInfo<>(list);
        PageInfo<TaskGroupItemDTO> pageInfoFinal = new PageInfo<>(TaskGroupItemMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
