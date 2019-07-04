package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;

public class LockMapBerthCondition extends BaseLockCondition {

    /**
     * 货架号
     */
    private String podCode;

    /**
     * 地码类型,对应字典表
     */
    @Column(name = "berth_type_value")
    private String berthTypeValue;

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getBerthTypeValue() {
        return berthTypeValue;
    }

    public void setBerthTypeValue(String berthTypeValue) {
        this.berthTypeValue = berthTypeValue;
    }
}
