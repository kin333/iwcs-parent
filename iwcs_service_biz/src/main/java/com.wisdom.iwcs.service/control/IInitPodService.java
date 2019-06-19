package com.wisdom.iwcs.service.control;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.control.InitPodRequestDTO;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:11
 */
public interface IInitPodService {
    Result initPod(InitPodRequestDTO requestDTO);
}
