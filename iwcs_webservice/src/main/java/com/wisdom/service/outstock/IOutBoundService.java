package com.wisdom.service.outstock;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.outstock.dto.OutBoundRequestDTO;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:16
 */
public interface IOutBoundService {
    Result outBoundConfirm(OutBoundRequestDTO requestDTO);
}
