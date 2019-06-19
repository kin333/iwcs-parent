package com.wisdom.iwcs.service.stock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.stock.dto.StockSnDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/19 15:53
 */
public interface IStockSnService {
    int insert(StockSnDTO record);

    int insertBatch(List<StockSnDTO> records);

    StockSnDTO selectByPrimaryKey(Integer id);

    List<StockSnDTO> selectSelective(StockSnDTO record);

    int updateByPrimaryKey(StockSnDTO record);

    int updateByPrimaryKeySelective(StockSnDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<StockSnDTO> selectPage(GridPageRequest gridPageRequest);

    List<StockSnDTO> selectStockSn(String stkCode);
}
