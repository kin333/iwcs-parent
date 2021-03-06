package com.wisdom.iwcs.domain.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_wb")
public class BaseWb {
    @Id
    private Integer id;

    /**
     * 工作台编码
     */
    @Column(name = "wb_code")
    private String wbCode;

    /**
     * 库区编码
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 地图编码
     */
    @Column(name = "map_code")
    private String mapCode;

    /**
     * 点位地码
     */
    @Column(name = "ber_code")
    private String berCode;

    /**
     * 工作台名称
     */
    @Column(name = "wb_name")
    private String wbName;

    /**
     * 工作台类型，暂时无用
     */
    @Column(name = "wb_type")
    private String wbType;

    /**
     * x坐标
     */
    private String coox;

    /**
     * Y坐标
     */
    private String cooy;

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
     * 获取工作台编码
     *
     * @return wb_code - 工作台编码
     */
    public String getWbCode() {
        return wbCode;
    }

    /**
     * 设置工作台编码
     *
     * @param wbCode 工作台编码
     */
    public void setWbCode(String wbCode) {
        this.wbCode = wbCode == null ? null : wbCode.trim();
    }

    /**
     * 获取库区编码
     *
     * @return area_code - 库区编码
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置库区编码
     *
     * @param areaCode 库区编码
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 获取地图编码
     *
     * @return map_code - 地图编码
     */
    public String getMapCode() {
        return mapCode;
    }

    /**
     * 设置地图编码
     *
     * @param mapCode 地图编码
     */
    public void setMapCode(String mapCode) {
        this.mapCode = mapCode == null ? null : mapCode.trim();
    }

    /**
     * 获取点位地码
     *
     * @return ber_code - 点位地码
     */
    public String getBerCode() {
        return berCode;
    }

    /**
     * 设置点位地码
     *
     * @param berCode 点位地码
     */
    public void setBerCode(String berCode) {
        this.berCode = berCode == null ? null : berCode.trim();
    }

    /**
     * 获取工作台名称
     *
     * @return wb_name - 工作台名称
     */
    public String getWbName() {
        return wbName;
    }

    /**
     * 设置工作台名称
     *
     * @param wbName 工作台名称
     */
    public void setWbName(String wbName) {
        this.wbName = wbName == null ? null : wbName.trim();
    }

    /**
     * 获取工作台类型，暂时无用
     *
     * @return wb_type - 工作台类型，暂时无用
     */
    public String getWbType() {
        return wbType;
    }

    /**
     * 设置工作台类型，暂时无用
     *
     * @param wbType 工作台类型，暂时无用
     */
    public void setWbType(String wbType) {
        this.wbType = wbType == null ? null : wbType.trim();
    }

    /**
     * 获取x坐标
     *
     * @return coox - x坐标
     */
    public String getCoox() {
        return coox;
    }

    /**
     * 设置x坐标
     *
     * @param coox x坐标
     */
    public void setCoox(String coox) {
        this.coox = coox == null ? null : coox.trim();
    }

    /**
     * 获取Y坐标
     *
     * @return cooy - Y坐标
     */
    public String getCooy() {
        return cooy;
    }

    /**
     * 设置Y坐标
     *
     * @param cooy Y坐标
     */
    public void setCooy(String cooy) {
        this.cooy = cooy == null ? null : cooy.trim();
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