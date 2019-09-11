package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;

public class BasePodAndMapDTO {
    //货架编号
    @Column(name = "pod_code")
    private String podCode;
    //目标点（终点）
    private String point;

    private String indBind;

    public String getIndBind() {
        return indBind;
    }

    public void setIndBind(String indBind) {
        this.indBind = indBind;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
