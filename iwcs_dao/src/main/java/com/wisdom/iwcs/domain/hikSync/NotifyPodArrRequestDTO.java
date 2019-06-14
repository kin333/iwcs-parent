package com.wisdom.iwcs.domain.hikSync;

/**
 * @author cecilia.yang
 * 通知货架返程回储位
 */
public class NotifyPodArrRequestDTO {
    /**
     * 请求编号，每个请求都要一个唯一编号， 同一个请求重复提交， 使用同一编号。由第三方系统提供
     */
    private String reqCode;
    /**
     * 请求时间截 格式: “yyyy-MM-dd HH:mm:ss”。由第三方系统提供
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

    private NotifyPodArrDataDTO data;

    @Override
    public String toString() {
        return "NotifyPodArrRequestDTO{" +
                "reqCode='" + reqCode + '\'' +
                ", reqTime='" + reqTime + '\'' +
                ", clientCode='" + clientCode + '\'' +
                ", tokenCode='" + tokenCode + '\'' +
                ", data=" + data +
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

    public NotifyPodArrDataDTO getData() {
        return data;
    }

    public void setData(NotifyPodArrDataDTO data) {
        this.data = data;
    }
}
