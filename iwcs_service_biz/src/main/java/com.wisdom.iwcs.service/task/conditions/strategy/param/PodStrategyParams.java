package com.wisdom.iwcs.service.task.conditions.strategy.param;

import lombok.Getter;
import lombok.Setter;

/**
 * 货架资源处理策略参数
 * @author han
 */
@Getter
@Setter
public class PodStrategyParams extends BaseStrategyParams {
    /**
     * 货架类型
     */
    private String podType;
}
