package com.wisdom.iwcs.service.task.conditions.strategy;

import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.PointPodResource;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wisdom.iwcs.common.utils.TaskConstants.InLockStatus.IN_LOCK;

/**
 * 检查点位没有被锁定
 * @author han
 */
@Service
public class PointNoLockHandler implements IStrategyHandler {
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Override
    public void strategyHandler(Object resource) {
        PointPodResource pointPodResource = (PointPodResource)resource;
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(pointPodResource.getPointAlias());
        if (IN_LOCK.equals(baseMapBerth.getInLock())) {
            throw new MesBusinessException(pointPodResource.getPointAlias() + "点位已被锁定");
        }
    }
}
