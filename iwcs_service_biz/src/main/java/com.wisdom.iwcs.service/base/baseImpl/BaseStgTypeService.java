package com.wisdom.iwcs.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseStgType;
import com.wisdom.iwcs.domain.base.dto.BaseStgTypeDTO;
import com.wisdom.iwcs.mapper.base.BaseStgTypeMapper;
import com.wisdom.iwcs.mapstruct.base.BaseStgTypeMapStruct;
import com.wisdom.iwcs.service.base.IBaseStgTypeService;
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
public class BaseStgTypeService implements IBaseStgTypeService {
    private final Logger logger = LoggerFactory.getLogger(BaseStgTypeService.class);

    private final BaseStgTypeMapper baseStgTypeMapper;

    private final BaseStgTypeMapStruct baseStgTypeMapStruct;

    @Autowired
    public BaseStgTypeService(BaseStgTypeMapStruct baseStgTypeMapStruct, BaseStgTypeMapper baseStgTypeMapper) {
        this.baseStgTypeMapStruct = baseStgTypeMapStruct;
        this.baseStgTypeMapper = baseStgTypeMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseStgTypeDTO }
     * @return int
     */
    @Override
    public int insert(BaseStgTypeDTO record) {
        BaseStgType baseStgType = baseStgTypeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseStgType.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        baseStgType.setCreatedTime(new Date());
        baseStgType.setCreatedBy(userId);
        baseStgType.setLastModifiedBy(userId);
        baseStgType.setLastModifiedTime(new Date());

        int num = baseStgTypeMapper.insert(baseStgType);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseStgTypeDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseStgTypeDTO> records) {
        List<BaseStgType> recordList = baseStgTypeMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseStgTypeMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseStgTypeDTO }
     */
    @Override
    public BaseStgTypeDTO selectByPrimaryKey(Integer id) {

        BaseStgType baseStgType = baseStgTypeMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseStgType, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseStgTypeMapStruct.toDto(baseStgType);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseStgTypeDTO }
     * @return {@link List<BaseStgTypeDTO> }
     */
    @Override
    public List<BaseStgTypeDTO> selectSelective(BaseStgTypeDTO record) {
        BaseStgType baseStgType = baseStgTypeMapStruct.toEntity(record);

        List<BaseStgType> baseStgTypeList = baseStgTypeMapper.select(baseStgType);
        return baseStgTypeMapStruct.toDto(baseStgTypeList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseStgTypeDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseStgTypeDTO record) {
        BaseStgType baseStgType = baseStgTypeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseStgType.setLastModifiedBy(userId);
        baseStgType.setLastModifiedTime(new Date());

        int num = baseStgTypeMapper.updateByPrimaryKey(baseStgType);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseStgTypeDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseStgTypeDTO record) {
        BaseStgType baseStgType = baseStgTypeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseStgType.setLastModifiedBy(userId);
        baseStgType.setLastModifiedTime(new Date());

        int num = baseStgTypeMapper.updateByPrimaryKeySelective(baseStgType);
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
        int num = baseStgTypeMapper.deleteByPrimaryKey(id);
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
        return baseStgTypeMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return baseStgTypeMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return baseStgTypeMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseStgTypeDTO> }
     */
    @Override
    public GridReturnData<BaseStgTypeDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseStgTypeDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseStgType> list = baseStgTypeMapper.selectPage(map);

        PageInfo<BaseStgType> pageInfo = new PageInfo<>(list);
        PageInfo<BaseStgTypeDTO> pageInfoFinal = new PageInfo<>(baseStgTypeMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
