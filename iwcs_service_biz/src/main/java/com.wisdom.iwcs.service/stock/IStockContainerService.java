package com.wisdom.iwcs.service.stock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.stock.dto.StockContainerDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/19 15:53
 */
public interface IStockContainerService {
    int insert(StockContainerDTO record);

    int insertBatch(List<StockContainerDTO> records);

    StockContainerDTO selectByPrimaryKey(Integer id);

    List<StockContainerDTO> selectSelective(StockContainerDTO record);

    int updateByPrimaryKey(StockContainerDTO record);

    int updateByPrimaryKeySelective(StockContainerDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<StockContainerDTO> selectPage(GridPageRequest gridPageRequest);
}
