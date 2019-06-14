package com.wisdom.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.base.BaseMapSectionMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMapSection;
import com.wisdom.iwcs.domain.base.dto.BaseMapSectionDTO;
import com.wisdom.iwcs.mapper.base.BaseMapSectionMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.IBaseMapSectionService;
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
public class BaseMapSectionService implements IBaseMapSectionService {
    private final Logger logger = LoggerFactory.getLogger(BaseMapSectionService.class);

    private final BaseMapSectionMapper baseMapSectionMapper;

    private final BaseMapSectionMapStruct baseMapSectionMapStruct;

    @Autowired
    public BaseMapSectionService(BaseMapSectionMapStruct baseMapSectionMapStruct, BaseMapSectionMapper baseMapSectionMapper) {
        this.baseMapSectionMapStruct = baseMapSectionMapStruct;
        this.baseMapSectionMapper = baseMapSectionMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseMapSectionDTO }
     * @return int
     */
    @Override
    public int insert(BaseMapSectionDTO record) {
        BaseMapSection baseMapSection = baseMapSectionMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMapSection.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        baseMapSection.setCreatedTime(new Date());
        baseMapSection.setCreatedBy(userId);
        baseMapSection.setLastModifiedBy(userId);
        baseMapSection.setLastModifiedTime(new Date());

        int num = baseMapSectionMapper.insert(baseMapSection);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseMapSectionDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseMapSectionDTO> records) {
        List<BaseMapSection> recordList = baseMapSectionMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseMapSectionMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseMapSectionDTO }
     */
    @Override
    public BaseMapSectionDTO selectByPrimaryKey(Integer id) {

        BaseMapSection baseMapSection = baseMapSectionMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseMapSection, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseMapSectionMapStruct.toDto(baseMapSection);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseMapSectionDTO }
     * @return {@link List<BaseMapSectionDTO> }
     */
    @Override
    public List<BaseMapSectionDTO> selectSelective(BaseMapSectionDTO record) {
        BaseMapSection baseMapSection = baseMapSectionMapStruct.toEntity(record);

        List<BaseMapSection> baseMapSectionList = baseMapSectionMapper.select(baseMapSection);
        return baseMapSectionMapStruct.toDto(baseMapSectionList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseMapSectionDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseMapSectionDTO record) {
        BaseMapSection baseMapSection = baseMapSectionMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMapSection.setLastModifiedBy(userId);
        baseMapSection.setLastModifiedTime(new Date());

        int num = baseMapSectionMapper.updateByPrimaryKey(baseMapSection);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseMapSectionDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseMapSectionDTO record) {
        BaseMapSection baseMapSection = baseMapSectionMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMapSection.setLastModifiedBy(userId);
        baseMapSection.setLastModifiedTime(new Date());

        int num = baseMapSectionMapper.updateByPrimaryKeySelective(baseMapSection);
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
        int num = baseMapSectionMapper.deleteByPrimaryKey(id);
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
        return baseMapSectionMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return baseMapSectionMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return baseMapSectionMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseMapSectionDTO> }
     */
    @Override
    public GridReturnData<BaseMapSectionDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseMapSectionDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseMapSection> list = baseMapSectionMapper.selectPage(map);

        PageInfo<BaseMapSection> pageInfo = new PageInfo<>(list);
        PageInfo<BaseMapSectionDTO> pageInfoFinal = new PageInfo<>(baseMapSectionMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
