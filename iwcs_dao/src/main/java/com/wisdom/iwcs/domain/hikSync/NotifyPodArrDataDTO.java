package com.wisdom.iwcs.domain.hikSync;

/**
 * @author cecilia.yang
 * 通知货架返程回储位DATA层
 */
public class NotifyPodArrDataDTO {
    /**
     * 储位编号
     */
    private String mapDataCode;
    /**
     * X坐标
     */
    private String cooX;
    /**
     * Y坐标
     */
    private String cooY;
    /**
     * 0-启用，1-禁用
     */
    private String status;
    /**
     * 地图编号
     */
    private String mapCode;
    /**
     * 货架编号
     */
    private String podCode;
    /**
     * 区域编号
     */
    private String areaCode;
    /**
     * 存储区编号
     */
    private String stgSecCode;

    @Override
    public String toString() {
        return "NotifyPodArrDataDTO{" +
                "mapDataCode='" + mapDataCode + '\'' +
                ", cooX='" + cooX + '\'' +
                ", cooY='" + cooY + '\'' +
                ", status='" + status + '\'' +
                ", mapCode='" + mapCode + '\'' +
                ", podCode='" + podCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", stgSecCode='" + stgSecCode + '\'' +
                '}';
    }

    public String getMapDataCode() {
        return mapDataCode;
    }

    public void setMapDataCode(String mapDataCode) {
        this.mapDataCode = mapDataCode;
    }

    public String getCooX() {
        return cooX;
    }

    public void setCooX(String cooX) {
        this.cooX = cooX;
    }

    public String getCooY() {
        return cooY;
    }

    public void setCooY(String cooY) {
        this.cooY = cooY;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getStgSecCode() {
        return stgSecCode;
    }

    public void setStgSecCode(String stgSecCode) {
        this.stgSecCode = stgSecCode;
    }
}
