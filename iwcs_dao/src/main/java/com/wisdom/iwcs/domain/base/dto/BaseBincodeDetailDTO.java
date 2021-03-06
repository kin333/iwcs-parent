package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_bincode_detail")
public class BaseBincodeDetailDTO {
    @Id
    private Integer id;

    /**
     * 仓位编号
     */
    private String bincode;

    /**
     * 货架编号
     */
    @Column(name = "pod_code")
    private String podCode;

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
     * 载货状态：0 空仓，1 有货未满 2满仓
     */
    @Column(name = "cargo_capacity_status")
    private String cargoCapacityStatus;

    /**
     * 锁状态，Y入库锁，Z出库锁，X其他锁，N无锁
     */
    @Column(name = "lock_stat")
    private Integer lockStat;

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
     * 层数，冗余，方便查询
     */
    private Integer layer;

    /**
     * 锁定源
     */
    @Column(name = "lock_source")
    private String lockSource;
    /**
     * 载具类型
     */
    @Column(name = "vehicle_type")
    private String vehicleType;
    /**
     * 载具编号
     */
    @Column(name = "vehicle_code")
    private String vehicleCode;

    public String getLockSource() {
        return lockSource;
    }

    public void setLockSource(String lockSource) {
        this.lockSource = lockSource;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }


    public Integer getLayer() {
        return layer;
    }

    public void setLayer(Integer layer) {
        this.layer = layer;
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
     * 获取货架编号
     *
     * @return pod_code - 货架编号
     */
    public String getPodCode() {
        return podCode;
    }

    /**
     * 设置货架编号
     *
     * @param podCode 货架编号
     */
    public void setPodCode(String podCode) {
        this.podCode = podCode == null ? null : podCode.trim();
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
     * 获取载货状态：0 空仓，1 有货未满 2满仓
     *
     * @return cargo_capacity_status - 载货状态：0 空仓，1 有货未满 2满仓
     */
    public String getCargoCapacityStatus() {
        return cargoCapacityStatus;
    }

    /**
     * 设置载货状态：0 空仓，1 有货未满 2满仓
     *
     * @param cargoCapacityStatus 载货状态：0 空仓，1 有货未满 2满仓
     */
    public void setCargoCapacityStatus(String cargoCapacityStatus) {
        this.cargoCapacityStatus = cargoCapacityStatus == null ? null : cargoCapacityStatus.trim();
    }

    /**
     * 获取锁状态，Y入库锁，Z出库锁，X其他锁，N无锁
     *
     * @return lock_stat - 锁状态，Y入库锁，Z出库锁，X其他锁，N无锁
     */
    public Integer getLockStat() {
        return lockStat;
    }

    /**
     * 设置锁状态，Y入库锁，Z出库锁，X其他锁，N无锁
     *
     * @param lockStat 锁状态，Y入库锁，Z出库锁，X其他锁，N无锁
     */
    public void setLockStat(Integer lockStat) {
        this.lockStat = lockStat;
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