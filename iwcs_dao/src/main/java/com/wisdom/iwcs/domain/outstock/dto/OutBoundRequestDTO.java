package com.wisdom.iwcs.domain.outstock.dto;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/2/25 18:19
 */
public class OutBoundRequestDTO {

    private String userId;

    /**
     * 点位选填
     */
    private String wbCode;
    /**
     * 库区
     */
    private String areaCode;

    /**
     * 呼叫类型，如订单出库，虚拟订单出库
     */
    private String outCallType;
    /**
     * 确认后小车的动作，如离开、原地待命、旋转等
     */
    private String agvNextAction;

    private List<OutBoundDataDTO> data;

    @Override
    public String toString() {
        return "OutBoundRequestDTO{" +
                "userId='" + userId + '\'' +
                ", wbCode='" + wbCode + '\'' +
                ", areaCode='" + areaCode + '\'' +
                ", outCallType='" + outCallType + '\'' +
                ", agvNextAction='" + agvNextAction + '\'' +
                ", data=" + data +
                '}';
    }

    public String getAgvNextAction() {
        return agvNextAction;
    }

    public void setAgvNextAction(String agvNextAction) {
        this.agvNextAction = agvNextAction;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getOutCallType() {
        return outCallType;
    }

    public void setOutCallType(String outCallType) {
        this.outCallType = outCallType;
    }

    public List<OutBoundDataDTO> getData() {
        return data;
    }

    public void setData(List<OutBoundDataDTO> data) {
        this.data = data;
    }
}
