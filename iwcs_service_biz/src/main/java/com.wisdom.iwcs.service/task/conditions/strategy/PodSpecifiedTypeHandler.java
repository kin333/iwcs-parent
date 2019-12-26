package com.wisdom.iwcs.service.task.conditions.strategy;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.domain.base.BasePod;
import com.wisdom.iwcs.domain.task.PointPodResource;
import com.wisdom.iwcs.mapper.base.BasePodMapper;
import com.wisdom.iwcs.service.task.conditions.strategy.param.PodStrategyParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 检查货架类型.
 * @author han
 */
@Service
public class PodSpecifiedTypeHandler implements IStrategyHandler {
    @Autowired
    BasePodMapper basePodMapper;
    @Override
    public void strategyHandler(Object resource, String strategyParams) {
        PointPodResource pointPodResource = (PointPodResource)resource;
        PodStrategyParams podStrategyParams = JSONObject.parseObject(strategyParams, PodStrategyParams.class);
        BasePod basePod = basePodMapper.selectByPodCode(pointPodResource.getPodCode());
        String podType = podStrategyParams.getPodType();
        if (StringUtils.isNotBlank(podType) && !podType.equals(basePod.getPodTypeCode())) {
            throw new MesBusinessException(basePod.getPodCode() + "货架的实际类型与指定类型不匹配,指定的类型为:"
                    + podType + ",实际的类型为:" + basePod.getPodTypeCode());
        }
    }
}