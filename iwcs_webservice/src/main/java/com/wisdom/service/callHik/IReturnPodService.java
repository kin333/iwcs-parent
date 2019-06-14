package com.wisdom.service.callHik;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.hikSync.ReturnPodDTO;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:09
 */
public interface IReturnPodService {
    Result returnPodRequest(ReturnPodDTO returnPodDTO);
}
