package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.domain.task.ResourceHandlerStrategy;
import com.wisdom.iwcs.domain.task.dto.ResourceHandlerStrategyDTO;
import com.wisdom.iwcs.mapper.task.ResourceHandlerStrategyMapper;
import com.wisdom.iwcs.mapstruct.task.ResourceHandlerStrategyMapStruct;
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
public class ResourceHandlerStrategyService {
    private final Logger logger = LoggerFactory.getLogger(ResourceHandlerStrategyService.class);

    private final ResourceHandlerStrategyMapper resourceHandlerStrategyMapper;

    private final ResourceHandlerStrategyMapStruct resourceHandlerStrategyMapStruct;

    @Autowired
    public ResourceHandlerStrategyService(ResourceHandlerStrategyMapStruct resourceHandlerStrategyMapStruct, ResourceHandlerStrategyMapper resourceHandlerStrategyMapper) {
        this.resourceHandlerStrategyMapStruct = resourceHandlerStrategyMapStruct;
        this.resourceHandlerStrategyMapper = resourceHandlerStrategyMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link ResourceHandlerStrategyDTO }
     *
     * @return int
     */
    public int insert(ResourceHandlerStrategyDTO record) {
        ResourceHandlerStrategy resourceHandlerStrategy = resourceHandlerStrategyMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        resourceHandlerStrategy.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        resourceHandlerStrategy.setCreateTime(new Date());
        resourceHandlerStrategy.setLastModifiedTime(new Date());

        int num = resourceHandlerStrategyMapper.insert(resourceHandlerStrategy);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<ResourceHandlerStrategyDTO> }
     *
     * @return int
     */
    public int insertBatch(List<ResourceHandlerStrategyDTO> records) {
        List<ResourceHandlerStrategy> recordList = resourceHandlerStrategyMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreateTime(new Date());
            record.setLastModifiedTime(new Date());
        });

        int num = resourceHandlerStrategyMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link ResourceHandlerStrategyDTO }
     */
    public ResourceHandlerStrategyDTO selectByPrimaryKey(Integer id) {

        ResourceHandlerStrategy resourceHandlerStrategy = resourceHandlerStrategyMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(resourceHandlerStrategy, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return resourceHandlerStrategyMapStruct.toDto(resourceHandlerStrategy);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link ResourceHandlerStrategyDTO }
     *
     * @return {@link List<ResourceHandlerStrategyDTO> }
     */
    public List<ResourceHandlerStrategyDTO> selectSelective(ResourceHandlerStrategyDTO record) {
        ResourceHandlerStrategy resourceHandlerStrategy = resourceHandlerStrategyMapStruct.toEntity(record);

        List<ResourceHandlerStrategy> resourceHandlerStrategyList = resourceHandlerStrategyMapper.select(resourceHandlerStrategy);
        return resourceHandlerStrategyMapStruct.toDto(resourceHandlerStrategyList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link ResourceHandlerStrategyDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(ResourceHandlerStrategyDTO record) {
        ResourceHandlerStrategy resourceHandlerStrategy = resourceHandlerStrategyMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        resourceHandlerStrategy.setLastModifiedTime(new Date());

        int num = resourceHandlerStrategyMapper.updateByPrimaryKey(resourceHandlerStrategy);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link ResourceHandlerStrategyDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(ResourceHandlerStrategyDTO record) {
        ResourceHandlerStrategy resourceHandlerStrategy = resourceHandlerStrategyMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        resourceHandlerStrategy.setLastModifiedTime(new Date());

        int num = resourceHandlerStrategyMapper.updateByPrimaryKeySelective(resourceHandlerStrategy);
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
        int num = resourceHandlerStrategyMapper.deleteByPrimaryKey(id);
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
    public int deleteLogicByPrimaryKey(Integer id) {
        return resourceHandlerStrategyMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    public int deleteMore(List<String> ids){
        return resourceHandlerStrategyMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return int
     */
    public int deleteMoreLogic(List<String> ids){
        return resourceHandlerStrategyMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<ResourceHandlerStrategyDTO> }
     */
    public GridReturnData<ResourceHandlerStrategyDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<ResourceHandlerStrategyDTO> mGridReturnData = new GridReturnData<>();
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

        List<ResourceHandlerStrategy> list = resourceHandlerStrategyMapper.selectPage(map);

        PageInfo<ResourceHandlerStrategy> pageInfo = new PageInfo<>(list);
        PageInfo<ResourceHandlerStrategyDTO> pageInfoFinal = new PageInfo<>(resourceHandlerStrategyMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
