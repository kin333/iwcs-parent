package com.wisdom.iwcs.service.outstock;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.outstock.dto.OutStockCallRequestDTO;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:17
 */
public interface IOutStockCallOutService {
    Result outStockCallOut(OutStockCallRequestDTO requestDTO);
}
