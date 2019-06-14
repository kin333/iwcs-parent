package com.wisdom.iwcs.domain.hikSync;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/28 10:03
 */
public class RotatePodByTpsDTO {

    private String reqCode;

    private String reqTime;

    private String clientCode;

    private String tokenCode;

    private String wbCode;

    private String binCode;

    @Override
    public String toString() {
        return "RotatePodByTpsDTO{" +
                "reqCode='" + reqCode + '\'' +
                ", reqTime='" + reqTime + '\'' +
                ", clientCode='" + clientCode + '\'' +
                ", tokenCode='" + tokenCode + '\'' +
                ", wbCode='" + wbCode + '\'' +
                ", binCode='" + binCode + '\'' +
                '}';
    }

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

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public String getBinCode() {
        return binCode;
    }

    public void setBinCode(String binCode) {
        this.binCode = binCode;
    }
}
