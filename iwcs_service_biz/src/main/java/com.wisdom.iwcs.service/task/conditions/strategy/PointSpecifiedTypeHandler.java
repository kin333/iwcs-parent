package com.wisdom.iwcs.service.task.conditions.strategy;

import com.alibaba.fastjson.JSONObject;
import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.PointPodResource;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.service.task.conditions.strategy.param.PointStrategyParams;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 检查点位区域类型.
 * @author han
 */
@Service
public class PointSpecifiedTypeHandler implements IStrategyHandler {
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Override
    public void strategyHandler(Object resource, String strategyParams) {
        PointPodResource pointPodResource = (PointPodResource)resource;
        PointStrategyParams pointStrategyParams = JSONObject.parseObject(strategyParams, PointStrategyParams.class);
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(pointPodResource.getPointAlias());
        String bizSecondAreaCode = pointStrategyParams.getBizSecondAreaCode();
        String operateAreaCode = pointStrategyParams.getOperateAreaCode();
        String bizType = pointStrategyParams.getBizType();
        if (StringUtils.isNotBlank(bizSecondAreaCode) && !bizSecondAreaCode.equals(baseMapBerth.getBizSecondAreaCode())) {
            throw new MesBusinessException(pointPodResource.getPointAlias() + "点位业务次级区域不匹配,要求的区域为:"
                    + bizSecondAreaCode + ",实际为:" + baseMapBerth.getBizSecondAreaCode());
        }
        if (StringUtils.isNotBlank(operateAreaCode) && !operateAreaCode.equals(baseMapBerth.getOperateAreaCode())) {
            throw new MesBusinessException(pointPodResource.getPointAlias() + "点位作业区域不匹配,要求的区域为:"
                    + operateAreaCode + ",实际为:" + baseMapBerth.getOperateAreaCode());
        }
        if (StringUtils.isNotBlank(bizType) && !bizType.equals(baseMapBerth.getBizType())) {
            throw new MesBusinessException(pointPodResource.getPointAlias() + "点位业务类型不匹配,要求的区域为:"
                    + bizType + ",实际为:" + baseMapBerth.getBizType());
        }

    }
}