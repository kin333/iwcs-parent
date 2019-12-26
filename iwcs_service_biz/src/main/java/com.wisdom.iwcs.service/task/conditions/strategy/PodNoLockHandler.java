package com.wisdom.iwcs.service.task.conditions.strategy;

import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.task.PointPodResource;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wisdom.iwcs.common.utils.TaskConstants.InLockStatus.IN_LOCK;

/**
 * 检查货架是否被锁定
 * @author han
 */
@Service
public class PodNoLockHandler implements IStrategyHandler {
    @Autowired
    BasePodDetailMapper basePodDetailMapper;
    @Override
    public void strategyHandler(Object resource) {
        PointPodResource pointPodResource = (PointPodResource)resource;
        BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(pointPodResource.getPodCode());
        if (IN_LOCK.equals(basePodDetail.getInLock())) {
            throw new MesBusinessException(pointPodResource.getPodCode() + "货架已被锁定");
        }
    }
}
