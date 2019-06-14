package com.wisdom.iwcs.domain.instock;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "instock_record")
public class InstockRecord {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 入库业务类型：normal(正常入库)、异常库存调整（exception_adjust）等
     */
    @Column(name = "instk_biz_type")
    private String instkBizType;

    /**
     * 库存唯一编号
     */
    @Column(name = "stk_code")
    private String stkCode;

    /**
     * 业务单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 子单号
     */
    @Column(name = "sub_order_no")
    private String subOrderNo;

    /**
     * 货主
     */
    @Column(name = "cargo_owner")
    private String cargoOwner;

    /**
     * 物料号
     */
    @Column(name = "mat_code")
    private String matCode;

    /**
     * 入库方式，0按数量，1按条码
     */
    @Column(name = "instock_type")
    private String instockType;

    /**
     * 仓位编号
     */
    private String bincode;

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
     * 总数量
     */
    @Column(name = "total_qty")
    private BigDecimal totalQty;

    /**
     * 仓库编号
     */
    @Column(name = "wh_code")
    private String whCode;

    /**
     * 库区编号
     */
    @Column(name = "area_code")
    private String areaCode;

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
     * 按容器管理标识，0否，1是
     */
    @Column(name = "container_flag")
    private String containerFlag;

    /**
     * 包装规格数量，按容器管理时使用，其他为0
     */
    @Column(name = "sku_qty")
    private BigDecimal skuQty;

    /**
     * 特殊库存编号
     */
    @Column(name = "spe_stk_code")
    private String speStkCode;

    /**
     * 库存冻结标识，0未冻结，1冻结
     */
    @Column(name = "freeze_flag")
    private String freezeFlag;

    /**
     * 冻结原因
     */
    @Column(name = "blk_rea_code")
    private String blkReaCode;

    /**
     * 基本计量单位
     */
    @Column(name = "bum_code")
    private String bumCode;

    /**
     * 单位物料重量
     */
    @Column(name = "mat_weight")
    private BigDecimal matWeight;

    /**
     * 重量单位
     */
    @Column(name = "weight_unit")
    private String weightUnit;

    /**
     * 工厂
     */
    @Column(name = "plant_code")
    private String plantCode;

    /**
     * 容器编号
     */
    @Column(name = "container_code")
    private String containerCode;

    /**
     * 上游呼叫编号
     */
    @Column(name = "src_biz_req_code")
    private String srcBizReqCode;

    /**
     * 预留字段
     */
    @Column(name = "record_prop1")
    private String recordProp1;

    /**
     * 预留字段
     */
    @Column(name = "record_prop2")
    private String recordProp2;

    /**
     * 预留字段
     */
    @Column(name = "record_prop3")
    private String recordProp3;

    /**
     * 备注
     */
    private String remark;

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
     * 上游用户编码
     */
    @Column(name = "src_user_code")
    private String srcUserCode;

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
     * 获取入库业务类型：normal(正常入库)、异常库存调整（exception_adjust）等
     *
     * @return instk_biz_type - 入库业务类型：normal(正常入库)、异常库存调整（exception_adjust）等
     */
    public String getInstkBizType() {
        return instkBizType;
    }

    /**
     * 设置入库业务类型：normal(正常入库)、异常库存调整（exception_adjust）等
     *
     * @param instkBizType 入库业务类型：normal(正常入库)、异常库存调整（exception_adjust）等
     */
    public void setInstkBizType(String instkBizType) {
        this.instkBizType = instkBizType == null ? null : instkBizType.trim();
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
     * 获取业务单号
     *
     * @return order_no - 业务单号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置业务单号
     *
     * @param orderNo 业务单号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    /**
     * 获取子单号
     *
     * @return sub_order_no - 子单号
     */
    public String getSubOrderNo() {
        return subOrderNo;
    }

    /**
     * 设置子单号
     *
     * @param subOrderNo 子单号
     */
    public void setSubOrderNo(String subOrderNo) {
        this.subOrderNo = subOrderNo == null ? null : subOrderNo.trim();
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
     * 获取入库方式，0按数量，1按条码
     *
     * @return instock_type - 入库方式，0按数量，1按条码
     */
    public String getInstockType() {
        return instockType;
    }

    /**
     * 设置入库方式，0按数量，1按条码
     *
     * @param instockType 入库方式，0按数量，1按条码
     */
    public void setInstockType(String instockType) {
        this.instockType = instockType == null ? null : instockType.trim();
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
     * 获取特征值1
     *
     * @return stk_character1 - 特征值1
     */
    public String getStkCharacter1() {
        return stkCharacter1;
    }

    /**
     * 设置特征值1
     *
     * @param stkCharacter1 特征值1
     */
    public void setStkCharacter1(String stkCharacter1) {
        this.stkCharacter1 = stkCharacter1 == null ? null : stkCharacter1.trim();
    }

    /**
     * 获取特征值2
     *
     * @return stk_character2 - 特征值2
     */
    public String getStkCharacter2() {
        return stkCharacter2;
    }

    /**
     * 设置特征值2
     *
     * @param stkCharacter2 特征值2
     */
    public void setStkCharacter2(String stkCharacter2) {
        this.stkCharacter2 = stkCharacter2 == null ? null : stkCharacter2.trim();
    }

    /**
     * 获取特征值3
     *
     * @return stk_character3 - 特征值3
     */
    public String getStkCharacter3() {
        return stkCharacter3;
    }

    /**
     * 设置特征值3
     *
     * @param stkCharacter3 特征值3
     */
    public void setStkCharacter3(String stkCharacter3) {
        this.stkCharacter3 = stkCharacter3 == null ? null : stkCharacter3.trim();
    }

    /**
     * 获取特征值4
     *
     * @return stk_character4 - 特征值4
     */
    public String getStkCharacter4() {
        return stkCharacter4;
    }

    /**
     * 设置特征值4
     *
     * @param stkCharacter4 特征值4
     */
    public void setStkCharacter4(String stkCharacter4) {
        this.stkCharacter4 = stkCharacter4 == null ? null : stkCharacter4.trim();
    }

    /**
     * 获取特征值5
     *
     * @return stk_character5 - 特征值5
     */
    public String getStkCharacter5() {
        return stkCharacter5;
    }

    /**
     * 设置特征值5
     *
     * @param stkCharacter5 特征值5
     */
    public void setStkCharacter5(String stkCharacter5) {
        this.stkCharacter5 = stkCharacter5 == null ? null : stkCharacter5.trim();
    }

    /**
     * 获取总数量
     *
     * @return total_qty - 总数量
     */
    public BigDecimal getTotalQty() {
        return totalQty;
    }

    /**
     * 设置总数量
     *
     * @param totalQty 总数量
     */
    public void setTotalQty(BigDecimal totalQty) {
        this.totalQty = totalQty;
    }

    /**
     * 获取仓库编号
     *
     * @return wh_code - 仓库编号
     */
    public String getWhCode() {
        return whCode;
    }

    /**
     * 设置仓库编号
     *
     * @param whCode 仓库编号
     */
    public void setWhCode(String whCode) {
        this.whCode = whCode == null ? null : whCode.trim();
    }

    /**
     * 获取库区编号
     *
     * @return area_code - 库区编号
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置库区编号
     *
     * @param areaCode 库区编号
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
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
     * 获取按容器管理标识，0否，1是
     *
     * @return container_flag - 按容器管理标识，0否，1是
     */
    public String getContainerFlag() {
        return containerFlag;
    }

    /**
     * 设置按容器管理标识，0否，1是
     *
     * @param containerFlag 按容器管理标识，0否，1是
     */
    public void setContainerFlag(String containerFlag) {
        this.containerFlag = containerFlag == null ? null : containerFlag.trim();
    }

    /**
     * 获取包装规格数量，按容器管理时使用，其他为0
     *
     * @return sku_qty - 包装规格数量，按容器管理时使用，其他为0
     */
    public BigDecimal getSkuQty() {
        return skuQty;
    }

    /**
     * 设置包装规格数量，按容器管理时使用，其他为0
     *
     * @param skuQty 包装规格数量，按容器管理时使用，其他为0
     */
    public void setSkuQty(BigDecimal skuQty) {
        this.skuQty = skuQty;
    }

    /**
     * 获取特殊库存编号
     *
     * @return spe_stk_code - 特殊库存编号
     */
    public String getSpeStkCode() {
        return speStkCode;
    }

    /**
     * 设置特殊库存编号
     *
     * @param speStkCode 特殊库存编号
     */
    public void setSpeStkCode(String speStkCode) {
        this.speStkCode = speStkCode == null ? null : speStkCode.trim();
    }

    /**
     * 获取库存冻结标识，0未冻结，1冻结
     *
     * @return freeze_flag - 库存冻结标识，0未冻结，1冻结
     */
    public String getFreezeFlag() {
        return freezeFlag;
    }

    /**
     * 设置库存冻结标识，0未冻结，1冻结
     *
     * @param freezeFlag 库存冻结标识，0未冻结，1冻结
     */
    public void setFreezeFlag(String freezeFlag) {
        this.freezeFlag = freezeFlag == null ? null : freezeFlag.trim();
    }

    /**
     * 获取冻结原因
     *
     * @return blk_rea_code - 冻结原因
     */
    public String getBlkReaCode() {
        return blkReaCode;
    }

    /**
     * 设置冻结原因
     *
     * @param blkReaCode 冻结原因
     */
    public void setBlkReaCode(String blkReaCode) {
        this.blkReaCode = blkReaCode == null ? null : blkReaCode.trim();
    }

    /**
     * 获取基本计量单位
     *
     * @return bum_code - 基本计量单位
     */
    public String getBumCode() {
        return bumCode;
    }

    /**
     * 设置基本计量单位
     *
     * @param bumCode 基本计量单位
     */
    public void setBumCode(String bumCode) {
        this.bumCode = bumCode == null ? null : bumCode.trim();
    }

    /**
     * 获取单位物料重量
     *
     * @return mat_weight - 单位物料重量
     */
    public BigDecimal getMatWeight() {
        return matWeight;
    }

    /**
     * 设置单位物料重量
     *
     * @param matWeight 单位物料重量
     */
    public void setMatWeight(BigDecimal matWeight) {
        this.matWeight = matWeight;
    }

    /**
     * 获取重量单位
     *
     * @return weight_unit - 重量单位
     */
    public String getWeightUnit() {
        return weightUnit;
    }

    /**
     * 设置重量单位
     *
     * @param weightUnit 重量单位
     */
    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit == null ? null : weightUnit.trim();
    }

    /**
     * 获取工厂
     *
     * @return plant_code - 工厂
     */
    public String getPlantCode() {
        return plantCode;
    }

    /**
     * 设置工厂
     *
     * @param plantCode 工厂
     */
    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode == null ? null : plantCode.trim();
    }

    /**
     * 获取容器编号
     *
     * @return container_code - 容器编号
     */
    public String getContainerCode() {
        return containerCode;
    }

    /**
     * 设置容器编号
     *
     * @param containerCode 容器编号
     */
    public void setContainerCode(String containerCode) {
        this.containerCode = containerCode == null ? null : containerCode.trim();
    }

    /**
     * 获取上游呼叫编号
     *
     * @return src_biz_req_code - 上游呼叫编号
     */
    public String getSrcBizReqCode() {
        return srcBizReqCode;
    }

    /**
     * 设置上游呼叫编号
     *
     * @param srcBizReqCode 上游呼叫编号
     */
    public void setSrcBizReqCode(String srcBizReqCode) {
        this.srcBizReqCode = srcBizReqCode == null ? null : srcBizReqCode.trim();
    }

    /**
     * 获取预留字段
     *
     * @return record_prop1 - 预留字段
     */
    public String getRecordProp1() {
        return recordProp1;
    }

    /**
     * 设置预留字段
     *
     * @param recordProp1 预留字段
     */
    public void setRecordProp1(String recordProp1) {
        this.recordProp1 = recordProp1 == null ? null : recordProp1.trim();
    }

    /**
     * 获取预留字段
     *
     * @return record_prop2 - 预留字段
     */
    public String getRecordProp2() {
        return recordProp2;
    }

    /**
     * 设置预留字段
     *
     * @param recordProp2 预留字段
     */
    public void setRecordProp2(String recordProp2) {
        this.recordProp2 = recordProp2 == null ? null : recordProp2.trim();
    }

    /**
     * 获取预留字段
     *
     * @return record_prop3 - 预留字段
     */
    public String getRecordProp3() {
        return recordProp3;
    }

    /**
     * 设置预留字段
     *
     * @param recordProp3 预留字段
     */
    public void setRecordProp3(String recordProp3) {
        this.recordProp3 = recordProp3 == null ? null : recordProp3.trim();
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
     * 获取上游用户编码
     *
     * @return src_user_code - 上游用户编码
     */
    public String getSrcUserCode() {
        return srcUserCode;
    }

    /**
     * 设置上游用户编码
     *
     * @param srcUserCode 上游用户编码
     */
    public void setSrcUserCode(String srcUserCode) {
        this.srcUserCode = srcUserCode == null ? null : srcUserCode.trim();
    }
}