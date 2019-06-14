package com.wisdom.iwcs.common.utils;

/**
 * @author Devin
 * @date 2018-05-08.
 */
public enum CompanyFinancialStatusEnum {

    LOCK("1", "锁定"),
    NO_LOCK("0", "未锁定");

    CompanyFinancialStatusEnum(String code, String name) {
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
