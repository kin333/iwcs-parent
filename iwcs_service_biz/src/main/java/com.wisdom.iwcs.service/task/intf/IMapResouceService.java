package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;

import java.util.List;


/**
 * 处理任务过程中资源分配
 */
public interface IMapResouceService {

    /**
     * 计算检验点的空点位
     * @param lockMapBerthCondition
     * @return
     */
    BaseMapBerth caculateInspectionWorkAreaEmptyPoint(LockMapBerthCondition lockMapBerthCondition);

    /**
     * 锁住空闲点位
     * @param lockStorageDto
     * @return
     */
    Result lockMapBerth(LockStorageDto lockStorageDto);

    /**
     * 解锁空闲点位
     * @param lockStorageDto
     * @return
     */
    Result unlockMapBerth(LockStorageDto lockStorageDto);

    /**
     * 货架上锁
     */
    boolean lockPod(BasePodDetail basePodDetail);


    Result lockEmptyStorageByBizTypeList(List<LockMapBerthCondition> baseMapBerthList);


}
