package com.wisdom.iwcs.common.utils;

/**
 * @author Devin
 * @date 2018-04-20.
 * 流程审批结果
 */
public enum ProcessResultEnum {


    AGREE("1", "同意"),
    REJECT("2", "驳回"),
    UNDER_APPROVAL("3", "正在审批");

    private String code;
    private String name;

    ProcessResultEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
