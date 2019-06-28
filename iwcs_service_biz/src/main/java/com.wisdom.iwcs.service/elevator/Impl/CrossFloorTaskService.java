package com.wisdom.iwcs.service.elevator.Impl;

import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.elevator.CrossFloorTask;
import com.wisdom.iwcs.domain.elevator.dto.CrossFloorTaskDTO;
import com.wisdom.iwcs.mapper.elevator.CrossFloorTaskMapper;
import com.wisdom.iwcs.mapstruct.elevator.CrossFloorTaskMapStruct;
import com.wisdom.iwcs.service.elevator.ICrossFloorTaskService;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class CrossFloorTaskService implements ICrossFloorTaskService {

    private final Logger logger = LoggerFactory.getLogger(CrossFloorTaskService.class);

    private final CrossFloorTaskMapper crossFloorTaskMapper;

    private final CrossFloorTaskMapStruct crossFloorTaskMapStruct;

    @Autowired
    public CrossFloorTaskService(CrossFloorTaskMapStruct crossFloorTaskMapStruct, CrossFloorTaskMapper crossFloorTaskMapper) {
        this.crossFloorTaskMapStruct = crossFloorTaskMapStruct;
        this.crossFloorTaskMapper = crossFloorTaskMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link CrossFloorTaskDTO }
     *
     * @return int
     */
    @Override
    public int insert(CrossFloorTaskDTO record) {
        CrossFloorTask crossFloorTask = crossFloorTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        crossFloorTask.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        crossFloorTask.setCreatedTime(new Date());
//        crossFloorTask.setCreatedBy(userId);
//        crossFloorTask.setLastModifiedBy(userId);
        crossFloorTask.setLastModifiedTime(new Date());

        int num = crossFloorTaskMapper.insert(crossFloorTask);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<CrossFloorTaskDTO> }
     *
     * @return int
     */
    @Override
    public int insertBatch(List<CrossFloorTaskDTO> records) {
        List<CrossFloorTask> recordList = crossFloorTaskMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
//            record.setCreatedBy(userId);
//            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = crossFloorTaskMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link CrossFloorTaskDTO }
     */
    @Override
    public CrossFloorTaskDTO selectByPrimaryKey(Integer id) {

        CrossFloorTask crossFloorTask = crossFloorTaskMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(crossFloorTask, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return crossFloorTaskMapStruct.toDto(crossFloorTask);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link CrossFloorTaskDTO }
     *
     * @return {@link List<CrossFloorTaskDTO> }
     */
    @Override
    public List<CrossFloorTaskDTO> selectSelective(CrossFloorTaskDTO record) {
        CrossFloorTask crossFloorTask = crossFloorTaskMapStruct.toEntity(record);

        List<CrossFloorTask> crossFloorTaskList = crossFloorTaskMapper.select(crossFloorTask);
        return crossFloorTaskMapStruct.toDto(crossFloorTaskList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link CrossFloorTaskDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKey(CrossFloorTaskDTO record) {
        CrossFloorTask crossFloorTask = crossFloorTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        crossFloorTask.setLastModifiedBy(userId);
        crossFloorTask.setLastModifiedTime(new Date());

        int num = crossFloorTaskMapper.updateByPrimaryKey(crossFloorTask);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link CrossFloorTaskDTO }
     *
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(CrossFloorTaskDTO record) {
        CrossFloorTask crossFloorTask = crossFloorTaskMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        crossFloorTask.setLastModifiedBy(userId);
        crossFloorTask.setLastModifiedTime(new Date());

        int num = crossFloorTaskMapper.updateByPrimaryKeySelective(crossFloorTask);
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
        int num = crossFloorTaskMapper.deleteByPrimaryKey(id);
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
    @Override
    public int deleteLogicByPrimaryKey(Integer id) {
        return crossFloorTaskMapper.deleteLogicByPrimaryKey(id);
    }

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
        return crossFloorTaskMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids){
        return crossFloorTaskMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<CrossFloorTaskDTO> }
     */
    @Override
    public GridReturnData<CrossFloorTaskDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<CrossFloorTaskDTO> mGridReturnData = new GridReturnData<>();
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

        List<CrossFloorTask> list = crossFloorTaskMapper.selectPage(map);

        PageInfo<CrossFloorTask> pageInfo = new PageInfo<>(list);
        PageInfo<CrossFloorTaskDTO> pageInfoFinal = new PageInfo<>(crossFloorTaskMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
