package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;

public class LockMapBerthCondition {

    /**
     * 地图编号
     */
    @Column(name = "map_code")
    private String mapCode;

    /**
     * 货架号
     */
    private String podCode;
    /**
     * 锁定源
     */
    private String lockSource;

    /**
     * 点位业务类型
     */
    @Column(name = "biz_type")
    private String bizType;

    /**
     * 作业区域(如老化区、检验区)
     */
    @Column(name = "operate_area_code")
    private String operateAreaCode;

    /**
     * 业务次级区域(如老化区下的自动区、手动区)
     */
    @Column(name = "biz_second_area_code")
    private String bizSecondAreaCode;

    /**
     * 地码类型,对应字典表
     */
    @Column(name = "berth_type_value")
    private String berthTypeValue;

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getLockSource() {
        return lockSource;
    }

    public void setLockSource(String lockSource) {
        this.lockSource = lockSource;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getOperateAreaCode() {
        return operateAreaCode;
    }

    public void setOperateAreaCode(String operateAreaCode) {
        this.operateAreaCode = operateAreaCode;
    }

    public String getBizSecondAreaCode() {
        return bizSecondAreaCode;
    }

    public void setBizSecondAreaCode(String bizSecondAreaCode) {
        this.bizSecondAreaCode = bizSecondAreaCode;
    }

    public String getBerthTypeValue() {
        return berthTypeValue;
    }

    public void setBerthTypeValue(String berthTypeValue) {
        this.berthTypeValue = berthTypeValue;
    }


}
