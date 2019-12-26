package com.wisdom.iwcs.service.task.conditions.strategy;

import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.PointPodResource;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 检查点位上无货架.
 * @author han
 */
@Service
public class PointNoPodHandler implements IStrategyHandler {
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Override
    public void strategyHandler(Object resource) {
        PointPodResource pointPodResource = (PointPodResource)resource;
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(pointPodResource.getPointAlias());
        if (StringUtils.isNotBlank(baseMapBerth.getPodCode())) {
            throw new MesBusinessException(pointPodResource.getPointAlias() + "点位上已有货架:" + baseMapBerth.getPodCode());
        }
    }
}
