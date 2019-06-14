package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "base_mat")
public class BaseMatDTO {
    @Id
    private Integer id;

    /**
     * 物料编码
     */
    @Column(name = "mat_code")
    private String matCode;

    /**
     * 基本计量单位
     */
    @Column(name = "bum_code")
    private String bumCode;

    /**
     * 采购单位
     */
    @Column(name = "pum_code")
    private String pumCode;

    /**
     * 毛重
     */
    @Column(name = "gross_weight")
    private BigDecimal grossWeight;

    /**
     * 净重
     */
    @Column(name = "net_weight")
    private BigDecimal netWeight;

    /**
     * 重量单位
     */
    @Column(name = "weight_unit")
    private String weightUnit;

    /**
     * 长度
     */
    private BigDecimal length;

    /**
     * 宽度
     */
    private BigDecimal width;

    /**
     * 高度
     */
    private BigDecimal height;

    /**
     * 尺寸单位
     */
    @Column(name = "dim_unit")
    private String dimUnit;

    /**
     * 最小安全库存
     */
    @Column(name = "min_stk_qty")
    private BigDecimal minStkQty;

    /**
     * 最大安全库存
     */
    @Column(name = "max_stk_qty")
    private BigDecimal maxStkQty;

    /**
     * 按容器管理
     */
    @Column(name = "cntr_flag")
    private String cntrFlag;

    /**
     * 寿命期，可以为空，单位天
     */
    @Column(name = "validate_lifetime")
    private BigDecimal validateLifetime;

    /**
     * 过期预警时间，可以为空，单位天
     */
    @Column(name = "expire_warn_time")
    private BigDecimal expireWarnTime;

    /**
     * 是否允许过期物料出库，0否，1是
     */
    @Column(name = "expire_mat_out")
    private String expireMatOut;

    /**
     * 物料类型code
     */
    @Column(name = "mat_type_code")
    private String matTypeCode;

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
     * 货物描述
     */
    @Column(name = "carnm")
    private String carnm;

    /**
     * 是否有规格，0为否，1为是
     */
    @Column(name = "spec_flag")
    private String specFlag;

    private BaseMatPackageSpecDTO baseMatPackageSpecDTO;

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
     * 获取物料编码
     *
     * @return mat_code - 物料编码
     */
    public String getMatCode() {
        return matCode;
    }

    /**
     * 设置物料编码
     *
     * @param matCode 物料编码
     */
    public void setMatCode(String matCode) {
        this.matCode = matCode == null ? null : matCode.trim();
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
     * 获取采购单位
     *
     * @return pum_code - 采购单位
     */
    public String getPumCode() {
        return pumCode;
    }

    /**
     * 设置采购单位
     *
     * @param pumCode 采购单位
     */
    public void setPumCode(String pumCode) {
        this.pumCode = pumCode == null ? null : pumCode.trim();
    }

    /**
     * 获取毛重
     *
     * @return gross_weight - 毛重
     */
    public BigDecimal getGrossWeight() {
        return grossWeight;
    }

    /**
     * 设置毛重
     *
     * @param grossWeight 毛重
     */
    public void setGrossWeight(BigDecimal grossWeight) {
        this.grossWeight = grossWeight;
    }

    /**
     * 获取净重
     *
     * @return net_weight - 净重
     */
    public BigDecimal getNetWeight() {
        return netWeight;
    }

    /**
     * 设置净重
     *
     * @param netWeight 净重
     */
    public void setNetWeight(BigDecimal netWeight) {
        this.netWeight = netWeight;
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
     * 获取长度
     *
     * @return length - 长度
     */
    public BigDecimal getLength() {
        return length;
    }

    /**
     * 设置长度
     *
     * @param length 长度
     */
    public void setLength(BigDecimal length) {
        this.length = length;
    }

    /**
     * 获取宽度
     *
     * @return width - 宽度
     */
    public BigDecimal getWidth() {
        return width;
    }

    /**
     * 设置宽度
     *
     * @param width 宽度
     */
    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    /**
     * 获取高度
     *
     * @return height - 高度
     */
    public BigDecimal getHeight() {
        return height;
    }

    /**
     * 设置高度
     *
     * @param height 高度
     */
    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    /**
     * 获取尺寸单位
     *
     * @return dim_unit - 尺寸单位
     */
    public String getDimUnit() {
        return dimUnit;
    }

    /**
     * 设置尺寸单位
     *
     * @param dimUnit 尺寸单位
     */
    public void setDimUnit(String dimUnit) {
        this.dimUnit = dimUnit == null ? null : dimUnit.trim();
    }

    /**
     * 获取最小安全库存
     *
     * @return min_stk_qty - 最小安全库存
     */
    public BigDecimal getMinStkQty() {
        return minStkQty;
    }

    /**
     * 设置最小安全库存
     *
     * @param minStkQty 最小安全库存
     */
    public void setMinStkQty(BigDecimal minStkQty) {
        this.minStkQty = minStkQty;
    }

    /**
     * 获取最大安全库存
     *
     * @return max_stk_qty - 最大安全库存
     */
    public BigDecimal getMaxStkQty() {
        return maxStkQty;
    }

    /**
     * 设置最大安全库存
     *
     * @param maxStkQty 最大安全库存
     */
    public void setMaxStkQty(BigDecimal maxStkQty) {
        this.maxStkQty = maxStkQty;
    }

    /**
     * 获取按容器管理
     *
     * @return cntr_flag - 按容器管理
     */
    public String getCntrFlag() {
        return cntrFlag;
    }

    /**
     * 设置按容器管理
     *
     * @param cntrFlag 按容器管理
     */
    public void setCntrFlag(String cntrFlag) {
        this.cntrFlag = cntrFlag == null ? null : cntrFlag.trim();
    }

    /**
     * 获取寿命期，可以为空，单位天
     *
     * @return validate_lifetime - 寿命期，可以为空，单位天
     */
    public BigDecimal getValidateLifetime() {
        return validateLifetime;
    }

    /**
     * 设置寿命期，可以为空，单位天
     *
     * @param validateLifetime 寿命期，可以为空，单位天
     */
    public void setValidateLifetime(BigDecimal validateLifetime) {
        this.validateLifetime = validateLifetime;
    }

    /**
     * 获取过期预警时间，可以为空，单位天
     *
     * @return expire_warn_time - 过期预警时间，可以为空，单位天
     */
    public BigDecimal getExpireWarnTime() {
        return expireWarnTime;
    }

    /**
     * 设置过期预警时间，可以为空，单位天
     *
     * @param expireWarnTime 过期预警时间，可以为空，单位天
     */
    public void setExpireWarnTime(BigDecimal expireWarnTime) {
        this.expireWarnTime = expireWarnTime;
    }

    /**
     * 获取是否允许过期物料出库，0否，1是
     *
     * @return expire_mat_out - 是否允许过期物料出库，0否，1是
     */
    public String getExpireMatOut() {
        return expireMatOut;
    }

    /**
     * 设置是否允许过期物料出库，0否，1是
     *
     * @param expireMatOut 是否允许过期物料出库，0否，1是
     */
    public void setExpireMatOut(String expireMatOut) {
        this.expireMatOut = expireMatOut == null ? null : expireMatOut.trim();
    }

    /**
     * 获取物料类型code
     *
     * @return mat_type_code - 物料类型code
     */
    public String getMatTypeCode() {
        return matTypeCode;
    }

    /**
     * 设置物料类型code
     *
     * @param matTypeCode 物料类型code
     */
    public void setMatTypeCode(String matTypeCode) {
        this.matTypeCode = matTypeCode == null ? null : matTypeCode.trim();
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

    public String getCarnm() {
        return carnm;
    }

    public void setCarnm(String carnm) {
        this.carnm = carnm;
    }

    public String getSpecFlag() {
        return specFlag;
    }

    public void setSpecFlag(String specFlag) {
        this.specFlag = specFlag;
    }

    public BaseMatPackageSpecDTO getBaseMatPackageSpecDTO() {
        return baseMatPackageSpecDTO;
    }

    public void setBaseMatPackageSpecDTO(BaseMatPackageSpecDTO baseMatPackageSpecDTO) {
        this.baseMatPackageSpecDTO = baseMatPackageSpecDTO;
    }
}