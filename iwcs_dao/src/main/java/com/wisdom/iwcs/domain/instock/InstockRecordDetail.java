package com.wisdom.iwcs.domain.instock;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "instock_record_detail")
public class InstockRecordDetail {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 入库记录id
     */
    @Column(name = "instock_record_id")
    private Integer instockRecordId;

    /**
     * 库存唯一编号
     */
    @Column(name = "stk_code")
    private String stkCode;

    /**
     * 仓位编号
     */
    private String bincode;

    /**
     * 条码
     */
    private String sn;

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
     * @return instockRecordId
     */
    public Integer getInstockRecordId() {
        return instockRecordId;
    }

    /**
     * @return instockRecordId
     */
    public void setInstockRecordId(Integer instockRecordId) {
        this.instockRecordId = instockRecordId;
    }

    /**
     * 获取库存唯一编号
     *
     * @return stk_code - 库存唯一编号
     */
    public String getStkCode() {
        return stkCode;
    }

    /**
     * 设置库存唯一编号
     *
     * @param stkCode 库存唯一编号
     */
    public void setStkCode(String stkCode) {
        this.stkCode = stkCode == null ? null : stkCode.trim();
    }

    /**
     * 获取仓位编号
     *
     * @return bincode - 仓位编号
     */
    public String getBincode() {
        return bincode;
    }

    /**
     * 设置仓位编号
     *
     * @param bincode 仓位编号
     */
    public void setBincode(String bincode) {
        this.bincode = bincode == null ? null : bincode.trim();
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
}