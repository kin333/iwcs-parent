package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_map_berth")
public class BaseMapBerthDTO {
    @Id
    private Integer id;

    /**
     * 地图编号
     */
    @Column(name = "map_code")
    private String mapCode;

    /**
     * 点位地码
     */
    @Column(name = "ber_code")
    private String berCode;

    /**
     * x坐标
     */
    private String coox;

    /**
     * y坐标
     */
    private String cooy;

    /**
     * 锁定状态：0:未锁定，1：已锁定
     */
    private Integer inLock;
    /**
     * 货架号
     */
    private String podCode;
    /**
     * 锁定源
     */
    private String lockSource;

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
     * 地码类型,对应字典表
     */
    @Column(name = "berth_type_value")
    private String berthTypeValue;

    /**
     * 库区编码
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 点位业务类型
     */
    @Column(name = "biz_type")
    private String bizType;

    /**
     * 关联产线点编号
     */
    @Column(name = "relation_piont_code")
    private String relationPiontCode;

    /**
     * 点位编号
     */
    @Column(name = "point_alias")
    private String pointAlias;

    public String getBerthTypeValue() {
        return berthTypeValue;
    }

    public void setBerthTypeValue(String berthTypeValue) {
        this.berthTypeValue = berthTypeValue;
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
     * 获取地图编号
     *
     * @return map_code - 地图编号
     */
    public String getMapCode() {
        return mapCode;
    }

    /**
     * 设置地图编号
     *
     * @param mapCode 地图编号
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
     * 获取y坐标
     *
     * @return cooy - y坐标
     */
    public String getCooy() {
        return cooy;
    }

    /**
     * 设置y坐标
     *
     * @param cooy y坐标
     */
    public void setCooy(String cooy) {
        this.cooy = cooy == null ? null : cooy.trim();
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

    public Integer getInLock() {
        return inLock;
    }

    public void setInLock(Integer inLock) {
        this.inLock = inLock;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getLockSource() {
        return lockSource;
    }

    public void setLockSource(String lockSource) {
        this.lockSource = lockSource;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getRelationPiontCode() {
        return relationPiontCode;
    }

    public void setRelationPiontCode(String relationPiontCode) {
        this.relationPiontCode = relationPiontCode;
    }

    public String getPointAlias() {
        return pointAlias;
    }

    public void setPointAlias(String pointAlias) {
        this.pointAlias = pointAlias;
    }
}