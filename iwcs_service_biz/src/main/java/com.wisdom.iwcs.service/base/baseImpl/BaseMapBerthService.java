package com.wisdom.iwcs.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapstruct.base.BaseMapBerthMapStruct;
import com.wisdom.iwcs.service.base.IBaseMapBerthService;
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
public class BaseMapBerthService implements IBaseMapBerthService {
    private final Logger logger = LoggerFactory.getLogger(BaseMapBerthService.class);

    private final BaseMapBerthMapper baseMapBerthMapper;

    private final BaseMapBerthMapStruct baseMapBerthMapStruct;

    @Autowired
    public BaseMapBerthService(BaseMapBerthMapStruct baseMapBerthMapStruct, BaseMapBerthMapper baseMapBerthMapper) {
        this.baseMapBerthMapStruct = baseMapBerthMapStruct;
        this.baseMapBerthMapper = baseMapBerthMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseMapBerthDTO }
     * @return int
     */
    @Override
    public int insert(BaseMapBerthDTO record) {
        BaseMapBerth baseMapBerth = baseMapBerthMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMapBerth.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        baseMapBerth.setCreatedTime(new Date());
        baseMapBerth.setCreatedBy(userId);
        baseMapBerth.setLastModifiedBy(userId);
        baseMapBerth.setLastModifiedTime(new Date());

        int num = baseMapBerthMapper.insert(baseMapBerth);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseMapBerthDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseMapBerthDTO> records) {
        List<BaseMapBerth> recordList = baseMapBerthMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseMapBerthMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseMapBerthDTO }
     */
    @Override
    public BaseMapBerthDTO selectByPrimaryKey(Integer id) {

        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseMapBerth, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseMapBerthMapStruct.toDto(baseMapBerth);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseMapBerthDTO }
     * @return {@link List<BaseMapBerthDTO> }
     */
    @Override
    public List<BaseMapBerthDTO> selectSelective(BaseMapBerthDTO record) {
        BaseMapBerth baseMapBerth = baseMapBerthMapStruct.toEntity(record);

        List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.select(baseMapBerth);
        return baseMapBerthMapStruct.toDto(baseMapBerthList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseMapBerthDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseMapBerthDTO record) {
        BaseMapBerth baseMapBerth = baseMapBerthMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMapBerth.setLastModifiedBy(userId);
        baseMapBerth.setLastModifiedTime(new Date());

        int num = baseMapBerthMapper.updateByPrimaryKey(baseMapBerth);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseMapBerthDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseMapBerthDTO record) {
        BaseMapBerth baseMapBerth = baseMapBerthMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMapBerth.setLastModifiedBy(userId);
        baseMapBerth.setLastModifiedTime(new Date());

        int num = baseMapBerthMapper.updateByPrimaryKeySelective(baseMapBerth);
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
        int num = baseMapBerthMapper.deleteByPrimaryKey(id);
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
        return baseMapBerthMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return baseMapBerthMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return baseMapBerthMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseMapBerthDTO> }
     */
    @Override
    public GridReturnData<BaseMapBerthDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseMapBerthDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseMapBerth> list = baseMapBerthMapper.selectPage(map);

        PageInfo<BaseMapBerth> pageInfo = new PageInfo<>(list);
        PageInfo<BaseMapBerthDTO> pageInfoFinal = new PageInfo<>(baseMapBerthMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
