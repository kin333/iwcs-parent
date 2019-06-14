package com.wisdom.iwcs.domain.base.dto;

import java.util.Date;

/**
 * @Author: cecilia.yang
 * @Date: 2019/5/9 14:14
 */
public class ShowPodInfoResultDTO {
    /**
     * 货架编号
     */
    private String podCode;
    /**
     * 货架类型编号
     */
    private String podTypeCode;
    /**
     * 地码
     */
    private String berCode;
    /**
     * 库区代码
     */
    private String areaCode;
    /**
     * 地码最后更新时间
     */
    private Date lastBercodeUpdateTime;
    /**
     * 地图编号
     */
    private String mapCode;
    /**
     * 存储区编号
     */
    private String stgCode;
    /**
     * X坐标
     */
    private String coox;
    /**
     * Y坐标
     */
    private String cooy;
    /**
     * 货架预留属性
     */
    private String podProp1;
    /**
     * 货架预留属性
     */
    private String podProp2;
    /**
     * 货架预留属性
     */
    private String podProp3;
    /**
     * 货架预留属性
     */
    private String podProp4;
    /**
     * 货架预留属性
     */
    private String podProp5;
    /**
     * 锁状态
     */
    private Integer lockStat;
    /**
     * 总层数
     */
    private Integer totalLayer;
    /**
     * 总bincode数
     */
    private Integer binCnt;
    /**
     * 空仓位数
     */
    private Integer eBincode;
    /**
     * 有货仓位数
     */
    private Integer fBincode;

    private String lockStatName;

    @Override
    public String toString() {
        return "ShowPodInfoResultDTO{" +
                "podCode='" + podCode + '\'' +
                ", podTypeCode='" + podTypeCode + '\'' +
                ", berCode='" + berCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", lastBercodeUpdateTime=" + lastBercodeUpdateTime +
                ", mapCode='" + mapCode + '\'' +
                ", stgCode='" + stgCode + '\'' +
                ", coox='" + coox + '\'' +
                ", cooy='" + cooy + '\'' +
                ", podProp1='" + podProp1 + '\'' +
                ", podProp2='" + podProp2 + '\'' +
                ", podProp3='" + podProp3 + '\'' +
                ", podProp4='" + podProp4 + '\'' +
                ", podProp5='" + podProp5 + '\'' +
                ", lockStat=" + lockStat +
                ", totalLayer=" + totalLayer +
                ", binCnt=" + binCnt +
                ", eBincode=" + eBincode +
                ", fBincode=" + fBincode +
                ", lockStatName='" + lockStatName + '\'' +
                '}';
    }

    public String getLockStatName() {
        return lockStatName;
    }

    public void setLockStatName(String lockStatName) {
        this.lockStatName = lockStatName;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getPodTypeCode() {
        return podTypeCode;
    }

    public void setPodTypeCode(String podTypeCode) {
        this.podTypeCode = podTypeCode;
    }

    public String getBerCode() {
        return berCode;
    }

    public void setBerCode(String berCode) {
        this.berCode = berCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public Date getLastBercodeUpdateTime() {
        return lastBercodeUpdateTime;
    }

    public void setLastBercodeUpdateTime(Date lastBercodeUpdateTime) {
        this.lastBercodeUpdateTime = lastBercodeUpdateTime;
    }

    public String getMapCode() {
        return mapCode;
    }

    public void setMapCode(String mapCode) {
        this.mapCode = mapCode;
    }

    public String getStgCode() {
        return stgCode;
    }

    public void setStgCode(String stgCode) {
        this.stgCode = stgCode;
    }

    public String getCoox() {
        return coox;
    }

    public void setCoox(String coox) {
        this.coox = coox;
    }

    public String getCooy() {
        return cooy;
    }

    public void setCooy(String cooy) {
        this.cooy = cooy;
    }

    public String getPodProp1() {
        return podProp1;
    }

    public void setPodProp1(String podProp1) {
        this.podProp1 = podProp1;
    }

    public String getPodProp2() {
        return podProp2;
    }

    public void setPodProp2(String podProp2) {
        this.podProp2 = podProp2;
    }

    public String getPodProp3() {
        return podProp3;
    }

    public void setPodProp3(String podProp3) {
        this.podProp3 = podProp3;
    }

    public String getPodProp4() {
        return podProp4;
    }

    public void setPodProp4(String podProp4) {
        this.podProp4 = podProp4;
    }

    public String getPodProp5() {
        return podProp5;
    }

    public void setPodProp5(String podProp5) {
        this.podProp5 = podProp5;
    }

    public Integer getLockStat() {
        return lockStat;
    }

    public void setLockStat(Integer lockStat) {
        this.lockStat = lockStat;
    }

    public Integer getTotalLayer() {
        return totalLayer;
    }

    public void setTotalLayer(Integer totalLayer) {
        this.totalLayer = totalLayer;
    }

    public Integer getBinCnt() {
        return binCnt;
    }

    public void setBinCnt(Integer binCnt) {
        this.binCnt = binCnt;
    }

    public Integer geteBincode() {
        return eBincode;
    }

    public void seteBincode(Integer eBincode) {
        this.eBincode = eBincode;
    }

    public Integer getfBincode() {
        return fBincode;
    }

    public void setfBincode(Integer fBincode) {
        this.fBincode = fBincode;
    }
}
