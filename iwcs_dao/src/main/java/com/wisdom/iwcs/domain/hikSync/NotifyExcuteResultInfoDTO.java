package com.wisdom.iwcs.domain.hikSync;

/**
 * 自动门结果反馈接口参数
 * @Author george
 * @Date 2019/11/27 13:31 
 */
public class NotifyExcuteResultInfoDTO {

    /**
     * 设备编号
     */
    private String deviceIndex;

    /**
     * 设备类型
     * door自动门
     */
    private String deviceType;

    /**
     * 1 开门到位
     * 2 开始关门(上报给RCS-2000)
     */
    private String actionStatus;

    /**
     * 通知过来的任务号
     */
    private String uuid;

    public String getDeviceIndex() {
        return deviceIndex;
    }

    public void setDeviceIndex(String deviceIndex) {
        this.deviceIndex = deviceIndex;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
