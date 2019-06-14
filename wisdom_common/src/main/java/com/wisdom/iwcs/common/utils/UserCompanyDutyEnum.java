package com.wisdom.iwcs.common.utils;

/**
 * @author Devin
 * @date 2018-04-08.
 */
public enum UserCompanyDutyEnum {
    BUSINESS("business", "商务"),
    SALES("sales", "销售"),
    OPERATION("operation", "操作"),
    SERVICE("service", "客服"),
    DOCUMENT("document", "单证");


    private String code;
    private String name;

    UserCompanyDutyEnum(String code, String name) {
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
