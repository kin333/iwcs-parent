package com.wisdom.iwcs.domain.task;

import lombok.Getter;
import lombok.Setter;

/**
 * 货架资源和点位资源的资源对象
 */
@Getter
@Setter
public class PointPodResource {
    /**
     * 货架号
     */
    private String podCode;
    /**
     * 地码编号(别名)
     */
    private String pointAlias;
    /**
     * 任务号
     */
    private String taskCode;
}
