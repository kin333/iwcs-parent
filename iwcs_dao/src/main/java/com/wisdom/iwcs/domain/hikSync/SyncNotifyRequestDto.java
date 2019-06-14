package com.wisdom.iwcs.domain.hikSync;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/19 9:47
 */
public class SyncNotifyRequestDto {
    /**
     * 请求编号，每个请求都要一个唯一编号， 同一个请求重复提交， 使用同一编号
     */
    private String reqCode;

    /**
     * 请求时间戳，格式: “yyyy-MM-dd HH:mm:ss”
     */
    private String reqTime;

    /**
     * 客户端编号， 如PDA， HCWMS等
     */
    private String clientCode;

    /**
     * 令牌号, 由调度系统颁发。
     */
    private String tokenCode;

    /**
     * 通知类型:
     * stgSecInfo:存储区同步
     * stgTypInfo:存储类型同步
     * stgBinTypInfo:仓位类型同步
     * podTypInfo:货架类型同步
     * podInfo:货架信息同步
     * wareHouseInfo:仓库信息同步
     * elcMapInfo:地图信息同步
     * workBenchInfo:工作台同步
     */
    private String notifyType;

    /**
     * 1-全量，2-增量
     */
    private String syncType;

    /**
     * 地图简称, 需要同步的地图,由RCS-2000告知第三方系统，不填表示同步全部地图.
     */
    private String mapShortName;

    private List<SyncNotifyDataDto> data;

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getSyncType() {
        return syncType;
    }

    public void setSyncType(String syncType) {
        this.syncType = syncType;
    }

    public String getMapShortName() {
        return mapShortName;
    }

    public void setMapShortName(String mapShortName) {
        this.mapShortName = mapShortName;
    }

    public List<SyncNotifyDataDto> getData() {
        return data;
    }

    public void setData(List<SyncNotifyDataDto> data) {
        this.data = data;
    }
}
