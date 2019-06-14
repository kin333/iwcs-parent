package com.wisdom.iwcs.domain.outstock;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "outstock_order_sn")
public class OutstockOrderSn {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 出库材料号
     */
    @Column(name = "order_mat_gen_code")
    private String orderMatGenCode;

    /**
     * 条码
     */
    private String sn;

    /**
     * 有效标记，0有效，1无效
     */
    @Column(name = "valid_flag")
    private Integer validFlag;

    /**
     * 删除标记，0未删除，1删除
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 最后修改时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

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
     * 获取出库材料号
     *
     * @return order_mat_gen_code - 出库材料号
     */
    public String getOrderMatGenCode() {
        return orderMatGenCode;
    }

    /**
     * 设置出库材料号
     *
     * @param orderMatGenCode 出库材料号
     */
    public void setOrderMatGenCode(String orderMatGenCode) {
        this.orderMatGenCode = orderMatGenCode == null ? null : orderMatGenCode.trim();
    }

    /**
     * 获取条码
     *
     * @return sn - 条码
     */
    public String getSn() {
        return sn;
    }

    /**
     * 设置条码
     *
     * @param sn 条码
     */
    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public Integer getValidFlag() {
        return validFlag;
    }

    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}