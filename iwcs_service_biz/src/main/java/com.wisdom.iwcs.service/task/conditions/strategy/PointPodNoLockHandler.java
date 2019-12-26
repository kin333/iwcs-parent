package com.wisdom.iwcs.service.task.conditions.strategy;

import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.task.PointPodResource;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wisdom.iwcs.common.utils.TaskConstants.InLockStatus.IN_LOCK;

/**
 * 检查点位上货架没有被锁定.
 * @author han
 */
@Service
public class PointPodNoLockHandler implements IStrategyHandler {
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    BasePodDetailMapper basePodDetailMapper;
    @Override
    public void strategyHandler(Object resource) {
        PointPodResource pointPodResource = (PointPodResource)resource;
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(pointPodResource.getPointAlias());
        if (StringUtils.isBlank(baseMapBerth.getPodCode())) {
            throw new MesBusinessException(pointPodResource.getPointAlias() + "点位上没有货架:");
        }
        BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(pointPodResource.getPodCode());
        if (IN_LOCK.equals(basePodDetail.getInLock())) {
            throw new MesBusinessException(pointPodResource.getPodCode() + "货架已被锁定");
        }
    }
}