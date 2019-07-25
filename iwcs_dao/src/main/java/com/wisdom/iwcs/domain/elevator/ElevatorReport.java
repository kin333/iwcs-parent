package com.wisdom.iwcs.domain.elevator;

/**
 * 电梯上报信息
 * @Author george
 * @Date 2019/7/25 17:11
 */
public class ElevatorReport {
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
     * 提升机状态 01正常，02异常1
     */
    private String eleStatus;

    /**
     * 楼层 01 1楼，02 2楼，03 3楼
     */
    private String floor;

    /**
     * 是否可进入电梯 01可进入，02不可进入
     */
    private String enterEle;

    /**
     * 吊箱内是否有货 01有，02无
     */
    private String inStock;

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

    public String getEleStatus() {
        return eleStatus;
    }

    public void setEleStatus(String eleStatus) {
        this.eleStatus = eleStatus;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getEnterEle() {
        return enterEle;
    }

    public void setEnterEle(String enterEle) {
        this.enterEle = enterEle;
    }

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
    }
}
