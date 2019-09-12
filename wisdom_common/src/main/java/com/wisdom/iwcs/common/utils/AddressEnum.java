package com.wisdom.iwcs.common.utils;

public enum AddressEnum {

    BIND_POD_AND_BERTH("bindPodAndMatUrl", "/rcs/services/rest/hikRpcService/bindPodAndBerth","海康接口的地址绑定解绑操作");
    //代码
    private String code;
    //值
    private String value;
    //名称含义
    private String name;

    AddressEnum(String code, String value, String name) {
        this.code = code;
        this.value = value;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
