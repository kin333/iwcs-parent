package com.wisdom.iwcs.domain.outstock;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Table(name = "outstock_biz_order")
public class OutstockBizOrder {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 业务单据编号
     */
    @Column(name = "biz_order_code")
    private String bizOrderCode;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 订单类型，如上游创建订单、系统虚拟订单
     */
    @Column(name = "order_type")
    private String orderType;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OutstockBizOrder)) return false;
        OutstockBizOrder that = (OutstockBizOrder) o;
        return Objects.equals(bizOrderCode, that.bizOrderCode);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bizOrderCode);
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取自增主键
     *
     * @return id - 自增主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取业务单据编号
     *
     * @return biz_order_code - 业务单据编号
     */
    public String getBizOrderCode() {
        return bizOrderCode;
    }

    /**
     * 设置业务单据编号
     *
     * @param bizOrderCode 业务单据编号
     */
    public void setBizOrderCode(String bizOrderCode) {
        this.bizOrderCode = bizOrderCode == null ? null : bizOrderCode.trim();
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}