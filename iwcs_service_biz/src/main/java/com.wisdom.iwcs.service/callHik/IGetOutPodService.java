package com.wisdom.iwcs.service.callHik;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.hikSync.GetOutPodDTO;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:08
 */
public interface IGetOutPodService {
    Result getOutPod(GetOutPodDTO getOutPodDTO);
}
