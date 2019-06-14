package com.wisdom.iwcs.domain.outstock.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "outstock_mat_config_relation")
public class MatConfigRelationDTO {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 配置号
     */
    @Column(name = "config_code")
    private String configCode;

    /**
     * 出库材料编号
     */
    @Column(name = "order_mat_gen_code")
    private String orderMatGenCode;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

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
     * 获取配置号
     *
     * @return config_code - 配置号
     */
    public String getConfigCode() {
        return configCode;
    }

    /**
     * 设置配置号
     *
     * @param configCode 配置号
     */
    public void setConfigCode(String configCode) {
        this.configCode = configCode == null ? null : configCode.trim();
    }

    /**
     * 获取出库材料编号
     *
     * @return order_mat_gen_code - 出库材料编号
     */
    public String getOrderMatGenCode() {
        return orderMatGenCode;
    }

    /**
     * 设置出库材料编号
     *
     * @param orderMatGenCode 出库材料编号
     */
    public void setOrderMatGenCode(String orderMatGenCode) {
        this.orderMatGenCode = orderMatGenCode == null ? null : orderMatGenCode.trim();
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