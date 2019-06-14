package com.wisdom.iwcs.domain.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_wh")
public class BaseWh {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 仓库代码
     */
    @Column(name = "wh_code")
    private String whCode;

    /**
     * 仓库名称
     */
    @Column(name = "wh_name")
    private String whName;

    /**
     * 仓库类型，0传统仓，1AGV仓库
     */
    @Column(name = "wh_type")
    private Integer whType;

    /**
     * 省
     */
    @Column(name = "wh_province")
    private String whProvince;

    /**
     * 市
     */
    @Column(name = "wh_city")
    private String whCity;

    /**
     * 区县
     */
    @Column(name = "wh_district")
    private String whDistrict;

    /**
     * 详细地址
     */
    @Column(name = "wh_address")
    private String whAddress;

    /**
     * 备注
     */
    private String remark;

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
     * 获取仓库代码
     *
     * @return wh_code - 仓库代码
     */
    public String getWhCode() {
        return whCode;
    }

    /**
     * 设置仓库代码
     *
     * @param whCode 仓库代码
     */
    public void setWhCode(String whCode) {
        this.whCode = whCode == null ? null : whCode.trim();
    }

    /**
     * 获取仓库名称
     *
     * @return wh_name - 仓库名称
     */
    public String getWhName() {
        return whName;
    }

    /**
     * 设置仓库名称
     *
     * @param whName 仓库名称
     */
    public void setWhName(String whName) {
        this.whName = whName == null ? null : whName.trim();
    }

    /**
     * 获取仓库类型，0传统仓，1AGV仓库
     *
     * @return wh_type - 仓库类型，0传统仓，1AGV仓库
     */
    public Integer getWhType() {
        return whType;
    }

    /**
     * 设置仓库类型，0传统仓，1AGV仓库
     *
     * @param whType 仓库类型，0传统仓，1AGV仓库
     */
    public void setWhType(Integer whType) {
        this.whType = whType;
    }

    /**
     * 获取省
     *
     * @return wh_province - 省
     */
    public String getWhProvince() {
        return whProvince;
    }

    /**
     * 设置省
     *
     * @param whProvince 省
     */
    public void setWhProvince(String whProvince) {
        this.whProvince = whProvince == null ? null : whProvince.trim();
    }

    /**
     * 获取市
     *
     * @return wh_city - 市
     */
    public String getWhCity() {
        return whCity;
    }

    /**
     * 设置市
     *
     * @param whCity 市
     */
    public void setWhCity(String whCity) {
        this.whCity = whCity == null ? null : whCity.trim();
    }

    /**
     * 获取区县
     *
     * @return wh_district - 区县
     */
    public String getWhDistrict() {
        return whDistrict;
    }

    /**
     * 设置区县
     *
     * @param whDistrict 区县
     */
    public void setWhDistrict(String whDistrict) {
        this.whDistrict = whDistrict == null ? null : whDistrict.trim();
    }

    /**
     * 获取详细地址
     *
     * @return wh_address - 详细地址
     */
    public String getWhAddress() {
        return whAddress;
    }

    /**
     * 设置详细地址
     *
     * @param whAddress 详细地址
     */
    public void setWhAddress(String whAddress) {
        this.whAddress = whAddress == null ? null : whAddress.trim();
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