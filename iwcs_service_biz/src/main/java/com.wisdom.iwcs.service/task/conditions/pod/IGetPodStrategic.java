package com.wisdom.iwcs.service.task.conditions.pod;

import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;

/**
 * 创建任务时,获取载具策略规范
 * @author han
 */
public interface IGetPodStrategic {
    /**
     * 获取载具(货架)
     * @return
     */
    String getPod(AutoCreateBaseInfo autoCreateBaseInfo);
}
