package com.wisdom.iwcs.domain.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_map_section")
public class BaseMapSection {
    @Id
    private Integer id;

    /**
     * 库区code
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 地图code
     */
    @Column(name = "map_code")
    private String mapCode;

    /**
     * 存储区代码
     */
    @Column(name = "sec_code")
    private String secCode;

    /**
     * 存储区名称
     */
    @Column(name = "sec_name")
    private String secName;

    /**
     * 存储区类型
     */
    @Column(name = "stg_type_code")
    private String stgTypeCode;

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
     * 获取库区code
     *
     * @return area_code - 库区code
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置库区code
     *
     * @param areaCode 库区code
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 获取地图code
     *
     * @return map_code - 地图code
     */
    public String getMapCode() {
        return mapCode;
    }

    /**
     * 设置地图code
     *
     * @param mapCode 地图code
     */
    public void setMapCode(String mapCode) {
        this.mapCode = mapCode == null ? null : mapCode.trim();
    }

    /**
     * 获取存储区代码
     *
     * @return sec_code - 存储区代码
     */
    public String getSecCode() {
        return secCode;
    }

    /**
     * 设置存储区代码
     *
     * @param secCode 存储区代码
     */
    public void setSecCode(String secCode) {
        this.secCode = secCode == null ? null : secCode.trim();
    }

    /**
     * 获取存储区名称
     *
     * @return sec_name - 存储区名称
     */
    public String getSecName() {
        return secName;
    }

    /**
     * 设置存储区名称
     *
     * @param secName 存储区名称
     */
    public void setSecName(String secName) {
        this.secName = secName == null ? null : secName.trim();
    }

    /**
     * 获取存储区类型
     *
     * @return stg_type_code - 存储区类型
     */
    public String getStgTypeCode() {
        return stgTypeCode;
    }

    /**
     * 设置存储区类型
     *
     * @param stgTypeCode 存储区类型
     */
    public void setStgTypeCode(String stgTypeCode) {
        this.stgTypeCode = stgTypeCode == null ? null : stgTypeCode.trim();
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