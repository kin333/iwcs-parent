package com.wisdom.iwcs.service.task.conditions.strategy;

import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.task.PointPodResource;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.service.task.impl.MapResouceService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 锁定点位上的货架操作
 * @author han
 */
@Service
public class LockPointPodHandler implements IStrategyHandler {
    @Autowired
    MapResouceService mapResouceService;
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    BasePodDetailMapper basePodDetailMapper;
    @Override
    public void strategyHandler(Object resource) {
        PointPodResource pointPodResource = (PointPodResource)resource;
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(pointPodResource.getPointAlias());
        if (StringUtils.isEmpty(baseMapBerth.getPodCode())) {
            throw new MesBusinessException(pointPodResource.getPointAlias() + "点位上没有货架");
        }
        BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(baseMapBerth.getPodCode());
        basePodDetail.setLockSource(pointPodResource.getTaskCode());
        mapResouceService.lockPod(basePodDetail);
    }
}