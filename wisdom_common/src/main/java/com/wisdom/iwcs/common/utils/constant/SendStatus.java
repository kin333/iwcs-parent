package com.wisdom.iwcs.common.utils.constant;

/**
 * 下发状态
 * @author han
 */
public enum SendStatus {
    SEND("1", "已下发"),
    NOT_SEND("0", "未下发");

    SendStatus(String code, String name) {
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
