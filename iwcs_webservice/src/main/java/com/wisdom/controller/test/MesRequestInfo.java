package com.wisdom.controller.test;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MesRequestInfo {
    private String reqcode;
    private String taskCode;
    private String supplyLoadWb;
    private Integer supplyLoadNum;
    private String emptyRecyleWb;
    private Integer emptyRecyleNum;
    private String supplyUnLoadWb;
    private Integer supplyUnLoadNum;
    private String currentWb;
    private String agvCode;
    private String taskSta;
    private String doorAction;
    private String doorStatus;

    public String getReqcode() {
        return reqcode;
    }

    public void setReqcode(String reqcode) {
        this.reqcode = reqcode;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getSupplyLoadWb() {
        return supplyLoadWb;
    }

    public void setSupplyLoadWb(String supplyLoadWb) {
        this.supplyLoadWb = supplyLoadWb;
    }

    public Integer getSupplyLoadNum() {
        return supplyLoadNum;
    }

    public void setSupplyLoadNum(Integer supplyLoadNum) {
        this.supplyLoadNum = supplyLoadNum;
    }

    public String getEmptyRecyleWb() {
        return emptyRecyleWb;
    }

    public void setEmptyRecyleWb(String emptyRecyleWb) {
        this.emptyRecyleWb = emptyRecyleWb;
    }

    public Integer getEmptyRecyleNum() {
        return emptyRecyleNum;
    }

    public void setEmptyRecyleNum(Integer emptyRecyleNum) {
        this.emptyRecyleNum = emptyRecyleNum;
    }

    public String getSupplyUnLoadWb() {
        return supplyUnLoadWb;
    }

    public void setSupplyUnLoadWb(String supplyUnLoadWb) {
        this.supplyUnLoadWb = supplyUnLoadWb;
    }

    public Integer getSupplyUnLoadNum() {
        return supplyUnLoadNum;
    }

    public void setSupplyUnLoadNum(Integer supplyUnLoadNum) {
        this.supplyUnLoadNum = supplyUnLoadNum;
    }

    public String getCurrentWb() {
        return currentWb;
    }

    public void setCurrentWb(String currentWb) {
        this.currentWb = currentWb;
    }

    public String getAgvCode() {
        return agvCode;
    }

    public void setAgvCode(String agvCode) {
        this.agvCode = agvCode;
    }

    public String getTaskSta() {
        return taskSta;
    }

    public void setTaskSta(String taskSta) {
        this.taskSta = taskSta;
    }

    public String getDoorAction() {
        return doorAction;
    }

    public void setDoorAction(String doorAction) {
        this.doorAction = doorAction;
    }

    public String getDoorStatus() {
        return doorStatus;
    }

    public void setDoorStatus(String doorStatus) {
        this.doorStatus = doorStatus;
    }
}
