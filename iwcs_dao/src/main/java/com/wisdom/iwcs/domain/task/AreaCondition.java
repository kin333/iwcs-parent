package com.wisdom.iwcs.domain.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AreaCondition {
    /**
     * 作业区域
     */
    private String area;

    /**
     * 业务次级区域
     */
    private String bizSecondArea;

    /**
     * 点位业务类型
     */
    private String bizType;
    /**
     * 货架类型
     */
    private String podType;
}
