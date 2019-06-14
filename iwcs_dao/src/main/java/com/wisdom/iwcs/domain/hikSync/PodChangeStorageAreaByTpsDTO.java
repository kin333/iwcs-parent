package com.wisdom.iwcs.domain.hikSync;

/**
 * 变更货架存储区接口
 */
public class PodChangeStorageAreaByTpsDTO {

    /**
     * 请求编号，每个请求都要一个唯一编号， 同一个请求重复提交， 使用同一编号
     */
    private String reqCode;

    /**
     * 请求时间截 格式: “yyyy-MM-dd HH:mm:ss”
     */
    private String reqTime;

    /**
     * 客户端编号，如PDA，HCWMS等
     */
    private String clientCode;

    /**
     * 令牌号, 由调度系统颁发
     */
    private String tokenCode;

    private String taskCode;
    private String areaTypCode;
    /**
     * 存储区编号（区域中划分的小区域
     */
    private String stgSecCode;
    private String binCode;


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

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getAreaTypCode() {
        return areaTypCode;
    }

    public void setAreaTypCode(String areaTypCode) {
        this.areaTypCode = areaTypCode;
    }

    public String getStgSecCode() {
        return stgSecCode;
    }

    public void setStgSecCode(String stgSecCode) {
        this.stgSecCode = stgSecCode;
    }

    public String getBinCode() {
        return binCode;
    }

    public void setBinCode(String binCode) {
        this.binCode = binCode;
    }
}
