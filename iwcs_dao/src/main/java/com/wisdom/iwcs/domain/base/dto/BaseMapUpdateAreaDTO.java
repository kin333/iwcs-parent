package com.wisdom.iwcs.domain.base.dto;

import java.util.List;

public class BaseMapUpdateAreaDTO {

    private List<Integer> id;
    private String operateAreaCode;
    private String bizType;
    private String bizSecondAreaCode;
    private Boolean operateAreaBool;
    private Boolean bizTypeBool;
    private Boolean bizSecondAreaBool;

    public List<Integer> getId() {
        return id;
    }

    public void setId(List<Integer> id) {
        this.id = id;
    }

    public String getOperateAreaCode() {
        return operateAreaCode;
    }

    public void setOperateAreaCode(String operateAreaCode) {
        this.operateAreaCode = operateAreaCode;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizSecondAreaCode() {
        return bizSecondAreaCode;
    }

    public void setBizSecondAreaCode(String bizSecondAreaCode) {
        this.bizSecondAreaCode = bizSecondAreaCode;
    }

    public Boolean getOperateAreaBool() {
        return operateAreaBool;
    }

    public void setOperateAreaBool(Boolean operateAreaBool) {
        this.operateAreaBool = operateAreaBool;
    }

    public Boolean getBizTypeBool() {
        return bizTypeBool;
    }

    public void setBizTypeBool(Boolean bizTypeBool) {
        this.bizTypeBool = bizTypeBool;
    }

    public Boolean getBizSecondAreaBool() {
        return bizSecondAreaBool;
    }

    public void setBizSecondAreaBool(Boolean bizSecondAreaBool) {
        this.bizSecondAreaBool = bizSecondAreaBool;
    }
}
