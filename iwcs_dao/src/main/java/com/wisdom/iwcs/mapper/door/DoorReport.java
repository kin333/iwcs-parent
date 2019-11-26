package com.wisdom.iwcs.mapper.door;

/**
 * 门 上报解析类
 * @Author george
 * @Date 2019/11/26 16:31
 */
public class DoorReport {
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
     * 门的状态 正常/异常
     */
    private String doorStatus;

    /**
     * 门的作业方式 开门到位/关门到位/开门中/关门中
     */
    private String doorWorkType;

    /**
     * 门的模式  自动/手动
     */
    private String doorModel;

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

    public String getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(String doorStatus) {
        this.doorStatus = doorStatus;
    }

    public String getDoorWorkType() {
        return doorWorkType;
    }

    public void setDoorWorkType(String doorWorkType) {
        this.doorWorkType = doorWorkType;
    }

    public String getDoorModel() {
        return doorModel;
    }

    public void setDoorModel(String doorModel) {
        this.doorModel = doorModel;
    }
}
