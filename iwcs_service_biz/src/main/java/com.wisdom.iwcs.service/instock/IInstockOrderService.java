package com.wisdom.iwcs.service.instock;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderConditionDto;
import com.wisdom.iwcs.domain.instock.dto.InstockOrderDTO;
import com.wisdom.iwcs.domain.instock.dto.instockrequest.DeleteInstockOrderDTO;

import java.util.List;

public interface IInstockOrderService {
    Result saveInStockData(InstockOrderDTO instockOrderDTO);

    //入库单删除
    Result deleteInstockOrder(DeleteInstockOrderDTO deleteInstockOrderDTO);

    List<InstockOrderDTO> selectInstockOrder(InstockOrderConditionDto instockOrderConditionDto);
}
