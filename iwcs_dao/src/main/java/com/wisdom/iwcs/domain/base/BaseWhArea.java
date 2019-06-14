package com.wisdom.iwcs.domain.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_wh_area")
public class BaseWhArea {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 仓库code
     */
    @Column(name = "wh_code")
    private String whCode;

    /**
     * 库区代码
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 库区名称
     */
    @Column(name = "area_name")
    private String areaName;

    /**
     * 库区状态，0可用，1锁定
     */
    @Column(name = "area_status")
    private Integer areaStatus;

    /**
     * 备注
     */
    private String remark;

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
     * 获取仓库code
     *
     * @return wh_code - 仓库code
     */
    public String getWhCode() {
        return whCode;
    }

    /**
     * 设置仓库code
     *
     * @param whCode 仓库code
     */
    public void setWhCode(String whCode) {
        this.whCode = whCode == null ? null : whCode.trim();
    }

    /**
     * 获取库区代码
     *
     * @return area_code - 库区代码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置库区代码
     *
     * @param areaCode 库区代码
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 获取库区名称
     *
     * @return area_name - 库区名称
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 设置库区名称
     *
     * @param areaName 库区名称
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * 获取库区状态，0可用，1锁定
     *
     * @return area_status - 库区状态，0可用，1锁定
     */
    public Integer getAreaStatus() {
        return areaStatus;
    }

    /**
     * 设置库区状态，0可用，1锁定
     *
     * @param areaStatus 库区状态，0可用，1锁定
     */
    public void setAreaStatus(Integer areaStatus) {
        this.areaStatus = areaStatus;
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