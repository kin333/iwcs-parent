package com.wisdom.iwcs.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BasePodType;
import com.wisdom.iwcs.domain.base.dto.BasePodTypeDTO;
import com.wisdom.iwcs.mapper.base.BasePodTypeMapper;
import com.wisdom.iwcs.mapstruct.base.BasePodTypeMapStruct;
import com.wisdom.iwcs.service.base.IBasePodTypeService;
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
public class BasePodTypeService implements IBasePodTypeService {
    private final Logger logger = LoggerFactory.getLogger(BasePodTypeService.class);

    private final BasePodTypeMapper basePodTypeMapper;

    private final BasePodTypeMapStruct basePodTypeMapStruct;

    @Autowired
    public BasePodTypeService(BasePodTypeMapStruct basePodTypeMapStruct, BasePodTypeMapper basePodTypeMapper) {
        this.basePodTypeMapStruct = basePodTypeMapStruct;
        this.basePodTypeMapper = basePodTypeMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BasePodTypeDTO }
     * @return int
     */
    @Override
    public int insert(BasePodTypeDTO record) {
        BasePodType basePodType = basePodTypeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodType.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        basePodType.setCreatedTime(new Date());
        basePodType.setCreatedBy(userId);
        basePodType.setLastModifiedBy(userId);
        basePodType.setLastModifiedTime(new Date());

        int num = basePodTypeMapper.insert(basePodType);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BasePodTypeDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BasePodTypeDTO> records) {
        List<BasePodType> recordList = basePodTypeMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = basePodTypeMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BasePodTypeDTO }
     */
    @Override
    public BasePodTypeDTO selectByPrimaryKey(Integer id) {

        BasePodType basePodType = basePodTypeMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(basePodType, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return basePodTypeMapStruct.toDto(basePodType);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BasePodTypeDTO }
     * @return {@link List<BasePodTypeDTO> }
     */
    @Override
    public List<BasePodTypeDTO> selectSelective(BasePodTypeDTO record) {
        BasePodType basePodType = basePodTypeMapStruct.toEntity(record);

        List<BasePodType> basePodTypeList = basePodTypeMapper.select(basePodType);
        return basePodTypeMapStruct.toDto(basePodTypeList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BasePodTypeDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BasePodTypeDTO record) {
        BasePodType basePodType = basePodTypeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodType.setLastModifiedBy(userId);
        basePodType.setLastModifiedTime(new Date());

        int num = basePodTypeMapper.updateByPrimaryKey(basePodType);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BasePodTypeDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BasePodTypeDTO record) {
        BasePodType basePodType = basePodTypeMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        basePodType.setLastModifiedBy(userId);
        basePodType.setLastModifiedTime(new Date());

        int num = basePodTypeMapper.updateByPrimaryKeySelective(basePodType);
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
        int num = basePodTypeMapper.deleteByPrimaryKey(id);
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
        return basePodTypeMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return basePodTypeMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return basePodTypeMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BasePodTypeDTO> }
     */
    @Override
    public GridReturnData<BasePodTypeDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BasePodTypeDTO> mGridReturnData = new GridReturnData<>();
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

        List<BasePodType> list = basePodTypeMapper.selectPage(map);

        PageInfo<BasePodType> pageInfo = new PageInfo<>(list);
        PageInfo<BasePodTypeDTO> pageInfoFinal = new PageInfo<>(basePodTypeMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
