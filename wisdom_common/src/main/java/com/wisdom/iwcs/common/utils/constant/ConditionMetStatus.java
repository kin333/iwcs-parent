package com.wisdom.iwcs.common.utils.constant;

/**
 * 条件状态
 * @author han
 */
public enum ConditionMetStatus {
    CONFORMITY("1", "已符合"),
    IN_CONFORMITY("0", "不符合");

    ConditionMetStatus(String code, String name) {
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
