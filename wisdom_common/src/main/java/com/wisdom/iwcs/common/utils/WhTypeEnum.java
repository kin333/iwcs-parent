package com.wisdom.iwcs.common.utils;

/**
 * @author cecilia.yang
 * @date 2019-02-21
 */
public enum WhTypeEnum {
    TRADITIONAL(0, "传统库"),
    INTELLIGENCE(1, "AGV仓");


    WhTypeEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private Integer code;
    private String name;

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
