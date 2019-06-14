package com.wisdom.service.outstock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.outstock.dto.OrderMatDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutStockOrderDto;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:16
 */
public interface IOrderMatService {
    int insert(OrderMatDTO record);

    int insertBatch(List<OrderMatDTO> records);

    OrderMatDTO selectByPrimaryKey(Integer id);

    List<OrderMatDTO> selectSelective(OrderMatDTO record);

    int updateByPrimaryKey(OrderMatDTO record);

    int updateByPrimaryKeySelective(OrderMatDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<OrderMatDTO> selectPage(GridPageRequest gridPageRequest);

    List<OutStockOrderDto> selectOutStockOrder(OutStockOrderDto outStockOrderDto);
}
