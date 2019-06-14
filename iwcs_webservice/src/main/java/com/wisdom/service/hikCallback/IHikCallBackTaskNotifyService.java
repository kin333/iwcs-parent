package com.wisdom.service.hikCallback;

import com.wisdom.iwcs.domain.hikSync.HikSyncResponse;
import com.wisdom.iwcs.domain.hikSync.NotifyClientRequestDTO;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:14
 */
public interface IHikCallBackTaskNotifyService {
    HikSyncResponse receiveTaskNotify(NotifyClientRequestDTO requestDTO);
}
