package com.wisdom.service.hikCallback;

import com.wisdom.iwcs.domain.hikSync.HikReturnPodStraResponse;
import com.wisdom.iwcs.domain.hikSync.PodReturnAreaRequestDTO;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:13
 */
public interface IHikCallBackGetPodReturnAreaService {
    HikReturnPodStraResponse returnPodReturnArea(PodReturnAreaRequestDTO podReturnAreaRequestDTO);
}
