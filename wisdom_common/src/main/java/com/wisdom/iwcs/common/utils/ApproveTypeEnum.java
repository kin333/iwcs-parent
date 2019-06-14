package com.wisdom.iwcs.common.utils;

/**
 * @author Devin
 * @date 2018-04-21.
 */
public enum ApproveTypeEnum {

    PAY("pay", "付费审核", true),

    CHARGE("charge", "收费", false),

    ORDER_APPROVE("order_approve", "账单审核", false),

    RELEASE_ORDER("release_order", "放单审核", false);


    private String code;
    private String name;
    private Boolean enable;

    ApproveTypeEnum(String code, String name, Boolean enable) {
        this.code = code;
        this.name = name;
        this.enable = enable;
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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }


}
