package com.wisdom.service.control;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.control.ManualUpdatePodLockRequestDTO;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:12
 */
public interface IManualUpdatePodLockService {
    /**
     * 手动上锁/解锁货架
     *
     * @param requestDTO
     * @return
     */
    Result manualUpdatePodLock(ManualUpdatePodLockRequestDTO requestDTO);
}
