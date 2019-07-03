package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMapBerth;


/**
 * 处理任务过程中资源分配
 */
public interface IMapResouceService {

    /**
     *
     * @param baseMapBerth
     * @return
     */
    Result caculateInspectionAreaEmptyPoint(BaseMapBerth baseMapBerth);
}
