package com.wisdom.service.stock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.stock.dto.StockConditionDto;
import com.wisdom.iwcs.domain.stock.dto.StockDTO;
import com.wisdom.iwcs.domain.stock.dto.StockDetialDto;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/19 15:53
 */
public interface IStockService {
    int insert(StockDTO record);

    int insertBatch(List<StockDTO> records);

    StockDTO selectByPrimaryKey(Integer id);

    List<StockDTO> selectSelective(StockDTO record);

    int updateByPrimaryKey(StockDTO record);

    int updateByPrimaryKeySelective(StockDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<StockDTO> selectPage(GridPageRequest gridPageRequest);

    List<StockDTO> selectStock(StockConditionDto stockConditionDto);

    StockDetialDto selectStockDeatilInfo();
}
