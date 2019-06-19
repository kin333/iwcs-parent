package com.wisdom.iwcs.service.hikCallback;

import com.wisdom.iwcs.domain.hikSync.HikSyncResponse;
import com.wisdom.iwcs.domain.hikSync.NotifyPodArrRequestDTO;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:13
 */
public interface IHikCallBackNotifyPodArrService {
    HikSyncResponse receivePodArriveStorageNotify(NotifyPodArrRequestDTO requestDTO);
}
