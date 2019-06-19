package com.wisdom.iwcs.service.control;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.control.MoveByPodRequestDTO;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:12
 */
public interface IMovePodService {
    Result moveByBincode(MoveByPodRequestDTO requestDTO);
}
