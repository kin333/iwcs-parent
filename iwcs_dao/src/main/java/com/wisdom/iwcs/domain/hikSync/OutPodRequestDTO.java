package com.wisdom.iwcs.domain.hikSync;

import java.util.List;

/**
 * 货架出库
 */
public class OutPodRequestDTO {
    /**
     * 请求编号，每个请求都要一个唯一编号， 同一个请求重复提交， 使用同一编号
     */
    private String reqCode;

    /**
     * 请求时间戳 格式: “yyyy-MM-dd HH:mm:ss”
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

    /**
     * getOutPod
     */
    private String interfaceName;

    /**
     * 拼接Data
     */
    private List<OutPodDataDTO> data;


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

    public List<OutPodDataDTO> getData() {
        return data;
    }

    public void setData(List<OutPodDataDTO> data) {
        this.data = data;
    }
}
