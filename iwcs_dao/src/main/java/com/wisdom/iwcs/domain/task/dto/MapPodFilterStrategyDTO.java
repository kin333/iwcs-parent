package com.wisdom.iwcs.domain.task.dto;

import javax.persistence.*;

@Table(name = "map_pod_filter_strategy")
public class MapPodFilterStrategyDTO {
    @Id
    private Integer id;

    /**
     * 策略编号
     */
    @Column(name = "strategy_code")
    private String strategyCode;

    /**
     * 作业区域(如老化区、检验区)
     */
    @Column(name = "operate_area_code")
    private String operateAreaCode;

    @Column(name = "biz_type")
    private String bizType;

    /**
     * 业务次级区域(如老化区下的自动区、手动区)
     */
    @Column(name = "biz_second_area_code")
    private String bizSecondAreaCode;

    /**
     * 空满状态，0为空，1为满
     */
    @Column(name = "pod_stock")
    private Integer podStock;

    @Column(name = "pod_type")
    private String podType;

    @Column(name = "priority")
    private String priority;
    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrategyCode() {
        return strategyCode;
    }
    public void setStrategyCode(String strategyCode) {
        this.strategyCode = strategyCode == null ? null : strategyCode.trim();
    }

    public String getOperateAreaCode() {
        return operateAreaCode;
    }

    public void setOperateAreaCode(String operateAreaCode) {
        this.operateAreaCode = operateAreaCode == null ? null : operateAreaCode.trim();
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType == null ? null : bizType.trim();
    }

    public String getBizSecondAreaCode() {
        return bizSecondAreaCode;
    }

    public void setBizSecondAreaCode(String bizSecondAreaCode) {
        this.bizSecondAreaCode = bizSecondAreaCode == null ? null : bizSecondAreaCode.trim();
    }

    public Integer getPodStock() {
        return podStock;
    }

    public void setPodStock(Integer podStock) {
        this.podStock = podStock;
    }

    public String getPodType() {
        return podType;
    }

    public void setPodType(String podType) {
        this.podType = podType == null ? null : podType.trim();
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}