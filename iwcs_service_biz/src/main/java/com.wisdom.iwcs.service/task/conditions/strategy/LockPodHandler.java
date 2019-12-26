package com.wisdom.iwcs.service.task.conditions.strategy;

import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.task.PointPodResource;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.service.task.impl.MapResouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 锁定货架操作
 * @author han
 */
@Service
public class LockPodHandler implements IStrategyHandler {
    @Autowired
    MapResouceService mapResouceService;
    @Autowired
    BasePodDetailMapper basePodDetailMapper;
    @Override
    public void strategyHandler(Object resource) {
        PointPodResource pointPodResource = (PointPodResource)resource;
        BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(pointPodResource.getPodCode());
        basePodDetail.setLockSource(pointPodResource.getTaskCode());
        mapResouceService.lockPod(basePodDetail);
    }
}
