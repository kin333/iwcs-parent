package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.domain.task.BaseCtnrType;
import com.wisdom.iwcs.domain.task.dto.BaseCtnrTypeDTO;
import com.wisdom.iwcs.mapper.task.BaseCtnrTypeMapper;
import com.wisdom.iwcs.mapstruct.task.BaseCtnrTypeMapStruct;
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
public class BaseCtnrTypeService {
    private final Logger logger = LoggerFactory.getLogger(BaseCtnrTypeService.class);

    private final BaseCtnrTypeMapper baseCtnrTypeMapper;

    private final BaseCtnrTypeMapStruct baseCtnrTypeMapStruct;

    @Autowired
    public BaseCtnrTypeService(BaseCtnrTypeMapStruct baseCtnrTypeMapStruct, BaseCtnrTypeMapper baseCtnrTypeMapper) {
        this.baseCtnrTypeMapStruct = baseCtnrTypeMapStruct;
        this.baseCtnrTypeMapper = baseCtnrTypeMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link BaseCtnrTypeDTO }
     *
     * @return int
     */
    public int insert(BaseCtnrTypeDTO record) {
        BaseCtnrType baseCtnrType = baseCtnrTypeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseCtnrType.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        baseCtnrType.setCreatedTime(new Date());
        baseCtnrType.setCreatedBy(userId);
        baseCtnrType.setLastModifiedBy(userId);
        baseCtnrType.setLastModifiedTime(new Date());

        int num = baseCtnrTypeMapper.insert(baseCtnrType);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<BaseCtnrTypeDTO> }
     *
     * @return int
     */
    public int insertBatch(List<BaseCtnrTypeDTO> records) {
        List<BaseCtnrType> recordList = baseCtnrTypeMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseCtnrTypeMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link BaseCtnrTypeDTO }
     */
    public BaseCtnrTypeDTO selectByPrimaryKey(Integer id) {

        BaseCtnrType baseCtnrType = baseCtnrTypeMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseCtnrType, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseCtnrTypeMapStruct.toDto(baseCtnrType);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link BaseCtnrTypeDTO }
     *
     * @return {@link List<BaseCtnrTypeDTO> }
     */
    public List<BaseCtnrTypeDTO> selectSelective(BaseCtnrTypeDTO record) {
        BaseCtnrType baseCtnrType = baseCtnrTypeMapStruct.toEntity(record);

        List<BaseCtnrType> baseCtnrTypeList = baseCtnrTypeMapper.select(baseCtnrType);
        return baseCtnrTypeMapStruct.toDto(baseCtnrTypeList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link BaseCtnrTypeDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(BaseCtnrTypeDTO record) {
        BaseCtnrType baseCtnrType = baseCtnrTypeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseCtnrType.setLastModifiedBy(userId);
        baseCtnrType.setLastModifiedTime(new Date());

        int num = baseCtnrTypeMapper.updateByPrimaryKey(baseCtnrType);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link BaseCtnrTypeDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(BaseCtnrTypeDTO record) {
        BaseCtnrType baseCtnrType = baseCtnrTypeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseCtnrType.setLastModifiedBy(userId);
        baseCtnrType.setLastModifiedTime(new Date());

        int num = baseCtnrTypeMapper.updateByPrimaryKeySelective(baseCtnrType);
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
        int num = baseCtnrTypeMapper.deleteByPrimaryKey(id);
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
        return baseCtnrTypeMapper.deleteLogicByPrimaryKey(id);
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
        return baseCtnrTypeMapper.deleteByIds(String.join(",", ids));
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
        return baseCtnrTypeMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<BaseCtnrTypeDTO> }
     */
    public GridReturnData<BaseCtnrTypeDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<BaseCtnrTypeDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseCtnrType> list = baseCtnrTypeMapper.selectPage(map);

        PageInfo<BaseCtnrType> pageInfo = new PageInfo<>(list);
        PageInfo<BaseCtnrTypeDTO> pageInfoFinal = new PageInfo<>(baseCtnrTypeMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
