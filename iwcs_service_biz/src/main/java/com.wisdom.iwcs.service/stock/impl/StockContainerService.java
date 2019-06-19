package com.wisdom.iwcs.service.stock.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.stock.StockContainer;
import com.wisdom.iwcs.domain.stock.dto.StockContainerDTO;
import com.wisdom.iwcs.mapper.stock.StockContainerMapper;
import com.wisdom.iwcs.mapstruct.stock.StockContainerMapStruct;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.stock.IStockContainerService;
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
public class StockContainerService implements IStockContainerService {
    private final Logger logger = LoggerFactory.getLogger(StockContainerService.class);

    private final StockContainerMapper stockContainerMapper;

    private final StockContainerMapStruct stockContainerMapStruct;

    @Autowired
    public StockContainerService(StockContainerMapStruct stockContainerMapStruct, StockContainerMapper stockContainerMapper) {
        this.stockContainerMapStruct = stockContainerMapStruct;
        this.stockContainerMapper = stockContainerMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link StockContainerDTO }
     * @return int
     */
    @Override
    public int insert(StockContainerDTO record) {
        StockContainer stockContainer = stockContainerMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        stockContainer.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        stockContainer.setCreatedTime(new Date());
        stockContainer.setCreatedBy(userId);
        stockContainer.setLastModifiedBy(userId);
        stockContainer.setLastModifiedTime(new Date());

        int num = stockContainerMapper.insert(stockContainer);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<StockContainerDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<StockContainerDTO> records) {
        List<StockContainer> recordList = stockContainerMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = stockContainerMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link StockContainerDTO }
     */
    @Override
    public StockContainerDTO selectByPrimaryKey(Integer id) {

        StockContainer stockContainer = stockContainerMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(stockContainer, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return stockContainerMapStruct.toDto(stockContainer);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link StockContainerDTO }
     * @return {@link List<StockContainerDTO> }
     */
    @Override
    public List<StockContainerDTO> selectSelective(StockContainerDTO record) {
        StockContainer stockContainer = stockContainerMapStruct.toEntity(record);

        List<StockContainer> stockContainerList = stockContainerMapper.select(stockContainer);
        return stockContainerMapStruct.toDto(stockContainerList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link StockContainerDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(StockContainerDTO record) {
        StockContainer stockContainer = stockContainerMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        stockContainer.setLastModifiedBy(userId);
        stockContainer.setLastModifiedTime(new Date());

        int num = stockContainerMapper.updateByPrimaryKey(stockContainer);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link StockContainerDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(StockContainerDTO record) {
        StockContainer stockContainer = stockContainerMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        stockContainer.setLastModifiedBy(userId);
        stockContainer.setLastModifiedTime(new Date());

        int num = stockContainerMapper.updateByPrimaryKeySelective(stockContainer);
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
        int num = stockContainerMapper.deleteByPrimaryKey(id);
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
        return stockContainerMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return stockContainerMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return stockContainerMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<StockContainerDTO> }
     */
    @Override
    public GridReturnData<StockContainerDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<StockContainerDTO> mGridReturnData = new GridReturnData<>();
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

        List<StockContainer> list = stockContainerMapper.selectPage(map);

        PageInfo<StockContainer> pageInfo = new PageInfo<>(list);
        PageInfo<StockContainerDTO> pageInfoFinal = new PageInfo<>(stockContainerMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }
}
