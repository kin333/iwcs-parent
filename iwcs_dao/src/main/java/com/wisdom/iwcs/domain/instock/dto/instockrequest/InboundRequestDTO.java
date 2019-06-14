package com.wisdom.iwcs.domain.instock.dto.instockrequest;

import java.util.List;

/**
 * 入库确认
 */
public class InboundRequestDTO {
    /**
     * 请求编号
     */
    private String reqcode;
    /**
     * 点位
     */
    private String wbCode;

    /**
     * 操作员，选填项
     */
    private String userId;
    /**
     * 库区代码
     */
    private String areaCode;
    /**
     * 确认后小车的动作，如离开、原地待命、旋转等
     */
    private String agvNextAction;

    /**
     * 入库确认后是否循环补充，0：否，1：循环
     */
    private String looplb;

    /**
     * 组装数据
     */
    private List<DataInboundDTO> data;

    @Override
    public String toString() {
        return "InboundRequestDTO{" +
                "reqcode='" + reqcode + '\'' +
                ", wbCode='" + wbCode + '\'' +
                ", userId='" + userId + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", agvNextAction='" + agvNextAction + '\'' +
                ", looplb='" + looplb + '\'' +
                ", data=" + data +
                '}';
    }

    public String getReqcode() {
        return reqcode;
    }

    public void setReqcode(String reqcode) {
        this.reqcode = reqcode;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAgvNextAction() {
        return agvNextAction;
    }

    public void setAgvNextAction(String agvNextAction) {
        this.agvNextAction = agvNextAction;
    }

    public String getLooplb() {
        return looplb;
    }

    public void setLooplb(String looplb) {
        this.looplb = looplb;
    }

    public List<DataInboundDTO> getData() {
        return data;
    }

    public void setData(List<DataInboundDTO> data) {
        this.data = data;
    }
}
