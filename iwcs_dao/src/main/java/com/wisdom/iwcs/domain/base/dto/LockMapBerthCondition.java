package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;

public class LockMapBerthCondition extends BaseLockCondition {

    /**
     * 货架号
     */
    private String podCode;

    /**
     * 点位业务类型（如产线自动工作点、校验点等）
     */
    @Column(name = "biz_type")
    private String bizType;

    /**
     * 地码类型
     */
    @Column(name = "berth_type_value")
    private String berthTypeValue;

    public String getBerthTypeValue() {
        return berthTypeValue;
    }

    public void setBerthTypeValue(String berthTypeValue) {
        this.berthTypeValue = berthTypeValue;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    @Override
    public String getBizType() {
        return bizType;
    }

    @Override
    public void setBizType(String bizType) {
        this.bizType = bizType;
    }
}
