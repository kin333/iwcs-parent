package com.wisdom.iwcs.service.task.conditions.point;

import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;

/**
 * 创建任务时,获取点位策略规范
 * @author han
 */
public interface IGetPointStrategic {
    /**
     * 获取点位
     * @return
     */
    String getPoint(AutoCreateBaseInfo autoCreateBaseInfo);
}
