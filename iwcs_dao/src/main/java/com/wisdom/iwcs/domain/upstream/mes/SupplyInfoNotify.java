package com.wisdom.iwcs.domain.upstream.mes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 通知AGV接料点目的地信息类
 * @author han
 */
@Getter
@Setter
@ToString
public class SupplyInfoNotify {
    /**
     * 唯一任务号，必填项
     */
    private String taskCode;
    /**
     * 接料点1 必填项，
     */
    private String supplyUnLoadWbFirst;
    /**
     * 接料点2 选填项，
     */
    private String supplyUnLoadWbSecond;
    /**
     * 接料点1接料数量 必填项，
     */
    private Integer supplyUnLoadWbFirstCount;
    /**
     * 接料点2接料数量 选填项，
     */
    private Integer supplyUnLoadWbSecondCount;

    /**
     * 超越接料点
     */
    private String supplyUnLoadWb;


}
