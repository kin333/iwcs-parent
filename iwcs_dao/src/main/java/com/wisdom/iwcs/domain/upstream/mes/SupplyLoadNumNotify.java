package com.wisdom.iwcs.domain.upstream.mes;

import lombok.Getter;
import lombok.Setter;

/**
 * 通知AGV上料数量通知
 * @author han
 */
@Getter
@Setter
public class SupplyLoadNumNotify {

    /**
     * 唯一任务号
     */
    private String taskCode;

    /**
     * 接料数量
     */
    private Integer supplyLoadNum;
}
