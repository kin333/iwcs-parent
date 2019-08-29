package com.wisdom.iwcs.service.task.conditions.point;

import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 通过属性计算策略
 */
@Component
public class CalculateByPropStrategic implements IGetPointStrategic{
    private static final Logger logger = LoggerFactory.getLogger(CalculateByPropStrategic.class);
    @Override
    public String getPoint(AutoCreateBaseInfo autoCreateBaseInfo) {
        logger.info("主任务{}开始进行通过属性计算策略", autoCreateBaseInfo.getMainTaskNum());

        //TODO

        logger.info("主任务{}通过属性计算策略获取完成,返回点位值为{}", autoCreateBaseInfo.getMainTaskNum(), "");
        return null;
    }
}
