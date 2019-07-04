package com.wisdom.iwcs.domain.base.dto;

import javax.persistence.Column;

public class LockStorageDto {
    /**
     * 地图编号
     */
    @Column(name = "map_code")
    private String mapCode;
    /**
     * 货架号
     */
    private String podCode;
    /**
     * 锁定源
     */
    private String lockSource;
    /**
     * 点位地码
     */
    @Column(name = "ber_code")
    private String berCode;

    @Column(name = "version")
    private Integer version;

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
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

    public String getBerCode() {
        return berCode;
    }

    public void setBerCode(String berCode) {
        this.berCode = berCode;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
