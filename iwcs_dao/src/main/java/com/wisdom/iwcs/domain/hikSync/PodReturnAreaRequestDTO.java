package com.wisdom.iwcs.domain.hikSync;

/**
 * @author cecilia.yang
 * 指定货架回库策略
 */
public class PodReturnAreaRequestDTO {

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
     * 货架编号
     */
    private String podCode;

    @Override
    public String toString() {
        return "PodReturnAreaRequestDTO{" +
                "reqCode='" + reqCode + '\'' +
                ", reqTime='" + reqTime + '\'' +
                ", clientCode='" + clientCode + '\'' +
                ", tokenCode='" + tokenCode + '\'' +
                ", podCode='" + podCode + '\'' +
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

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }
}
