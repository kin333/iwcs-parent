package com.wisdom.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.base.BaseMatPackageSpecMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMatPackageSpec;
import com.wisdom.iwcs.domain.base.dto.BaseMatPackageSpecDTO;
import com.wisdom.iwcs.mapper.base.BaseMatPackageSpecMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.IBaseMatPackageSpecService;
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
public class BaseMatPackageSpecService implements IBaseMatPackageSpecService {
    private final Logger logger = LoggerFactory.getLogger(BaseMatPackageSpecService.class);

    private final BaseMatPackageSpecMapper baseMatPackageSpecMapper;

    private final BaseMatPackageSpecMapStruct baseMatPackageSpecMapStruct;

    @Autowired
    public BaseMatPackageSpecService(BaseMatPackageSpecMapStruct baseMatPackageSpecMapStruct, BaseMatPackageSpecMapper baseMatPackageSpecMapper) {
        this.baseMatPackageSpecMapStruct = baseMatPackageSpecMapStruct;
        this.baseMatPackageSpecMapper = baseMatPackageSpecMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseMatPackageSpecDTO }
     * @return int
     */
    @Override
    public int insert(BaseMatPackageSpecDTO record) {
        BaseMatPackageSpec baseMatPackageSpec = baseMatPackageSpecMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMatPackageSpec.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        baseMatPackageSpec.setCreatedTime(new Date());
        baseMatPackageSpec.setCreatedBy(userId);
        baseMatPackageSpec.setLastModifiedBy(userId);
        baseMatPackageSpec.setLastModifiedTime(new Date());

        int num = baseMatPackageSpecMapper.insert(baseMatPackageSpec);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseMatPackageSpecDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseMatPackageSpecDTO> records) {
        List<BaseMatPackageSpec> recordList = baseMatPackageSpecMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseMatPackageSpecMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseMatPackageSpecDTO }
     */
    @Override
    public BaseMatPackageSpecDTO selectByPrimaryKey(Integer id) {

        BaseMatPackageSpec baseMatPackageSpec = baseMatPackageSpecMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseMatPackageSpec, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseMatPackageSpecMapStruct.toDto(baseMatPackageSpec);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseMatPackageSpecDTO }
     * @return {@link List<BaseMatPackageSpecDTO> }
     */
    @Override
    public List<BaseMatPackageSpecDTO> selectSelective(BaseMatPackageSpecDTO record) {
        BaseMatPackageSpec baseMatPackageSpec = baseMatPackageSpecMapStruct.toEntity(record);

        List<BaseMatPackageSpec> baseMatPackageSpecList = baseMatPackageSpecMapper.select(baseMatPackageSpec);
        return baseMatPackageSpecMapStruct.toDto(baseMatPackageSpecList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseMatPackageSpecDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseMatPackageSpecDTO record) {
        BaseMatPackageSpec baseMatPackageSpec = baseMatPackageSpecMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMatPackageSpec.setLastModifiedBy(userId);
        baseMatPackageSpec.setLastModifiedTime(new Date());

        int num = baseMatPackageSpecMapper.updateByPrimaryKey(baseMatPackageSpec);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseMatPackageSpecDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseMatPackageSpecDTO record) {
        BaseMatPackageSpec baseMatPackageSpec = baseMatPackageSpecMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseMatPackageSpec.setLastModifiedBy(userId);
        baseMatPackageSpec.setLastModifiedTime(new Date());

        int num = baseMatPackageSpecMapper.updateByPrimaryKeySelective(baseMatPackageSpec);
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
        int num = baseMatPackageSpecMapper.deleteByPrimaryKey(id);
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
        return baseMatPackageSpecMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return baseMatPackageSpecMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return baseMatPackageSpecMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseMatPackageSpecDTO> }
     */
    @Override
    public GridReturnData<BaseMatPackageSpecDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseMatPackageSpecDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseMatPackageSpec> list = baseMatPackageSpecMapper.selectPage(map);

        PageInfo<BaseMatPackageSpec> pageInfo = new PageInfo<>(list);
        PageInfo<BaseMatPackageSpecDTO> pageInfoFinal = new PageInfo<>(baseMatPackageSpecMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
