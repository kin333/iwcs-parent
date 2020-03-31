package com.wisdom.iwcs.domain.base.dto;

/**
 * 页面查询货架位置信息 专用
 * @Author george
 * @Date 2019/12/19 10:23
 */
public class MapBerthAndPodDetailInfo {

    private String podCode;

    private String podBerCode;

    private String pointAlias;

    private String mapBerCode;

    private String mapPodCode;

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getPodBerCode() {
        return podBerCode;
    }

    public void setPodBerCode(String podBerCode) {
        this.podBerCode = podBerCode;
    }

    public String getPointAlias() {
        return pointAlias;
    }

    public void setPointAlias(String pointAlias) {
        this.pointAlias = pointAlias;
    }

    public String getMapBerCode() {
        return mapBerCode;
    }

    public void setMapBerCode(String mapBerCode) {
        this.mapBerCode = mapBerCode;
    }

    public String getMapPodCode() {
        return mapPodCode;
    }

    public void setMapPodCode(String mapPodCode) {
        this.mapPodCode = mapPodCode;
    }
}
