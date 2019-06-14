package com.wisdom.iwcs.domain.hikSync;

import java.util.List;

/**
 * @Author cecilia.yang
 * AGV通知WMS接口
 */
public class NotifyClientRequestDTO {
    /**
     * 接口方法名：notifyClient
     */
    private String method;
    /**
     * 请求编号，每个请求都要一个唯一编号， 同一个请求重复提交， 使用同一编号。
     */
    private String reqCode;
    /**
     * 请求时间截 格式: “yyyy-MM-dd HH:mm:ss”。
     */
    private String reqTime;
    /**
     * 客户端编号，如PDA，HCWMS等。由TPS告知第三方系统
     */
    private String clientCode;
    /**
     * 令牌号, 由调度系统颁发。由TPS告知第三方系统
     */
    private String tokenCode;
    /**
     * 通知类型:
     * 1:到达工作台
     * 2:到达排队区
     */
    private String notifyTyp;
    /**
     * 工作台编号
     */
    private String wbCode;
    /**
     * 货架编号
     */
    private String podCode;
    /**
     * 货架类型
     */
    private String podTyp;

    private List<NotifyClientDataDTO> data;

    @Override
    public String toString() {
        return "NotifyClientRequestDTO{" +
                "method='" + method + '\'' +
                ", reqCode='" + reqCode + '\'' +
                ", reqTime='" + reqTime + '\'' +
                ", clientCode='" + clientCode + '\'' +
                ", tokenCode='" + tokenCode + '\'' +
                ", notifyTyp='" + notifyTyp + '\'' +
                ", wbCode='" + wbCode + '\'' +
                ", podCode='" + podCode + '\'' +
                ", podTyp='" + podTyp + '\'' +
                ", data=" + data +
                '}';
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public String getNotifyTyp() {
        return notifyTyp;
    }

    public void setNotifyTyp(String notifyTyp) {
        this.notifyTyp = notifyTyp;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getPodTyp() {
        return podTyp;
    }

    public void setPodTyp(String podTyp) {
        this.podTyp = podTyp;
    }

    public List<NotifyClientDataDTO> getData() {
        return data;
    }

    public void setData(List<NotifyClientDataDTO> data) {
        this.data = data;
    }
}
