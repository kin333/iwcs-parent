package com.wisdom.service.base.baseImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.base.BaseWbGroupDetailMapStruct;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseWbGroupDetail;
import com.wisdom.iwcs.domain.base.dto.BaseWbGroupDetailDTO;
import com.wisdom.iwcs.mapper.base.BaseWbGroupDetailMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.base.IBaseWbGroupDetailService;
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
public class BaseWbGroupDetailService implements IBaseWbGroupDetailService {
    private final Logger logger = LoggerFactory.getLogger(BaseWbGroupDetailService.class);

    private final BaseWbGroupDetailMapper baseWbGroupDetailMapper;

    private final BaseWbGroupDetailMapStruct baseWbGroupDetailMapStruct;

    @Autowired
    public BaseWbGroupDetailService(BaseWbGroupDetailMapStruct baseWbGroupDetailMapStruct, BaseWbGroupDetailMapper baseWbGroupDetailMapper) {
        this.baseWbGroupDetailMapStruct = baseWbGroupDetailMapStruct;
        this.baseWbGroupDetailMapper = baseWbGroupDetailMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link BaseWbGroupDetailDTO }
     * @return int
     */
    @Override
    public int insert(BaseWbGroupDetailDTO record) {
        BaseWbGroupDetail baseWbGroupDetail = baseWbGroupDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWbGroupDetail.setCreatedTime(new Date());
        baseWbGroupDetail.setCreatedBy(userId);
        baseWbGroupDetail.setLastModifiedBy(userId);
        baseWbGroupDetail.setLastModifiedTime(new Date());

        int num = baseWbGroupDetailMapper.insert(baseWbGroupDetail);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<BaseWbGroupDetailDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<BaseWbGroupDetailDTO> records) {
        List<BaseWbGroupDetail> recordList = baseWbGroupDetailMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseWbGroupDetailMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link BaseWbGroupDetailDTO }
     */
    @Override
    public BaseWbGroupDetailDTO selectByPrimaryKey(Integer id) {

        BaseWbGroupDetail baseWbGroupDetail = baseWbGroupDetailMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseWbGroupDetail, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseWbGroupDetailMapStruct.toDto(baseWbGroupDetail);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link BaseWbGroupDetailDTO }
     * @return {@link List<BaseWbGroupDetailDTO> }
     */
    @Override
    public List<BaseWbGroupDetailDTO> selectSelective(BaseWbGroupDetailDTO record) {
        BaseWbGroupDetail baseWbGroupDetail = baseWbGroupDetailMapStruct.toEntity(record);

        List<BaseWbGroupDetail> baseWbGroupDetailList = baseWbGroupDetailMapper.select(baseWbGroupDetail);
        return baseWbGroupDetailMapStruct.toDto(baseWbGroupDetailList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link BaseWbGroupDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(BaseWbGroupDetailDTO record) {
        BaseWbGroupDetail baseWbGroupDetail = baseWbGroupDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWbGroupDetail.setLastModifiedBy(userId);
        baseWbGroupDetail.setLastModifiedTime(new Date());

        int num = baseWbGroupDetailMapper.updateByPrimaryKey(baseWbGroupDetail);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link BaseWbGroupDetailDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(BaseWbGroupDetailDTO record) {
        BaseWbGroupDetail baseWbGroupDetail = baseWbGroupDetailMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseWbGroupDetail.setLastModifiedBy(userId);
        baseWbGroupDetail.setLastModifiedTime(new Date());

        int num = baseWbGroupDetailMapper.updateByPrimaryKeySelective(baseWbGroupDetail);
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
        int num = baseWbGroupDetailMapper.deleteByPrimaryKey(id);
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
        return baseWbGroupDetailMapper.deleteByIds(String.join(",", ids));
    }


    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<BaseWbGroupDetailDTO> }
     */
    @Override
    public GridReturnData<BaseWbGroupDetailDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<BaseWbGroupDetailDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseWbGroupDetail> list = baseWbGroupDetailMapper.selectPage(map);

        PageInfo<BaseWbGroupDetail> pageInfo = new PageInfo<>(list);
        PageInfo<BaseWbGroupDetailDTO> pageInfoFinal = new PageInfo<>(baseWbGroupDetailMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 统一校验接口 互斥类型
     */
    @Override
    public List<String> selectMutexTypeWbByWbCode(String wbCode) {
        List<String> wbCodes = baseWbGroupDetailMapper.selectMutexTypeWbByWbCode(wbCode);
        wbCodes.remove(wbCode);
        return wbCodes;
    }

    /**
     * 统一校验备货工作台 备货类型
     */
    @Override
    public List<String> selectInventoryTypeWbByWbCode(String wbCode) {
        //先排除有互斥的 在查询备货点
        List<String> wbCodes = baseWbGroupDetailMapper.selectInventoryTypeWbByWbCode(wbCode);
        //排除互斥
        wbCodes.remove(wbCode);
        return wbCodes;
    }

}
