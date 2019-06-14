package com.wisdom.iwcs.domain.outstock.dto;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/27 15:59
 */
public class OutStockCallRequestDTO {
    /**
     * 点位，必填
     */
    private String wbCode;
    /**
     * 库区，必填
     */
    private String areaCode;
    /**
     * 呼叫用户
     */
    private String srcUserCode;
    /**
     * 呼叫唯一编号
     */
    private String srcReqCode;
    /**
     * 呼叫客户端类型，0手持，1PC，2上游系统
     */
    private String srcClientType;
    /**
     * 呼叫类型，1：按出库单呼叫，2：无订单呼叫，3：整单呼叫
     */
    private String callType;

    private List<OutStockCallDataDTO> data;

    @Override
    public String toString() {
        return "OutStockCallRequestDTO{" +
                "wbCode='" + wbCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", srcUserCode='" + srcUserCode + '\'' +
                ", srcReqCode='" + srcReqCode + '\'' +
                ", srcClientType='" + srcClientType + '\'' +
                ", callType='" + callType + '\'' +
                ", data=" + data +
                '}';
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getSrcUserCode() {
        return srcUserCode;
    }

    public void setSrcUserCode(String srcUserCode) {
        this.srcUserCode = srcUserCode;
    }

    public String getSrcReqCode() {
        return srcReqCode;
    }

    public void setSrcReqCode(String srcReqCode) {
        this.srcReqCode = srcReqCode;
    }

    public String getSrcClientType() {
        return srcClientType;
    }

    public void setSrcClientType(String srcClientType) {
        this.srcClientType = srcClientType;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public List<OutStockCallDataDTO> getData() {
        return data;
    }

    public void setData(List<OutStockCallDataDTO> data) {
        this.data = data;
    }
}
