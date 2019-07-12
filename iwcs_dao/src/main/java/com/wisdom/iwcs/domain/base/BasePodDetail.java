package com.wisdom.iwcs.domain.base;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "base_pod_detail")
public class BasePodDetail {
    /**
     * 自增主键
     */
    @Id
    private Integer id;

    /**
     * 货架编号
     */
    @Column(name = "pod_code")
    private String podCode;

    /**
     * 当前货架地码信息
     */
    @Column(name = "ber_code")
    private String berCode;

    /**
     * 地码最后更新时间
     */
    @Column(name = "last_bercode_update_time")
    private Date lastBercodeUpdateTime;

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
     * 存储区类型代码
     */
    @Column(name = "stg_type_code")
    private String stgTypeCode;

    /**
     * 存储区代码
     */
    @Column(name = "stg_code")
    private String stgCode;

    /**
     * x坐标
     */
    private String coox;

    /**
     * y坐标
     */
    private String cooy;

    /**
     * 锁状态，Y入库锁，Z出库锁，X其他锁，N无锁
     */
    @Column(name = "lock_stat")
    private Integer lockStat;
    /**
     * 货架预留属性，如货主
     */
    @Column(name = "pod_prop1")
    private String podProp1;

    /**
     * 货架预留属性
     */
    @Column(name = "pod_prop2")
    private String podProp2;

    /**
     * 货架预留属性
     */
    @Column(name = "pod_prop3")
    private String podProp3;

    /**
     * 货架预留属性
     */
    @Column(name = "pod_prop4")
    private String podProp4;

    /**
     * 货架预留属性
     */
    @Column(name = "pod_prop5")
    private String podProp5;

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
     * 是否有锁,1是，0否
     */
    @Column(name = "in_lock")
    private Integer inLock;

    /**
     * 锁的来源
     */
    @Column(name = "lock_source")
    private String lockSource;

    /**
     * 货架是否有货，,0为否，1为是
     */
    @Column(name = "in_stock")
    private Integer inStock;

    /**
     * 版本号
     */
    @Column(name = "version")
    private Integer version;

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
    /**
     * 锁定时间
     */
    private Date podLockSourceTime;
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
     * 获取当前货架地码信息
     *
     * @return ber_code - 当前货架地码信息
     */
    public String getBerCode() {
        return berCode;
    }

    /**
     * 设置当前货架地码信息
     *
     * @param berCode 当前货架地码信息
     */
    public void setBerCode(String berCode) {
        this.berCode = berCode == null ? null : berCode.trim();
    }

    /**
     * 获取地码最后更新时间
     *
     * @return last_bercode_update_time - 地码最后更新时间
     */
    public Date getLastBercodeUpdateTime() {
        return lastBercodeUpdateTime;
    }

    /**
     * 设置地码最后更新时间
     *
     * @param lastBercodeUpdateTime 地码最后更新时间
     */
    public void setLastBercodeUpdateTime(Date lastBercodeUpdateTime) {
        this.lastBercodeUpdateTime = lastBercodeUpdateTime;
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
     * 获取存储区类型代码
     *
     * @return stg_type_code - 存储区类型代码
     */
    public String getStgTypeCode() {
        return stgTypeCode;
    }

    /**
     * 设置存储区类型代码
     *
     * @param stgTypeCode 存储区类型代码
     */
    public void setStgTypeCode(String stgTypeCode) {
        this.stgTypeCode = stgTypeCode == null ? null : stgTypeCode.trim();
    }

    /**
     * 获取存储区代码
     *
     * @return stg_code - 存储区代码
     */
    public String getStgCode() {
        return stgCode;
    }

    /**
     * 设置存储区代码
     *
     * @param stgCode 存储区代码
     */
    public void setStgCode(String stgCode) {
        this.stgCode = stgCode == null ? null : stgCode.trim();
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
     * 获取货架预留属性，如货主
     *
     * @return pod_prop1 - 货架预留属性，如货主
     */
    public String getPodProp1() {
        return podProp1;
    }

    /**
     * 设置货架预留属性，如货主
     *
     * @param podProp1 货架预留属性，如货主
     */
    public void setPodProp1(String podProp1) {
        this.podProp1 = podProp1 == null ? null : podProp1.trim();
    }

    /**
     * 获取货架预留属性
     *
     * @return pod_prop2 - 货架预留属性
     */
    public String getPodProp2() {
        return podProp2;
    }

    /**
     * 设置货架预留属性
     *
     * @param podProp2 货架预留属性
     */
    public void setPodProp2(String podProp2) {
        this.podProp2 = podProp2 == null ? null : podProp2.trim();
    }

    /**
     * 获取货架预留属性
     *
     * @return pod_prop3 - 货架预留属性
     */
    public String getPodProp3() {
        return podProp3;
    }

    /**
     * 设置货架预留属性
     *
     * @param podProp3 货架预留属性
     */
    public void setPodProp3(String podProp3) {
        this.podProp3 = podProp3 == null ? null : podProp3.trim();
    }

    /**
     * 获取货架预留属性
     *
     * @return pod_prop4 - 货架预留属性
     */
    public String getPodProp4() {
        return podProp4;
    }

    /**
     * 设置货架预留属性
     *
     * @param podProp4 货架预留属性
     */
    public void setPodProp4(String podProp4) {
        this.podProp4 = podProp4 == null ? null : podProp4.trim();
    }

    /**
     * 获取货架预留属性
     *
     * @return pod_prop5 - 货架预留属性
     */
    public String getPodProp5() {
        return podProp5;
    }

    /**
     * 设置货架预留属性
     *
     * @param podProp5 货架预留属性
     */
    public void setPodProp5(String podProp5) {
        this.podProp5 = podProp5 == null ? null : podProp5.trim();
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

    public String getLockSource() {
        return lockSource;
    }

    public void setLockSource(String lockSource) {
        this.lockSource = lockSource;
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }
    /**
     * 获取锁定时间
     * @return podLockSourceTime
     */
    public Date getPodLockSourceTime() {
        return podLockSourceTime;
    }
    /**
     * 设置锁定时间
     * @param podLockSourceTime 上锁时间，现在服务的时间
     */
    public void setPodLockSourceTime(Date podLockSourceTime) {
        this.podLockSourceTime = podLockSourceTime;
    }
}