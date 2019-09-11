package com.wisdom.iwcs.domain.hikSync;

public class BindPodAndBerthDTO {
    //请求编号，每个请求都要一个唯一编号，同一个请求重复提交，使用同一编号。
    private String reqCode;
    //请求时间戳，格式: “yyyy-MM-dd HH:mm:ss”
    private String reqTime;
    //客户端编号，如 PDA，HCWMS 等
    private String clientCode;
    //令牌号, 由调度系统颁发
    private String tokenCode;
    //bindPodAndBerth TCP 协议必传，REST 协议不用传，传了也不影响
    private String interfaceName;
    //货架编号
    private String podCode;
    //位置编号
    private String positionCode;
    //"1"：绑定， "0"：解绑 解绑时, 位置编号可以为空
    private String indBind;

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

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public String getIndBind() {
        return indBind;
    }

    public void setIndBind(String indBind) {
        this.indBind = indBind;
    }
}
