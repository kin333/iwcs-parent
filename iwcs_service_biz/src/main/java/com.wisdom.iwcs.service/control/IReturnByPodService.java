package com.wisdom.iwcs.service.control;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.control.ReturnByBincodeRequestDTO;
import com.wisdom.iwcs.domain.control.ReturnByPodCodeRequestDTO;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:12
 */
public interface IReturnByPodService {
    /**
     * 按bincode指定回库
     *
     * @param requestDTO
     * @return
     */
    Result returnByBincode(ReturnByBincodeRequestDTO requestDTO);

    /**
     * 按podCode指定回库
     *
     * @param requestDTO
     * @return
     */
    Result returnByPodCode(ReturnByPodCodeRequestDTO requestDTO);
}
