package com.wisdom.service.stock.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.controller.mapstruct.inv.InvTaskCondDetailDTOOrStockMapStruct;
import com.wisdom.controller.mapstruct.stock.StockMapStruct;
import com.wisdom.iwcs.common.utils.DeleteFlagEnum;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.inv.dto.InvTaskCondDetailDTO;
import com.wisdom.iwcs.domain.stock.Stock;
import com.wisdom.iwcs.domain.stock.dto.StockConditionDto;
import com.wisdom.iwcs.domain.stock.dto.StockDTO;
import com.wisdom.iwcs.domain.stock.dto.StockDetialDto;
import com.wisdom.iwcs.mapper.stock.StockMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.stock.IStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class StockService implements IStockService {
    private final Logger logger = LoggerFactory.getLogger(StockService.class);

    private final StockMapper stockMapper;

    private final StockMapStruct stockMapStruct;

    @Autowired
    InvTaskCondDetailDTOOrStockMapStruct invTaskCondDetailDTOOrStockMapStruct;

    @Autowired
    public StockService(StockMapStruct stockMapStruct, StockMapper stockMapper) {
        this.stockMapStruct = stockMapStruct;
        this.stockMapper = stockMapper;
    }

    /**
     * 写入记录
     *
     * @param record {@link StockDTO }
     * @return int
     */
    @Override
    public int insert(StockDTO record) {
        Stock stock = stockMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        stock.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
        stock.setCreatedTime(new Date());
        stock.setCreatedBy(userId);
        stock.setLastModifiedBy(userId);
        stock.setLastModifiedTime(new Date());

        int num = stockMapper.insert(stock);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 批量写入记录
     *
     * @param records {@link List<StockDTO> }
     * @return int
     */
    @Override
    public int insertBatch(List<StockDTO> records) {
        List<Stock> recordList = stockMapStruct.toEntity(records);

        Integer userId = SecurityUtils.getCurrentUserId();
        recordList.forEach(record -> {
            record.setDeleteFlag(DeleteFlagEnum.NOT_DELETED.getStatus());
            record.setCreatedTime(new Date());
            record.setCreatedBy(userId);
            record.setLastModifiedBy(userId);
            record.setLastModifiedTime(new Date());
        });

        int num = stockMapper.insertList(recordList);
        Preconditions.checkArgument(num == recordList.size(), ApplicationErrorEnum.COMMON_FAIL);

        return num;
    }

    /**
     * 根据主键-ID查询
     *
     * @param id {@link Integer }
     * @return {@link StockDTO }
     */
    @Override
    public StockDTO selectByPrimaryKey(Integer id) {

        Stock stock = stockMapper.selectByPrimaryKey(id);
        Preconditions.checkNotNull(stock, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);

        return stockMapStruct.toDto(stock);
    }

    /**
     * 根据字段选择性查询
     *
     * @param record {@link StockDTO }
     * @return {@link List<StockDTO> }
     */
    @Override
    public List<StockDTO> selectSelective(StockDTO record) {
        Stock stock = stockMapStruct.toEntity(record);

        List<Stock> stockList = stockMapper.select(stock);
        return stockMapStruct.toDto(stockList);
    }

    /**
     * 根据主键更新
     *
     * @param record {@link StockDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKey(StockDTO record) {
        Stock stock = stockMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        stock.setLastModifiedBy(userId);
        stock.setLastModifiedTime(new Date());

        int num = stockMapper.updateByPrimaryKey(stock);
        Preconditions.checkArgument(num == 1, ApplicationErrorEnum.COMMON_FAIL);

        return num;

    }

    /**
     * 根据主键选择性更新
     *
     * @param record {@link StockDTO }
     * @return int
     */
    @Override
    public int updateByPrimaryKeySelective(StockDTO record) {
        Stock stock = stockMapStruct.toEntity(record);

        Integer userId = SecurityUtils.getCurrentUserId();
        stock.setLastModifiedBy(userId);
        stock.setLastModifiedTime(new Date());

        int num = stockMapper.updateByPrimaryKeySelective(stock);
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
        int num = stockMapper.deleteByPrimaryKey(id);
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
        return stockMapper.deleteLogicByPrimaryKey(id);
    }

    /**
     * 根据主键删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMore(List<String> ids) {
        return stockMapper.deleteByIds(String.join(",", ids));
    }

    /**
     * 根据主键逻辑删除多条记录
     *
     * @param ids {@link List<String> }
     * @return int
     */
    @Override
    public int deleteMoreLogic(List<String> ids) {
        return stockMapper.deleteLogicByIds(String.join(",", ids));
    }

    /**
     * 根据条件分页查询
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link GridReturnData<StockDTO> }
     */
    @Override
    public GridReturnData<StockDTO> selectPage(GridPageRequest gridPageRequest) {
        GridReturnData<StockDTO> mGridReturnData = new GridReturnData<>();
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

        List<Stock> list = stockMapper.selectPage(map);

        PageInfo<Stock> pageInfo = new PageInfo<>(list);
        PageInfo<StockDTO> pageInfoFinal = new PageInfo<>(stockMapStruct.toDto(list));
        pageInfoFinal.setTotal(pageInfo.getTotal());
        mGridReturnData.setPageInfo(pageInfoFinal);

        return mGridReturnData;
    }

    @Override
    public List<StockDTO> selectStock(StockConditionDto stockConditionDto) {

        List<Stock> stockList = stockMapper.selectStock(stockConditionDto);
        return stockMapStruct.toDto(stockList);
    }

    /**
     * 获取库存简要信息
     *
     * @return
     */
    @Override
    public StockDetialDto selectStockDeatilInfo() {
        StockDetialDto stockDetialDto = stockMapper.selectStockDeatilInfo();
        return stockDetialDto;
    }

    public List<Stock> invTaskCondQuery(InvTaskCondDetailDTO invTaskCondDetailDTO, Long days) {
        Stock stock = invTaskCondDetailDTOOrStockMapStruct.toEntity(invTaskCondDetailDTO);
        stock.setOrderNo(ObjectUtils.isEmpty(invTaskCondDetailDTO.getStkOrderNo()) ? "" : invTaskCondDetailDTO.getStkOrderNo());
        stock.setSubOrderNo(ObjectUtils.isEmpty(invTaskCondDetailDTO.getStkSubOrderNo()) ? "" : invTaskCondDetailDTO.getStkSubOrderNo());
        return stockMapper.invTaskCondQuery(stock, days);
    }

}
