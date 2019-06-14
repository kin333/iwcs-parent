package com.wisdom.iwcs.common.utils;

/**
 * @author Devin
 * @date 2018-06-08.
 * 组织类型
 */
public enum OrganizationTypeEnum {

    COMPANY("1", "公司"),
    DEPARTMENT("2", "部门");


    OrganizationTypeEnum(String code, String name) {
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
