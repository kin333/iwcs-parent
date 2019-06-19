package com.wisdom.iwcs.service.callHik;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.control.RotatePodRequestDTO;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:09
 */
public interface IRotatePodService {
    Result rotatePod(RotatePodRequestDTO requestDTO);
}
