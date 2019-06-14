package com.wisdom.service.stock.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.stock.StockSnMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.stock.StockSn;
import com.wisdom.iwcs.domain.stock.dto.StockSnDTO;
import com.wisdom.iwcs.mapper.stock.StockSnMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.stock.IStockSnService;
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
public class StockSnService implements IStockSnService {
    private final Logger logger = LoggerFactory.getLogger(StockSnService.class);

    private final StockSnMapper stockSnMapper;

    private final StockSnMapStruct stockSnMapStruct;

    @Autowired
    public StockSnService(StockSnMapStruct stockSnMapStruct, StockSnMapper stockSnMapper) {
        this.stockSnMapStruct = stockSnMapStruct;
        this.stockSnMapper = stockSnMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link StockSnDTO }
     * @return int
     */
    @Override
    public int insert(StockSnDTO record) {
        StockSn stockSn = stockSnMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        stockSn.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        stockSn.setCreatedTime(new Date());
        stockSn.setCreatedBy(userId);
        stockSn.setLastModifiedBy(userId);
        stockSn.setLastModifiedTime(new Date());

        int num = stockSnMapper.insert(stockSn);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<StockSnDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<StockSnDTO> records) {
        List<StockSn> recordList = stockSnMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = stockSnMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link StockSnDTO }
     */
    @Override
    public StockSnDTO selectByPrimaryKey(Integer id) {

        StockSn stockSn = stockSnMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(stockSn, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return stockSnMapStruct.toDto(stockSn);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link StockSnDTO }
     * @return {@link List<StockSnDTO> }
     */
    @Override
    public List<StockSnDTO> selectSelective(StockSnDTO record) {
        StockSn stockSn = stockSnMapStruct.toEntity(record);

        List<StockSn> stockSnList = stockSnMapper.select(stockSn);
        return stockSnMapStruct.toDto(stockSnList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link StockSnDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(StockSnDTO record) {
        StockSn stockSn = stockSnMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        stockSn.setLastModifiedBy(userId);
        stockSn.setLastModifiedTime(new Date());

        int num = stockSnMapper.updateByPrimaryKey(stockSn);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link StockSnDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(StockSnDTO record) {
        StockSn stockSn = stockSnMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        stockSn.setLastModifiedBy(userId);
        stockSn.setLastModifiedTime(new Date());

        int num = stockSnMapper.updateByPrimaryKeySelective(stockSn);
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
        int num = stockSnMapper.deleteByPrimaryKey(id);
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
        return stockSnMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return stockSnMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return stockSnMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<StockSnDTO> }
     */
    @Override
    public GridReturnData<StockSnDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<StockSnDTO> mGridReturnData = new GridReturnData<>();
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

        List<StockSn> list = stockSnMapper.selectPage(map);

        PageInfo<StockSn> pageInfo = new PageInfo<>(list);
        PageInfo<StockSnDTO> pageInfoFinal = new PageInfo<>(stockSnMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    /**
     * 根据库存编号获取库存sn
     *
     * @param stkCode
     * @return
     */
    @Override
    public List<StockSnDTO> selectStockSn(String stkCode) {

        Preconditions.checkBusinessError(stkCode == null || stkCode.isEmpty(), "缺少库存编号");
        List<StockSn> stockSnList = stockSnMapper.selectStockSn(stkCode);
        return stockSnMapStruct.toDto(stockSnList);
    }
}
