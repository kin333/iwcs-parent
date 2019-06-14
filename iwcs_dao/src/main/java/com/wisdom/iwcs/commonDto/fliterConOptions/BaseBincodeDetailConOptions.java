package com.wisdom.iwcs.commonDto.fliterConOptions;

/**
 * 仓位动态详情条件
 *
 * @author ted
 * @create 2019-03-01 上午10:23
 **/
public class BaseBincodeDetailConOptions {
    /**
     * 仓位编号
     */
    private String bincode;

    /**
     * 货架编号
     */
    private String podCode;

    /**
     * 库区编码
     */
    private String areaCode;

    /**
     * 地图编码
     */
    private String mapCode;

    /**
     * 载货状态：0 空仓，1 有货未满 2满仓
     */
    private String cargoCapacityStatus;

    /**
     * 锁状态，Y入库锁，Z出库锁，X其他锁，N无锁
     */
    private String lockStat;

    public String getBincode() {
        return bincode;
    }

    public void setBincode(String bincode) {
        this.bincode = bincode;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getCargoCapacityStatus() {
        return cargoCapacityStatus;
    }

    public void setCargoCapacityStatus(String cargoCapacityStatus) {
        this.cargoCapacityStatus = cargoCapacityStatus;
    }

    public String getLockStat() {
        return lockStat;
    }

    public void setLockStat(String lockStat) {
        this.lockStat = lockStat;
    }
}
