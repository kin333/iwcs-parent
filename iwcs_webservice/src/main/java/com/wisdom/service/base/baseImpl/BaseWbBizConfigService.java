package com.wisdom.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.base.BaseWbBizConfigMapStruct;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseWbBizConfig;
import com.wisdom.iwcs.domain.base.dto.BaseWbBizConfigDTO;
import com.wisdom.iwcs.mapper.base.BaseWbBizConfigMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.IBaseWbBizConfigService;
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
public class BaseWbBizConfigService implements IBaseWbBizConfigService {
    private final Logger logger = LoggerFactory.getLogger(BaseWbBizConfigService.class);

    private final BaseWbBizConfigMapper baseWbBizConfigMapper;

    private final BaseWbBizConfigMapStruct baseWbBizConfigMapStruct;

    @Autowired
    public BaseWbBizConfigService(BaseWbBizConfigMapStruct baseWbBizConfigMapStruct, BaseWbBizConfigMapper baseWbBizConfigMapper) {
        this.baseWbBizConfigMapStruct = baseWbBizConfigMapStruct;
        this.baseWbBizConfigMapper = baseWbBizConfigMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseWbBizConfigDTO }
     * @return int
     */
    @Override
    public int insert(BaseWbBizConfigDTO record) {
        BaseWbBizConfig baseWbBizConfig = baseWbBizConfigMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWbBizConfig.setCreatedTime(new Date());
        baseWbBizConfig.setCreatedBy(userId);
        baseWbBizConfig.setLastModifiedBy(userId);
        baseWbBizConfig.setLastModifiedTime(new Date());

        int num = baseWbBizConfigMapper.insert(baseWbBizConfig);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseWbBizConfigDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseWbBizConfigDTO> records) {
        List<BaseWbBizConfig> recordList = baseWbBizConfigMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseWbBizConfigMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseWbBizConfigDTO }
     */
    @Override
    public BaseWbBizConfigDTO selectByPrimaryKey(Integer id) {

        BaseWbBizConfig baseWbBizConfig = baseWbBizConfigMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseWbBizConfig, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseWbBizConfigMapStruct.toDto(baseWbBizConfig);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseWbBizConfigDTO }
     * @return {@link List<BaseWbBizConfigDTO> }
     */
    @Override
    public List<BaseWbBizConfigDTO> selectSelective(BaseWbBizConfigDTO record) {
        BaseWbBizConfig baseWbBizConfig = baseWbBizConfigMapStruct.toEntity(record);

        List<BaseWbBizConfig> baseWbBizConfigList = baseWbBizConfigMapper.select(baseWbBizConfig);
        return baseWbBizConfigMapStruct.toDto(baseWbBizConfigList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseWbBizConfigDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseWbBizConfigDTO record) {
        BaseWbBizConfig baseWbBizConfig = baseWbBizConfigMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWbBizConfig.setLastModifiedBy(userId);
        baseWbBizConfig.setLastModifiedTime(new Date());

        int num = baseWbBizConfigMapper.updateByPrimaryKey(baseWbBizConfig);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseWbBizConfigDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseWbBizConfigDTO record) {
        BaseWbBizConfig baseWbBizConfig = baseWbBizConfigMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWbBizConfig.setLastModifiedBy(userId);
        baseWbBizConfig.setLastModifiedTime(new Date());

        int num = baseWbBizConfigMapper.updateByPrimaryKeySelective(baseWbBizConfig);
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
        int num = baseWbBizConfigMapper.deleteByPrimaryKey(id);
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
        return baseWbBizConfigMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseWbBizConfigDTO> }
     */
    @Override
    public GridReturnData<BaseWbBizConfigDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseWbBizConfigDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseWbBizConfig> list = baseWbBizConfigMapper.selectPage(map);

        PageInfo<BaseWbBizConfig> pageInfo = new PageInfo<>(list);
        PageInfo<BaseWbBizConfigDTO> pageInfoFinal = new PageInfo<>(baseWbBizConfigMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
