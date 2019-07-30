package com.wisdom.iwcs.service.task.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.*;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.domain.task.BaseConnectionPoint;
import com.wisdom.iwcs.domain.task.dto.BaseConnectionPointDTO;
import com.wisdom.iwcs.mapper.task.BaseConnectionPointMapper;
import com.wisdom.iwcs.mapstruct.task.BaseConnectionPointMapStruct;
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
public class BaseConnectionPointService {
    private final Logger logger = LoggerFactory.getLogger(BaseConnectionPointService.class);

    private final BaseConnectionPointMapper baseConnectionPointMapper;

    private final BaseConnectionPointMapStruct baseConnectionPointMapStruct;

    @Autowired
    public BaseConnectionPointService(BaseConnectionPointMapStruct baseConnectionPointMapStruct, BaseConnectionPointMapper baseConnectionPointMapper) {
        this.baseConnectionPointMapStruct = baseConnectionPointMapStruct;
        this.baseConnectionPointMapper = baseConnectionPointMapper;
    }

    /**
     * 写入记录
     *
     *
     * @param record {@link BaseConnectionPointDTO }
     *
     * @return int
     */
    public int insert(BaseConnectionPointDTO record) {
        BaseConnectionPoint baseConnectionPoint = baseConnectionPointMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseConnectionPoint.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        baseConnectionPoint.setCreatedTime(new Date());
        baseConnectionPoint.setCreatedBy(userId);
        baseConnectionPoint.setLastModifiedBy(userId);
        baseConnectionPoint.setLastModifiedTime(new Date());

        int num = baseConnectionPointMapper.insert(baseConnectionPoint);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     *
     * @param records {@link List<BaseConnectionPointDTO> }
     *
     * @return int
     */
    public int insertBatch(List<BaseConnectionPointDTO> records) {
        List<BaseConnectionPoint> recordList = baseConnectionPointMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = baseConnectionPointMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link BaseConnectionPointDTO }
     */
    public BaseConnectionPointDTO selectByPrimaryKey(Integer id) {

        BaseConnectionPoint baseConnectionPoint = baseConnectionPointMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(baseConnectionPoint, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return baseConnectionPointMapStruct.toDto(baseConnectionPoint);
    }

    /**
     * 根据字段选择性查询
     *
     *
     * @param record {@link BaseConnectionPointDTO }
     *
     * @return {@link List<BaseConnectionPointDTO> }
     */
    public List<BaseConnectionPointDTO> selectSelective(BaseConnectionPointDTO record) {
        BaseConnectionPoint baseConnectionPoint = baseConnectionPointMapStruct.toEntity(record);

        List<BaseConnectionPoint> baseConnectionPointList = baseConnectionPointMapper.select(baseConnectionPoint);
        return baseConnectionPointMapStruct.toDto(baseConnectionPointList);
    }

    /**
     * 根据主键更新
     *
     *
     * @param record {@link BaseConnectionPointDTO }
     *
     * @return int
     */
    public int updateByPrimaryKey(BaseConnectionPointDTO record) {
        BaseConnectionPoint baseConnectionPoint = baseConnectionPointMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseConnectionPoint.setLastModifiedBy(userId);
        baseConnectionPoint.setLastModifiedTime(new Date());

        int num = baseConnectionPointMapper.updateByPrimaryKey(baseConnectionPoint);
        Preconditions.checkArgument(num ==1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     *
     * @param record {@link BaseConnectionPointDTO }
     *
     * @return int
     */
    public int updateByPrimaryKeySelective(BaseConnectionPointDTO record) {
        BaseConnectionPoint baseConnectionPoint = baseConnectionPointMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        baseConnectionPoint.setLastModifiedBy(userId);
        baseConnectionPoint.setLastModifiedTime(new Date());

        int num = baseConnectionPointMapper.updateByPrimaryKeySelective(baseConnectionPoint);
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
        int num = baseConnectionPointMapper.deleteByPrimaryKey(id);
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
        return baseConnectionPointMapper.deleteLogicByPrimaryKey(id);
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
        return baseConnectionPointMapper.deleteByIds(String.join(",", ids));
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
        return baseConnectionPointMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link GridReturnData<BaseConnectionPointDTO> }
     */
    public GridReturnData<BaseConnectionPointDTO> selectPage(GridPageRequest gridPageRequest){
        GridReturnData<BaseConnectionPointDTO> mGridReturnData = new GridReturnData<>();
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

        List<BaseConnectionPoint> list = baseConnectionPointMapper.selectPage(map);

        PageInfo<BaseConnectionPoint> pageInfo = new PageInfo<>(list);
        PageInfo<BaseConnectionPointDTO> pageInfoFinal = new PageInfo<>(baseConnectionPointMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
