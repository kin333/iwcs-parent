package com.wisdom.iwcs.domain.instock;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "instock_order_detail")
public class InstockOrderDetail {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 入库主单id
     */
    @Column(name = "instaock_order_id")
    private String instaockOrderId;

    /**
     * 业务单据编号（冗余）
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 子单编号
     */
    @Column(name = "sub_order_no")
    private String subOrderNo;

    /**
     * 物料编号
     */
    @Column(name = "mat_code")
    private String matCode;

    /**
     * 工厂编号
     */
    @Column(name = "plant_code")
    private String plantCode;

    /**
     * 优先级
     */
    private String priority;

    /**
     * 批次号
     */
    @Column(name = "batch_num")
    private String batchNum;

    /**
     * 特殊库存标识
     */
    @Column(name = "spe_stk_flag")
    private String speStkFlag;

    /**
     * 特殊库存编号
     */
    @Column(name = "spe_stk_num")
    private String speStkNum;

    /**
     * 预计入库时间
     */
    @Column(name = "expected_date")
    private Date expectedDate;

    /**
     * 物料数量
     */
    @Column(name = "mat_qty")
    private BigDecimal matQty;

    /**
     * 物料单位
     */
    @Column(name = "mat_unit")
    private String matUnit;

    /**
     * 货主
     */
    @Column(name = "cargo_owner")
    private String cargoOwner;

    /**
     * 备注
     */
    private String remark;

    /**
     * 预留子单属性
     */
    @Column(name = "sub_order_prop1")
    private String subOrderProp1;

    /**
     * 预留子单属性
     */
    @Column(name = "sub_order_prop2")
    private String subOrderProp2;

    /**
     * 预留子单属性
     */
    @Column(name = "sub_order_prop3")
    private String subOrderProp3;

    /**
     * 预留子单属性
     */
    @Column(name = "sub_order_prop4")
    private String subOrderProp4;

    /**
     * 预留子单属性
     */
    @Column(name = "sub_order_prop5")
    private String subOrderProp5;

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
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 最后修改人
     */
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    /**
     * 最后修改时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**
     * 已入库数量
     */
    @Column(name = "in_storage_num")
    private BigDecimal inStorageNum;

    /**
     * 单据明细状态,如0：已创建登
     */
    @Column(name = "order_detail_status")
    private String orderDetailStatus;

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
     * 获取入库主单id
     *
     * @return instaock_order_id - 入库主单id
     */
    public String getInstaockOrderId() {
        return instaockOrderId;
    }

    /**
     * 设置入库主单id
     *
     * @param instaockOrderId 入库主单id
     */
    public void setInstaockOrderId(String instaockOrderId) {
        this.instaockOrderId = instaockOrderId == null ? null : instaockOrderId.trim();
    }

    /**
     * 获取业务单据编号（冗余）
     *
     * @return order_no - 业务单据编号（冗余）
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置业务单据编号（冗余）
     *
     * @param orderNo 业务单据编号（冗余）
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 获取子单编号
     *
     * @return sub_order_no - 子单编号
     */
    public String getSubOrderNo() {
        return subOrderNo;
    }

    /**
     * 设置子单编号
     *
     * @param subOrderNo 子单编号
     */
    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo == null ? null : subOrderNo.trim();
    }

    /**
     * 获取物料编号
     *
     * @return mat_code - 物料编号
     */
    public String getMatCode() {
        return matCode;
    }

    /**
     * 设置物料编号
     *
     * @param matCode 物料编号
     */
    public void setMatCode(String matCode) {
        this.matCode = matCode == null ? null : matCode.trim();
    }

    /**
     * 获取工厂编号
     *
     * @return plant_code - 工厂编号
     */
    public String getPlantCode() {
        return plantCode;
    }

    /**
     * 设置工厂编号
     *
     * @param plantCode 工厂编号
     */
    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode == null ? null : plantCode.trim();
    }

    /**
     * 获取优先级
     *
     * @return priority - 优先级
     */
    public String getPriority() {
        return priority;
    }

    /**
     * 设置优先级
     *
     * @param priority 优先级
     */
    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }

    /**
     * 获取批次号
     *
     * @return batch_num - 批次号
     */
    public String getBatchNum() {
        return batchNum;
    }

    /**
     * 设置批次号
     *
     * @param batchNum 批次号
     */
    public void setBatchNum(String batchNum) {
        this.batchNum = batchNum == null ? null : batchNum.trim();
    }

    /**
     * 获取特殊库存标识
     *
     * @return spe_stk_flag - 特殊库存标识
     */
    public String getSpeStkFlag() {
        return speStkFlag;
    }

    /**
     * 设置特殊库存标识
     *
     * @param speStkFlag 特殊库存标识
     */
    public void setSpeStkFlag(String speStkFlag) {
        this.speStkFlag = speStkFlag == null ? null : speStkFlag.trim();
    }

    /**
     * 获取特殊库存编号
     *
     * @return spe_stk_num - 特殊库存编号
     */
    public String getSpeStkNum() {
        return speStkNum;
    }

    /**
     * 设置特殊库存编号
     *
     * @param speStkNum 特殊库存编号
     */
    public void setSpeStkNum(String speStkNum) {
        this.speStkNum = speStkNum == null ? null : speStkNum.trim();
    }

    /**
     * 获取预计入库时间
     *
     * @return expected_date - 预计入库时间
     */
    public Date getExpectedDate() {
        return expectedDate;
    }

    /**
     * 设置预计入库时间
     *
     * @param expectedDate 预计入库时间
     */
    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    /**
     * 获取物料数量
     *
     * @return mat_qty - 物料数量
     */
    public BigDecimal getMatQty() {
        return matQty;
    }

    /**
     * 设置物料数量
     *
     * @param matQty 物料数量
     */
    public void setMatQty(BigDecimal matQty) {
        this.matQty = matQty;
    }

    /**
     * 获取物料单位
     *
     * @return mat_unit - 物料单位
     */
    public String getMatUnit() {
        return matUnit;
    }

    /**
     * 设置物料单位
     *
     * @param matUnit 物料单位
     */
    public void setMatUnit(String matUnit) {
        this.matUnit = matUnit == null ? null : matUnit.trim();
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
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取预留子单属性
     *
     * @return sub_order_prop1 - 预留子单属性
     */
    public String getSubOrderProp1() {
        return subOrderProp1;
    }

    /**
     * 设置预留子单属性
     *
     * @param subOrderProp1 预留子单属性
     */
    public void setSubOrderProp1(String subOrderProp1) {
        this.subOrderProp1 = subOrderProp1 == null ? null : subOrderProp1.trim();
    }

    /**
     * 获取预留子单属性
     *
     * @return sub_order_prop2 - 预留子单属性
     */
    public String getSubOrderProp2() {
        return subOrderProp2;
    }

    /**
     * 设置预留子单属性
     *
     * @param subOrderProp2 预留子单属性
     */
    public void setSubOrderProp2(String subOrderProp2) {
        this.subOrderProp2 = subOrderProp2 == null ? null : subOrderProp2.trim();
    }

    /**
     * 获取预留子单属性
     *
     * @return sub_order_prop3 - 预留子单属性
     */
    public String getSubOrderProp3() {
        return subOrderProp3;
    }

    /**
     * 设置预留子单属性
     *
     * @param subOrderProp3 预留子单属性
     */
    public void setSubOrderProp3(String subOrderProp3) {
        this.subOrderProp3 = subOrderProp3 == null ? null : subOrderProp3.trim();
    }

    /**
     * 获取预留子单属性
     *
     * @return sub_order_prop4 - 预留子单属性
     */
    public String getSubOrderProp4() {
        return subOrderProp4;
    }

    /**
     * 设置预留子单属性
     *
     * @param subOrderProp4 预留子单属性
     */
    public void setSubOrderProp4(String subOrderProp4) {
        this.subOrderProp4 = subOrderProp4 == null ? null : subOrderProp4.trim();
    }

    /**
     * 获取预留子单属性
     *
     * @return sub_order_prop5 - 预留子单属性
     */
    public String getSubOrderProp5() {
        return subOrderProp5;
    }

    /**
     * 设置预留子单属性
     *
     * @param subOrderProp5 预留子单属性
     */
    public void setSubOrderProp5(String subOrderProp5) {
        this.subOrderProp5 = subOrderProp5 == null ? null : subOrderProp5.trim();
    }

    /**
     * 获取有效标记，0有效，1无效
     *
     * @return valid_flag - 有效标记，0有效，1无效
     */
    public Integer getValidFlag() {
        return validFlag;
    }

    /**
     * 设置有效标记，0有效，1无效
     *
     * @param validFlag 有效标记，0有效，1无效
     */
    public void setValidFlag(Integer validFlag) {
        this.validFlag = validFlag;
    }

    /**
     * 获取删除标记，0未删除，1删除
     *
     * @return delete_flag - 删除标记，0未删除，1删除
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置删除标记，0未删除，1删除
     *
     * @param deleteFlag 删除标记，0未删除，1删除
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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

    /**
     * 获取最后修改人
     *
     * @return last_modified_by - 最后修改人
     */
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * 设置最后修改人
     *
     * @param lastModifiedBy 最后修改人
     */
    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * 获取最后修改时间
     *
     * @return last_modified_time - 最后修改时间
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param lastModifiedTime 最后修改时间
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public BigDecimal getInStorageNum() {
        return inStorageNum;
    }

    public void setInStorageNum(BigDecimal inStorageNum) {
        this.inStorageNum = inStorageNum;
    }

    public String getOrderDetailStatus() {
        return orderDetailStatus;
    }

    public void setOrderDetailStatus(String orderDetailStatus) {
        this.orderDetailStatus = orderDetailStatus;
    }
}