package com.wisdom.iwcs.service.task.conditions.strategy.param;

import lombok.Getter;
import lombok.Setter;

/**
 * 点位资源处理策略参数
 * @author han
 */
@Getter
@Setter
public class PointStrategyParams extends BaseStrategyParams {
    /**
     * 点位业务类型
     */
    private String bizType;

    /**
     * 作业区域(如老化区、检验区)
     */
    private String operateAreaCode;

    /**
     * 业务次级区域(如老化区下的自动区、手动区)
     */
    private String bizSecondAreaCode;
}
