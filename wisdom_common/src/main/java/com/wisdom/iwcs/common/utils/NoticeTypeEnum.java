package com.wisdom.iwcs.common.utils;

/**
 * @author Devin
 * @date 2018-07-10.
 */
public enum NoticeTypeEnum {

    SYSTEM_NOTICE(0, "系统"),

    PERSON_NOTICE(1, "用户");

    private Integer code;
    private String name;


    NoticeTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
