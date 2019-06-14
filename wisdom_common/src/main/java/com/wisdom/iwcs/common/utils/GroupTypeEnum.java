package com.wisdom.iwcs.common.utils;

/**
 * 小组类型
 *
 * @author Devin
 * @date 2018-04-03.
 */
public enum GroupTypeEnum {

    MAKE_ORDER("1", "制单"),

    CARGO_CANVASSING("2", "销售"),

    BUSINESS("3", "商务");

    private String code;
    private String name;

    GroupTypeEnum(String code, String name) {
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
