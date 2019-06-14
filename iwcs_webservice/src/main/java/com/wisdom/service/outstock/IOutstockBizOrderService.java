package com.wisdom.service.outstock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.outstock.dto.DeleteOutStockOrderRequestDTO;
import com.wisdom.iwcs.domain.outstock.dto.InitOutStockOrderRequestDTO;
import com.wisdom.iwcs.domain.outstock.dto.OutstockBizOrderDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:16
 */
public interface IOutstockBizOrderService {
    int insert(OutstockBizOrderDTO record);

    int insertBatch(List<OutstockBizOrderDTO> records);

    OutstockBizOrderDTO selectByPrimaryKey(Integer id);

    List<OutstockBizOrderDTO> selectSelective(OutstockBizOrderDTO record);

    int updateByPrimaryKey(OutstockBizOrderDTO record);

    int updateByPrimaryKeySelective(OutstockBizOrderDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<OutstockBizOrderDTO> selectPage(GridPageRequest gridPageRequest);

    Result syncOutstockOrderInfo(InitOutStockOrderRequestDTO requestDTO);

    Result deleteOutStockOrderInfo(DeleteOutStockOrderRequestDTO requestDTO);
}
