package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.MapPodFilterStrategy;
import com.wisdom.iwcs.domain.task.dto.MapPodFilterStrategyDTO;
import com.wisdom.iwcs.mapper.task.MapPodFilterStrategyMapper;
import com.wisdom.iwcs.mapstruct.task.MapPodFilterStrategyMapStruct;
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
public class MapPodFilterStrategyService {
    private final Logger logger = LoggerFactory.getLogger(MapPodFilterStrategyService.class);

    private final MapPodFilterStrategyMapper mapPodFilterStrategyMapper;

    private final MapPodFilterStrategyMapStruct mapPodFilterStrategyMapStruct;

    @Autowired
    public MapPodFilterStrategyService(MapPodFilterStrategyMapStruct mapPodFilterStrategyMapStruct, MapPodFilterStrategyMapper mapPodFilterStrategyMapper) {
        this.mapPodFilterStrategyMapStruct = mapPodFilterStrategyMapStruct;
        this.mapPodFilterStrategyMapper = mapPodFilterStrategyMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link MapPodFilterStrategyDTO }
     *
     * @return int
     */
    public int insert(MapPodFilterStrategyDTO record) {
        MapPodFilterStrategy mapPodFilterStrategy = mapPodFilterStrategyMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        mapPodFilterStrategy.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//        mapPodFilterStrategy.setCreatedTime(new Date());
//        mapPodFilterStrategy.setCreatedBy(userId);
//        mapPodFilterStrategy.setLastModifiedBy(userId);
//        mapPodFilterStrategy.setLastModifiedTime(new Date());

        int num = mapPodFilterStrategyMapper.insert(mapPodFilterStrategy);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<MapPodFilterStrategyDTO> }
     *
     * @return int
     */
    public int insertBatch(List<MapPodFilterStrategyDTO> records) {
        List<MapPodFilterStrategy> recordList = mapPodFilterStrategyMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
//        recordList.forEach(record -> {
//            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
//            record.setCreatedTime(new Date());
//        record.setCreatedBy(userId);
//        record.setLastModifiedBy(userId);
//        record.setLastModifiedTime(new Date());
//    });

        int num = mapPodFilterStrategyMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link MapPodFilterStrategyDTO }
     */
    public MapPodFilterStrategyDTO selectByPrimaryKey(Integer id) {

        MapPodFilterStrategy mapPodFilterStrategy = mapPodFilterStrategyMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(mapPodFilterStrategy, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return mapPodFilterStrategyMapStruct.toDto(mapPodFilterStrategy);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link MapPodFilterStrategyDTO }
     *
     * @return {@link List<MapPodFilterStrategyDTO> }
     */
    public List<MapPodFilterStrategyDTO> selectSelective(MapPodFilterStrategyDTO record) {
        MapPodFilterStrategy mapPodFilterStrategy = mapPodFilterStrategyMapStruct.toEntity(record);

        List<MapPodFilterStrategy> mapPodFilterStrategyList = mapPodFilterStrategyMapper.select(mapPodFilterStrategy);
        return mapPodFilterStrategyMapStruct.toDto(mapPodFilterStrategyList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link MapPodFilterStrategyDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(MapPodFilterStrategyDTO record) {
        MapPodFilterStrategy mapPodFilterStrategy = mapPodFilterStrategyMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        mapPodFilterStrategy.setLastModifiedBy(userId);
//        mapPodFilterStrategy.setLastModifiedTime(new Date());

        int num = mapPodFilterStrategyMapper.updateByPrimaryKey(mapPodFilterStrategy);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link MapPodFilterStrategyDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(MapPodFilterStrategyDTO record) {
        MapPodFilterStrategy mapPodFilterStrategy = mapPodFilterStrategyMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
//        mapPodFilterStrategy.setLastModifiedBy(userId);
//        mapPodFilterStrategy.setLastModifiedTime(new Date());

        int num = mapPodFilterStrategyMapper.updateByPrimaryKeySelective(mapPodFilterStrategy);
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
        int num = mapPodFilterStrategyMapper.deleteByPrimaryKey(id);
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
//        return mapPodFilterStrategyMapper.deleteLogicByPrimaryKey(id);
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
        return mapPodFilterStrategyMapper.deleteByIds(String.join(",", ids));
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
//        return mapPodFilterStrategyMapper.deleteLogicByIds(String.join(",", ids));
//    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<MapPodFilterStrategyDTO> }
     */
    public GridReturnData<MapPodFilterStrategyDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<MapPodFilterStrategyDTO> mGridReturnData = new GridReturnData<>();
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

        List<MapPodFilterStrategy> list = mapPodFilterStrategyMapper.selectPage(map);

        PageInfo<MapPodFilterStrategy> pageInfo = new PageInfo<>(list);
        PageInfo<MapPodFilterStrategyDTO> pageInfoFinal = new PageInfo<>(mapPodFilterStrategyMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
