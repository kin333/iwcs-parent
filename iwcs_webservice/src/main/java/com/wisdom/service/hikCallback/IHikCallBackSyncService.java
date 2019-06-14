package com.wisdom.service.hikCallback;

import com.wisdom.iwcs.domain.hikSync.HikSyncResponse;
import com.wisdom.iwcs.domain.hikSync.SyncNotifyRequestDto;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:13
 */
public interface IHikCallBackSyncService {
    HikSyncResponse receiveSyncBaseInfoNotify(SyncNotifyRequestDto syncNotifyRequestDto);
}
