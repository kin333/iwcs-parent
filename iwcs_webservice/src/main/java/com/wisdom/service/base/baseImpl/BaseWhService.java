package com.wisdom.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.base.BaseWhMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseWh;
import com.wisdom.iwcs.domain.base.dto.BaseWhDTO;
import com.wisdom.iwcs.mapper.base.BaseWhMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.IBaseWhService;
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
public class BaseWhService implements IBaseWhService {
    private final Logger logger = LoggerFactory.getLogger(BaseWhService.class);

    private final BaseWhMapper baseWhMapper;

    private final BaseWhMapStruct baseWhMapStruct;

    @Autowired
    public BaseWhService(BaseWhMapStruct baseWhMapStruct, BaseWhMapper baseWhMapper) {
        this.baseWhMapStruct = baseWhMapStruct;
        this.baseWhMapper = baseWhMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseWhDTO }
     * @return int
     */
    @Override
    public int insert(BaseWhDTO record) {
        BaseWh baseWh = baseWhMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWh.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        baseWh.setCreatedTime(new Date());
        baseWh.setCreatedBy(userId);
        baseWh.setLastModifiedBy(userId);
        baseWh.setLastModifiedTime(new Date());

        int num = baseWhMapper.insert(baseWh);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseWhDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseWhDTO> records) {
        List<BaseWh> recordList = baseWhMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseWhMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseWhDTO }
     */
    @Override
    public BaseWhDTO selectByPrimaryKey(Integer id) {

        BaseWh baseWh = baseWhMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseWh, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseWhMapStruct.toDto(baseWh);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseWhDTO }
     * @return {@link List<BaseWhDTO> }
     */
    @Override
    public List<BaseWhDTO> selectSelective(BaseWhDTO record) {
        BaseWh baseWh = baseWhMapStruct.toEntity(record);

        List<BaseWh> baseWhList = baseWhMapper.select(baseWh);
        return baseWhMapStruct.toDto(baseWhList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseWhDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseWhDTO record) {
        BaseWh baseWh = baseWhMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWh.setLastModifiedBy(userId);
        baseWh.setLastModifiedTime(new Date());

        int num = baseWhMapper.updateByPrimaryKey(baseWh);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseWhDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseWhDTO record) {
        BaseWh baseWh = baseWhMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWh.setLastModifiedBy(userId);
        baseWh.setLastModifiedTime(new Date());

        int num = baseWhMapper.updateByPrimaryKeySelective(baseWh);
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
        int num = baseWhMapper.deleteByPrimaryKey(id);
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
        return baseWhMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return baseWhMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return baseWhMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseWhDTO> }
     */
    public GridReturnData<BaseWhDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseWhDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseWh> list = baseWhMapper.selectPage(map);

        PageInfo<BaseWh> pageInfo = new PageInfo<>(list);
        PageInfo<BaseWhDTO> pageInfoFinal = new PageInfo<>(baseWhMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
