package com.wisdom.iwcs.domain.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "base_mat_package_spec")
public class BaseMatPackageSpec {
    @Id
    private Integer id;

    /**
     * 规格编号
     */
    @Column(name = "spec_code")
    private String specCode;

    /**
     * 规格名称
     */
    @Column(name = "spec_name")
    private String specName;

    /**
     * 规格长度
     */
    @Column(name = "spec_length")
    private BigDecimal specLength;

    /**
     * 规格宽度
     */
    @Column(name = "spec_width")
    private BigDecimal specWidth;

    /**
     * 规格高度
     */
    @Column(name = "spec_height")
    private BigDecimal specHeight;

    /**
     * 规格尺寸单位
     */
    @Column(name = "spec__size_unit")
    private String specSizeUnit;

    /**
     * 规格重量
     */
    @Column(name = "spec_wt")
    private BigDecimal specWt;

    /**
     * 规格重量单位
     */
    @Column(name = "spec_wt_unit")
    private String specWtUnit;

    /**
     * 一规格数量
     */
    @Column(name = "spec_qty")
    private BigDecimal specQty;

    /**
     * 物料编号
     */
    @Column(name = "mat_code")
    private String matCode;

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
     * 获取规格编号
     *
     * @return spec_code - 规格编号
     */
    public String getSpecCode() {
        return specCode;
    }

    /**
     * 设置规格编号
     *
     * @param specCode 规格编号
     */
    public void setSpecCode(String specCode) {
        this.specCode = specCode == null ? null : specCode.trim();
    }

    /**
     * 获取规格名称
     *
     * @return spec_name - 规格名称
     */
    public String getSpecName() {
        return specName;
    }

    /**
     * 设置规格名称
     *
     * @param specName 规格名称
     */
    public void setSpecName(String specName) {
        this.specName = specName == null ? null : specName.trim();
    }

    /**
     * 获取规格长度
     *
     * @return spec_length - 规格长度
     */
    public BigDecimal getSpecLength() {
        return specLength;
    }

    /**
     * 设置规格长度
     *
     * @param specLength 规格长度
     */
    public void setSpecLength(BigDecimal specLength) {
        this.specLength = specLength;
    }

    /**
     * 获取规格宽度
     *
     * @return spec_width - 规格宽度
     */
    public BigDecimal getSpecWidth() {
        return specWidth;
    }

    /**
     * 设置规格宽度
     *
     * @param specWidth 规格宽度
     */
    public void setSpecWidth(BigDecimal specWidth) {
        this.specWidth = specWidth;
    }

    /**
     * 获取规格高度
     *
     * @return spec_height - 规格高度
     */
    public BigDecimal getSpecHeight() {
        return specHeight;
    }

    /**
     * 设置规格高度
     *
     * @param specHeight 规格高度
     */
    public void setSpecHeight(BigDecimal specHeight) {
        this.specHeight = specHeight;
    }

    /**
     * 获取规格尺寸单位
     *
     * @return spec__size_unit - 规格尺寸单位
     */
    public String getSpecSizeUnit() {
        return specSizeUnit;
    }

    /**
     * 设置规格尺寸单位
     *
     * @param specSizeUnit 规格尺寸单位
     */
    public void setSpecSizeUnit(String specSizeUnit) {
        this.specSizeUnit = specSizeUnit == null ? null : specSizeUnit.trim();
    }

    /**
     * 获取规格重量
     *
     * @return spec_wt - 规格重量
     */
    public BigDecimal getSpecWt() {
        return specWt;
    }

    /**
     * 设置规格重量
     *
     * @param specWt 规格重量
     */
    public void setSpecWt(BigDecimal specWt) {
        this.specWt = specWt;
    }

    /**
     * 获取规格重量单位
     *
     * @return spec_wt_unit - 规格重量单位
     */
    public String getSpecWtUnit() {
        return specWtUnit;
    }

    /**
     * 设置规格重量单位
     *
     * @param specWtUnit 规格重量单位
     */
    public void setSpecWtUnit(String specWtUnit) {
        this.specWtUnit = specWtUnit == null ? null : specWtUnit.trim();
    }

    /**
     * 获取一规格数量
     *
     * @return spec_qty - 一规格数量
     */
    public BigDecimal getSpecQty() {
        return specQty;
    }

    /**
     * 设置一规格数量
     *
     * @param specQty 一规格数量
     */
    public void setSpecQty(BigDecimal specQty) {
        this.specQty = specQty;
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
}