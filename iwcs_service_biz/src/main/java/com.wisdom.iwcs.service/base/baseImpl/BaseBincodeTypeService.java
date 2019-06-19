package com.wisdom.iwcs.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseBincodeType;
import com.wisdom.iwcs.domain.base.dto.BaseBincodeTypeDTO;
import com.wisdom.iwcs.mapper.base.BaseBincodeTypeMapper;
import com.wisdom.iwcs.mapstruct.base.BaseBincodeTypeMapStruct;
import com.wisdom.iwcs.service.base.IBaseBincodeTypeService;
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
public class BaseBincodeTypeService implements IBaseBincodeTypeService {
    private final Logger logger = LoggerFactory.getLogger(BaseBincodeTypeService.class);

    private final BaseBincodeTypeMapper baseBincodeTypeMapper;

    private final BaseBincodeTypeMapStruct baseBincodeTypeMapStruct;

    @Autowired
    public BaseBincodeTypeService(BaseBincodeTypeMapStruct baseBincodeTypeMapStruct, BaseBincodeTypeMapper baseBincodeTypeMapper) {
        this.baseBincodeTypeMapStruct = baseBincodeTypeMapStruct;
        this.baseBincodeTypeMapper = baseBincodeTypeMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseBincodeTypeDTO }
     * @return int
     */
    @Override
    public int insert(BaseBincodeTypeDTO record) {
        BaseBincodeType baseBincodeType = baseBincodeTypeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseBincodeType.setCreatedTime(new Date());
        baseBincodeType.setCreatedBy(userId);
        baseBincodeType.setLastModifiedBy(userId);
        baseBincodeType.setLastModifiedTime(new Date());

        int num = baseBincodeTypeMapper.insert(baseBincodeType);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseBincodeTypeDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseBincodeTypeDTO> records) {
        List<BaseBincodeType> recordList = baseBincodeTypeMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseBincodeTypeMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseBincodeTypeDTO }
     */
    @Override
    public BaseBincodeTypeDTO selectByPrimaryKey(Integer id) {

        BaseBincodeType baseBincodeType = baseBincodeTypeMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseBincodeType, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseBincodeTypeMapStruct.toDto(baseBincodeType);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseBincodeTypeDTO }
     * @return {@link List<BaseBincodeTypeDTO> }
     */
    @Override
    public List<BaseBincodeTypeDTO> selectSelective(BaseBincodeTypeDTO record) {
        BaseBincodeType baseBincodeType = baseBincodeTypeMapStruct.toEntity(record);

        List<BaseBincodeType> baseBincodeTypeList = baseBincodeTypeMapper.select(baseBincodeType);
        return baseBincodeTypeMapStruct.toDto(baseBincodeTypeList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseBincodeTypeDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseBincodeTypeDTO record) {
        BaseBincodeType baseBincodeType = baseBincodeTypeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseBincodeType.setLastModifiedBy(userId);
        baseBincodeType.setLastModifiedTime(new Date());

        int num = baseBincodeTypeMapper.updateByPrimaryKey(baseBincodeType);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseBincodeTypeDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseBincodeTypeDTO record) {
        BaseBincodeType baseBincodeType = baseBincodeTypeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseBincodeType.setLastModifiedBy(userId);
        baseBincodeType.setLastModifiedTime(new Date());

        int num = baseBincodeTypeMapper.updateByPrimaryKeySelective(baseBincodeType);
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
        int num = baseBincodeTypeMapper.deleteByPrimaryKey(id);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }


    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return baseBincodeTypeMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseBincodeTypeDTO> }
     */
    @Override
    public GridReturnData<BaseBincodeTypeDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseBincodeTypeDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseBincodeType> list = baseBincodeTypeMapper.selectPage(map);

        PageInfo<BaseBincodeType> pageInfo = new PageInfo<>(list);
        PageInfo<BaseBincodeTypeDTO> pageInfoFinal = new PageInfo<>(baseBincodeTypeMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
