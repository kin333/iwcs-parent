package com.wisdom.iwcs.common.utils;

/**
 * @author Devin
 * @date 2018-05-03.
 * 业务代码类型
 */
public enum BusinessTypeEnum {

    SHIPPING_COMPANY("SHIPPING_COMPANY", "船公司"),
    AIRLINE_COMPANY("AIRLINE_COMPANY", "航空公司"),
    AIRPLANE_NAME("AIRPLANE_NAME", "飞机名"),
    CASH_SUBJECT("CASH_SUBJECT", "现金科目"),
    IMPORT_SERVICE_PROJECT("IMPORT_SERVICE_PROJECT", "进口服务项目"),
    AIR_TRANSPORTATION_SERVICE_PROJECT("AIR_TRANSPORTATION_SERVICE_PROJECT", "空运服务项目"),
    FREIGHT_RATE_GRADE("FREIGHT_RATE_GRADE", "运价等级");


    BusinessTypeEnum(String code, String name) {
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
