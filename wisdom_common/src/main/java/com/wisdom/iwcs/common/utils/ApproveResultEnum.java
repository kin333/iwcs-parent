package com.wisdom.iwcs.common.utils;

/**
 * @author Devin
 * @date 2018-04-25.
 */
public enum ApproveResultEnum {
    AGREE("agree", "同意"),
    AGAINST("against", "驳回");


    ApproveResultEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;

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
