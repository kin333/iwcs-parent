package com.wisdom.iwcs.domain.linebody;

/**
 * 线体上报信息
 * @Author george
 * @Date 2019/7/25 17:11
 */
public class LineBodyReport {

    /**
     * 请求码 01020304
     */
    private String reqCode;

    /**
     * 地址 01
     */
    private String address;

    /**
     * 设备类型 01
     */
    private String deviceType;

    /**
     * 线体状态 01正常，02异常1
     */
    private String lineStatus;

    /**
     * 坐标点 01，02
     */
    private String workPoint;

    /**
     * 作业类型 01呼叫空货架，01呼叫空货架，03呼叫小车接有货货架
     */
    private String workType;

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLineStatus() {
        return lineStatus;
    }

    public void setLineStatus(String lineStatus) {
        this.lineStatus = lineStatus;
    }

    public String getWorkPoint() {
        return workPoint;
    }

    public void setWorkPoint(String workPoint) {
        this.workPoint = workPoint;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }
}
