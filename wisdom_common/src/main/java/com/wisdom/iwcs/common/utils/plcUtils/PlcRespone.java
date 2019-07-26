package com.wisdom.iwcs.common.utils.plcUtils;

/**
 * 对接plc 返回值
 * @Author george
 * @Date 2019/7/16 15:21
 */
public class PlcRespone {

    private String address;
    /**
     *     03:电梯 04:线体
     */
    private String commandType;

    private int returnBodyByteLength;

    private String returnBodyBytes;

    private String crcValid;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public int getReturnBodyByteLength() {
        return returnBodyByteLength;
    }

    public void setReturnBodyByteLength(int returnBodyByteLength) {
        this.returnBodyByteLength = returnBodyByteLength;
    }

    public String getReturnBodyBytes() {
        return returnBodyBytes;
    }

    public void setReturnBodyBytes(String returnBodyBytes) {
        this.returnBodyBytes = returnBodyBytes;
    }

    public String getCrcValid() {
        return crcValid;
    }

    public void setCrcValid(String crcValid) {
        this.crcValid = crcValid;
    }
}
