package com.wisdom.iwcs.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMap;
import com.wisdom.iwcs.domain.base.dto.BaseMapDTO;
import com.wisdom.iwcs.mapper.base.BaseMapMapper;
import com.wisdom.iwcs.mapstruct.base.BaseMapMapStruct;
import com.wisdom.iwcs.service.base.IBaseMapService;
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
public class BaseMapService implements IBaseMapService {
    private final Logger logger = LoggerFactory.getLogger(BaseMapService.class);

    private final BaseMapMapper baseMapMapper;

    private final BaseMapMapStruct baseMapMapStruct;

    @Autowired
    public BaseMapService(BaseMapMapStruct baseMapMapStruct, BaseMapMapper baseMapMapper) {
        this.baseMapMapStruct = baseMapMapStruct;
        this.baseMapMapper = baseMapMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseMapDTO }
     * @return int
     */
    @Override
    public int insert(BaseMapDTO record) {
        BaseMap baseMap = baseMapMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMap.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        baseMap.setCreatedTime(new Date());
        baseMap.setCreatedBy(userId);
        baseMap.setLastModifiedBy(userId);
        baseMap.setLastModifiedTime(new Date());

        int num = baseMapMapper.insert(baseMap);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseMapDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseMapDTO> records) {
        List<BaseMap> recordList = baseMapMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseMapMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseMapDTO }
     */
    @Override
    public BaseMapDTO selectByPrimaryKey(Integer id) {

        BaseMap baseMap = baseMapMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseMap, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseMapMapStruct.toDto(baseMap);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseMapDTO }
     * @return {@link List<BaseMapDTO> }
     */
    @Override
    public List<BaseMapDTO> selectSelective(BaseMapDTO record) {
        BaseMap baseMap = baseMapMapStruct.toEntity(record);

        List<BaseMap> baseMapList = baseMapMapper.select(baseMap);
        return baseMapMapStruct.toDto(baseMapList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseMapDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseMapDTO record) {
        BaseMap baseMap = baseMapMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMap.setLastModifiedBy(userId);
        baseMap.setLastModifiedTime(new Date());

        int num = baseMapMapper.updateByPrimaryKey(baseMap);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseMapDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseMapDTO record) {
        BaseMap baseMap = baseMapMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMap.setLastModifiedBy(userId);
        baseMap.setLastModifiedTime(new Date());

        int num = baseMapMapper.updateByPrimaryKeySelective(baseMap);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    @Override
    public int deleteByPrimaryKey(Integer id) {
        int num = baseMapMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键逻辑删除记录
     *
     * @param id {@link Integer }
     * @return int
     */
    @Override
    public int deleteLogicByPrimaryKey(Integer id) {
        return baseMapMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return baseMapMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return baseMapMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseMapDTO> }
     */
    @Override
    public GridReturnData<BaseMapDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseMapDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseMap> list = baseMapMapper.selectPage(map);

        PageInfo<BaseMap> pageInfo = new PageInfo<>(list);
        PageInfo<BaseMapDTO> pageInfoFinal = new PageInfo<>(baseMapMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
