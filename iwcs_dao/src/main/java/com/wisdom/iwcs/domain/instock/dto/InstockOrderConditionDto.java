package com.wisdom.iwcs.domain.instock.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class InstockOrderConditionDto {

    /**
     * 单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 单据类型
     */
    @Column(name = "order_type")
    private String orderType;

    /**
     * 预留字段
     */
    @Column(name = "order_prop1")
    private String orderProp1;

    /**
     * 预留字段
     */
    @Column(name = "order_prop2")
    private String orderProp2;

    /**
     * 预留字段
     */
    @Column(name = "order_prop3")
    private String orderProp3;

    /**
     * 预留字段
     */
    @Column(name = "order_prop4")
    private String orderProp4;

    /**
     * 预留字段
     */
    @Column(name = "order_prop5")
    private String orderProp5;

    /**
     * 库区
     */
    private String areaCode;

    /**
     * 查询使用 创建开始时间
     */
    private Long startTime;
    /**
     * 查询使用 创建结束时间
     */
    private Long endTime;

}
