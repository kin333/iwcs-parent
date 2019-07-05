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
