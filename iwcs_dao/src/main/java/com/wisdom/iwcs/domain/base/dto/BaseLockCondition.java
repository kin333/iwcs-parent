package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;

/**
 * 锁定条件基础类
 */
public class BaseLockCondition {

    /**
     * 地图编号
     */
    @Column(name = "map_code")
    private String mapCode;

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
     * 货架空满
     * @return
     */
    @Column(name = "in_stock")
    private String inStock;

    /**
     * 业务次级区域(如老化区下的自动区、手动区)
     */
    @Column(name = "biz_second_area_code")
    private String bizSecondAreaCode;

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
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

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }
}
