package com.wisdom.iwcs.domain.outstock;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "outstock_record")
public class OutstockRecord {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 出库类型：normal,exception_adjust
     */
    @Column(name = "outstk_type")
    private String outstkType;

    /**
     * 库存编号
     */
    @Column(name = "stk_code")
    private String stkCode;

    /**
     * 出库配置号
     */
    @Column(name = "config_code")
    private String configCode;

    /**
     * 业务单号
     */
    @Column(name = "biz_order_code")
    private String bizOrderCode;

    /**
     * 业务单项，如行号
     */
    @Column(name = "biz_order_item")
    private String bizOrderItem;

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
     * 货架号
     */
    private String bincode;

    /**
     * 出库方式，0按数量，1按条码
     */
    @Column(name = "outstock_type")
    private String outstockType;

    /**
     * 出库数量
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
    @Column(name = "stg_area_code")
    private String stgAreaCode;

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
    @Column(name = "spe_stk_code")
    private String speStkCode;

    /**
     * 库存冻结标识，0未冻结，1已冻结
     */
    @Column(name = "freeze_flag")
    private String freezeFlag;

    /**
     * 容器编号
     */
    @Column(name = "container_code")
    private String containerCode;

    /**
     * 按容器管理标识，0否，1是
     */
    @Column(name = "container_flag")
    private String containerFlag;

    /**
     * 包装规格数量，按容器管理时启用，其他为0
     */
    @Column(name = "sku_qty")
    private BigDecimal skuQty;

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
     * 上游呼叫编号
     */
    @Column(name = "src_biz_req_code")
    private String srcBizReqCode;

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
     * 上游用户编码
     */
    @Column(name = "src_user_code")
    private String srcUserCode;

    /**
     * 规格编号
     */
    @Column(name = "spec_code")
    private String specCode;

    public String getSpecCode() {
        return specCode;
    }

    public void setSpecCode(String specCode) {
        this.specCode = specCode;
    }

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
     * 获取出库类型：normal,exception_adjust
     *
     * @return outstk_type - 出库类型：normal,exception_adjust
     */
    public String getOutstkType() {
        return outstkType;
    }

    /**
     * 设置出库类型：normal,exception_adjust
     *
     * @param outstkType 出库类型：normal,exception_adjust
     */
    public void setOutstkType(String outstkType) {
        this.outstkType = outstkType == null ? null : outstkType.trim();
    }

    /**
     * 获取库存编号
     *
     * @return stk_code - 库存编号
     */
    public String getStkCode() {
        return stkCode;
    }

    /**
     * 设置库存编号
     *
     * @param stkCode 库存编号
     */
    public void setStkCode(String stkCode) {
        this.stkCode = stkCode == null ? null : stkCode.trim();
    }

    /**
     * 获取出库配置号
     *
     * @return config_code - 出库配置号
     */
    public String getConfigCode() {
        return configCode;
    }

    /**
     * 设置出库配置号
     *
     * @param configCode 出库配置号
     */
    public void setConfigCode(String configCode) {
        this.configCode = configCode == null ? null : configCode.trim();
    }

    /**
     * 获取业务单号
     *
     * @return biz_order_code - 业务单号
     */
    public String getBizOrderCode() {
        return bizOrderCode;
    }

    /**
     * 设置业务单号
     *
     * @param bizOrderCode 业务单号
     */
    public void setBizOrderCode(String bizOrderCode) {
        this.bizOrderCode = bizOrderCode == null ? null : bizOrderCode.trim();
    }

    /**
     * 获取业务单项，如行号
     *
     * @return biz_order_item - 业务单项，如行号
     */
    public String getBizOrderItem() {
        return bizOrderItem;
    }

    /**
     * 设置业务单项，如行号
     *
     * @param bizOrderItem 业务单项，如行号
     */
    public void setBizOrderItem(String bizOrderItem) {
        this.bizOrderItem = bizOrderItem == null ? null : bizOrderItem.trim();
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
     * 获取货架号
     *
     * @return bincode - 货架号
     */
    public String getBincode() {
        return bincode;
    }

    /**
     * 设置货架号
     *
     * @param bincode 货架号
     */
    public void setBincode(String bincode) {
        this.bincode = bincode == null ? null : bincode.trim();
    }

    /**
     * 获取出库方式，0按数量，1按条码
     *
     * @return outstock_type - 出库方式，0按数量，1按条码
     */
    public String getOutstockType() {
        return outstockType;
    }

    /**
     * 设置出库方式，0按数量，1按条码
     *
     * @param outstockType 出库方式，0按数量，1按条码
     */
    public void setOutstockType(String outstockType) {
        this.outstockType = outstockType == null ? null : outstockType.trim();
    }

    /**
     * 获取出库数量
     *
     * @return total_qty - 出库数量
     */
    public BigDecimal getTotalQty() {
        return totalQty;
    }

    /**
     * 设置出库数量
     *
     * @param totalQty 出库数量
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
     * @return stg_area_code - 库区编号
     */
    public String getStgAreaCode() {
        return stgAreaCode;
    }

    /**
     * 设置库区编号
     *
     * @param stgAreaCode 库区编号
     */
    public void setStgAreaCode(String stgAreaCode) {
        this.stgAreaCode = stgAreaCode == null ? null : stgAreaCode.trim();
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
     * 获取库存冻结标识，0未冻结，1已冻结
     *
     * @return freeze_flag - 库存冻结标识，0未冻结，1已冻结
     */
    public String getFreezeFlag() {
        return freezeFlag;
    }

    /**
     * 设置库存冻结标识，0未冻结，1已冻结
     *
     * @param freezeFlag 库存冻结标识，0未冻结，1已冻结
     */
    public void setFreezeFlag(String freezeFlag) {
        this.freezeFlag = freezeFlag == null ? null : freezeFlag.trim();
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
     * 获取包装规格数量，按容器管理时启用，其他为0
     *
     * @return sku_qty - 包装规格数量，按容器管理时启用，其他为0
     */
    public BigDecimal getSkuQty() {
        return skuQty;
    }

    /**
     * 设置包装规格数量，按容器管理时启用，其他为0
     *
     * @param skuQty 包装规格数量，按容器管理时启用，其他为0
     */
    public void setSkuQty(BigDecimal skuQty) {
        this.skuQty = skuQty;
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