package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;

import java.util.List;


/**
 * 处理任务过程中资源分配
 */
public interface IMapResouceService {

    /**
     * 计算检验点的空点位
     * @param baseMapBerthDTO
     * @return
     */
    Result caculateInspectionAreaEmptyPoint(BaseMapBerthDTO baseMapBerthDTO);

    /**
     * 锁住空闲点位
     * @param baseMapBerthDTO
     * @return
     */
    Result lockMapBerth(BaseMapBerthDTO baseMapBerthDTO);

    /**
     * 解锁空闲点位
     * @param baseMapBerth
     * @return
     */
    Result unlockMapBerth(BaseMapBerthDTO baseMapBerth);


    Result lockEmptyStorageByBizTypeList(List<BaseMapBerthDTO> baseMapBerthList);


}
