package com.wisdom.iwcs.common.utils.plcUtils;

/**
 * PLC 设备开关
 * @Author george
 * @Date 2019/7/16 15:26 
 */
public class PLCSwitch {

    /**
     * 设备类型：elevator(电梯)；line(线体)
     */
    private String deviceType;

    /**
     * 输入信号：0-报警，1：正常
     */
    private String inputSignal;

    /**
     * 设备在控制器上的输入开关序号：1,2,3
     */
    private Integer deviceInputSerial;

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getInputSignal() {
        return inputSignal;
    }

    public void setInputSignal(String inputSignal) {
        this.inputSignal = inputSignal;
    }

    public Integer getDeviceInputSerial() {
        return deviceInputSerial;
    }

    public void setDeviceInputSerial(Integer deviceInputSerial) {
        this.deviceInputSerial = deviceInputSerial;
    }
}
