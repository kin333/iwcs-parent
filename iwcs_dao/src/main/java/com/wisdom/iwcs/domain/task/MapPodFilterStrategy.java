package com.wisdom.iwcs.domain.task;

import javax.persistence.*;

@Table(name = "map_pod_filter_strategy")
public class MapPodFilterStrategy {
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
     *空满状态，0为空，1为满
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

    /**
     * ��ȡ���Ա��
     *
     * @return strategy_code - ���Ա��
     */
    public String getStrategyCode() {
        return strategyCode;
    }

    /**
     * ���ò��Ա��
     *
     * @param strategyCode ���Ա��
     */
    public void setStrategyCode(String strategyCode) {
        this.strategyCode = strategyCode == null ? null : strategyCode.trim();
    }

    /**
     * ��ȡ��ҵ����(���ϻ�����������)
     *
     * @return operate_area_code - ��ҵ����(���ϻ�����������)
     */
    public String getOperateAreaCode() {
        return operateAreaCode;
    }

    /**
     * ������ҵ����(���ϻ�����������)
     *
     * @param operateAreaCode ��ҵ����(���ϻ�����������)
     */
    public void setOperateAreaCode(String operateAreaCode) {
        this.operateAreaCode = operateAreaCode == null ? null : operateAreaCode.trim();
    }

    /**
     * @return biz_type
     */
    public String getBizType() {
        return bizType;
    }

    /**
     * @param bizType
     */
    public void setBizType(String bizType) {
        this.bizType = bizType == null ? null : bizType.trim();
    }

    /**
     * ��ȡҵ��μ�����(���ϻ����µ��Զ������ֶ���)
     *
     * @return biz_second_area_code - ҵ��μ�����(���ϻ����µ��Զ������ֶ���)
     */
    public String getBizSecondAreaCode() {
        return bizSecondAreaCode;
    }

    /**
     * ����ҵ��μ�����(���ϻ����µ��Զ������ֶ���)
     *
     * @param bizSecondAreaCode ҵ��μ�����(���ϻ����µ��Զ������ֶ���)
     */
    public void setBizSecondAreaCode(String bizSecondAreaCode) {
        this.bizSecondAreaCode = bizSecondAreaCode == null ? null : bizSecondAreaCode.trim();
    }

    /**
     * ��ȡ����״̬��0Ϊ�գ�1Ϊ��
     *
     * @return pod_stock - ����״̬��0Ϊ�գ�1Ϊ��
     */
    public Integer getPodStock() {
        return podStock;
    }

    /**
     * ���ÿ���״̬��0Ϊ�գ�1Ϊ��
     *
     * @param podStock ����״̬��0Ϊ�գ�1Ϊ��
     */
    public void setPodStock(Integer podStock) {
        this.podStock = podStock;
    }

    /**
     * @return pod_type
     */
    public String getPodType() {
        return podType;
    }

    /**
     * @param podType
     */
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