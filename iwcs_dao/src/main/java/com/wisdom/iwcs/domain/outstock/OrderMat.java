package com.wisdom.iwcs.domain.outstock;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "outstock_order_mat")
public class OrderMat {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 出库材料唯一标识码（自动生成）
     */
    @Column(name = "order_mat_gen_code")
    private String orderMatGenCode;

    /**
     * 业务订单号
     */
    @Column(name = "biz_order_code")
    private String bizOrderCode;

    /**
     * 上游item
     */
    @Column(name = "src_order_item")
    private String srcOrderItem;

    /**
     * 物料号
     */
    @Column(name = "mat_code")
    private String matCode;

    /**
     * 计划出库数量
     */
    @Column(name = "out_qty")
    private BigDecimal outQty;

    /**
     * 批次
     */
    @Column(name = "batch_num")
    private String batchNum;

    /**
     * 货主
     */
    @Column(name = "cargo_owner")
    private String cargoOwner;

    /**
     * 库区
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 出库单位:计量单位、容器
     */
    @Column(name = "Out_unit")
    private String outUnit;

    /**
     * 是否指定SN出库，0为否，1为是
     */
    @Column(name = "pre_sn_flag")
    private String preSnFlag;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 已出库数量
     */
    @Column(name = "out_storage_num")
    private BigDecimal outStorageNum;

    /**
     * 单据明细状态,如0：已创建登
     */
    @Column(name = "order_mat_status")
    private String orderMatStatus;

    /**
     * 特征值1
     */
    @Column(name = "stk_character1")
    private String stkCharacter1;

    /**
     * 特征值2
     */
    @Column(name = "stk_character2")
    private String stkCharacter2;

    /**
     * 特征值3
     */
    @Column(name = "stk_character3")
    private String stkCharacter3;

    /**
     * 特征值4
     */
    @Column(name = "stk_character4")
    private String stkCharacter4;

    /**
     * 特征值5
     */
    @Column(name = "stk_character5")
    private String stkCharacter5;

    /**
     * 完整订单号，上游biz_order_code和src_order_item拼接，批量查询用
     */
    @Column(name = "biz_whole_order_no")
    private String bizWholeOrderNo;
    /**
     * 容器编号
     */
    @Column(name = "container_code")
    private String containerCode;
    /**
     * 规格编号
     */
    @Column(name = "spec_code")
    private String specCode;

    /**
     * 出库时间
     */
    @Column(name = "outstock_time")
    private Date outstockTime;

    public Date getOutstockTime() {
        return outstockTime;
    }

    public void setOutstockTime(Date outstockTime) {
        this.outstockTime = outstockTime;
    }

    public String getContainerCode() {
        return containerCode;
    }

    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode;
    }

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }

    public String getBizWholeOrderNo() {
        return bizWholeOrderNo;
    }

    public void setBizWholeOrderNo(String bizWholeOrderNo) {
        this.bizWholeOrderNo = bizWholeOrderNo;
    }

    public String getStkCharacter1() {
        return stkCharacter1;
    }

    public void setStkCharacter1(String stkCharacter1) {
        this.stkCharacter1 = stkCharacter1;
    }

    public String getStkCharacter2() {
        return stkCharacter2;
    }

    public void setStkCharacter2(String stkCharacter2) {
        this.stkCharacter2 = stkCharacter2;
    }

    public String getStkCharacter3() {
        return stkCharacter3;
    }

    public void setStkCharacter3(String stkCharacter3) {
        this.stkCharacter3 = stkCharacter3;
    }

    public String getStkCharacter4() {
        return stkCharacter4;
    }

    public void setStkCharacter4(String stkCharacter4) {
        this.stkCharacter4 = stkCharacter4;
    }

    public String getStkCharacter5() {
        return stkCharacter5;
    }

    public void setStkCharacter5(String stkCharacter5) {
        this.stkCharacter5 = stkCharacter5;
    }

    public String getOrderMatStatus() {
        return orderMatStatus;
    }

    public void setOrderMatStatus(String orderMatStatus) {
        this.orderMatStatus = orderMatStatus;
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
     * 获取出库材料唯一标识码（自动生成）
     *
     * @return order_mat_gen_code - 出库材料唯一标识码（自动生成）
     */
    public String getOrderMatGenCode() {
        return orderMatGenCode;
    }

    /**
     * 设置出库材料唯一标识码（自动生成）
     *
     * @param orderMatGenCode 出库材料唯一标识码（自动生成）
     */
    public void setOrderMatGenCode(String orderMatGenCode) {
        this.orderMatGenCode = orderMatGenCode == null ? null : orderMatGenCode.trim();
    }

    /**
     * 获取业务订单号
     *
     * @return biz_order_code - 业务订单号
     */
    public String getBizOrderCode() {
        return bizOrderCode;
    }


    /**
     * 设置业务订单号
     *
     * @param bizOrderCode 业务订单号
     */
    public void setBizOrderCode(String bizOrderCode) {
        this.bizOrderCode = bizOrderCode;
    }

    /**
     * 获取上游item
     *
     * @return src_order_item - 上游item
     */
    public String getSrcOrderItem() {
        return srcOrderItem;
    }

    /**
     * 设置上游item
     *
     * @param srcOrderItem 上游item
     */
    public void setSrcOrderItem(String srcOrderItem) {
        this.srcOrderItem = srcOrderItem == null ? null : srcOrderItem.trim();
    }

    /**
     * 获取物料号
     *
     * @return mat_code - 物料号
     */
    public String getMatCode() {
        return matCode;
    }

    /**
     * 设置物料号
     *
     * @param matCode 物料号
     */
    public void setMatCode(String matCode) {
        this.matCode = matCode == null ? null : matCode.trim();
    }

    /**
     * 获取计划出库数量
     *
     * @return out_qty - 计划出库数量
     */
    public BigDecimal getOutQty() {
        return outQty;
    }

    /**
     * 设置计划出库数量
     *
     * @param outQty 计划出库数量
     */
    public void setOutQty(BigDecimal outQty) {
        this.outQty = outQty;
    }

    /**
     * 获取批次
     *
     * @return batch_num - 批次
     */
    public String getBatchNum() {
        return batchNum;
    }

    /**
     * 设置批次
     *
     * @param batchNum 批次
     */
    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
    }

    /**
     * 获取货主
     *
     * @return cargo_owner - 货主
     */
    public String getCargoOwner() {
        return cargoOwner;
    }

    /**
     * 设置货主
     *
     * @param cargoOwner 货主
     */
    public void setCargoOwner(String cargoOwner) {
        this.cargoOwner = cargoOwner == null ? null : cargoOwner.trim();
    }

    /**
     * 获取库区
     *
     * @return area_code - 库区
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置库区
     *
     * @param areaCode 库区
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 获取出库单位:计量单位、容器
     *
     * @return Out_unit - 出库单位:计量单位、容器
     */
    public String getOutUnit() {
        return outUnit;
    }

    /**
     * 设置出库单位:计量单位、容器
     *
     * @param outUnit 出库单位:计量单位、容器
     */
    public void setOutUnit(String outUnit) {
        this.outUnit = outUnit == null ? null : outUnit.trim();
    }

    /**
     * 获取是否指定SN出库，0为否，1为是
     *
     * @return pre_sn_flag - 是否指定SN出库，0为否，1为是
     */
    public String getPreSnFlag() {
        return preSnFlag;
    }

    /**
     * 设置是否指定SN出库，0为否，1为是
     *
     * @param preSnFlag 是否指定SN出库，0为否，1为是
     */
    public void setPreSnFlag(String preSnFlag) {
        this.preSnFlag = preSnFlag == null ? null : preSnFlag.trim();
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

    public BigDecimal getOutStorageNum() {
        return outStorageNum;
    }

    public void setOutStorageNum(BigDecimal outStorageNum) {
        this.outStorageNum = outStorageNum;
    }
}