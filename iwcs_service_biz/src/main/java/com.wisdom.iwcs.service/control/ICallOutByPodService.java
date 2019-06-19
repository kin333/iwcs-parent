package com.wisdom.iwcs.service.control;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.control.CallOutByBincodeRequestDTO;
import com.wisdom.iwcs.domain.control.CallOutByPodCodeRequestDTO;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:11
 */
public interface ICallOutByPodService {
    /**
     * 根据指定仓位号呼叫
     *
     * @param requestDTO
     * @return
     */
    Result callOutByBincode(CallOutByBincodeRequestDTO requestDTO);

    /**
     * 根据指定货架号呼叫
     *
     * @param requestDTO
     * @return
     */
    Result callOutByPodCode(CallOutByPodCodeRequestDTO requestDTO);

}
