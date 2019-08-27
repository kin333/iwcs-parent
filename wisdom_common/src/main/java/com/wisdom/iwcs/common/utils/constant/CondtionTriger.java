package com.wisdom.iwcs.common.utils.constant;

/**
 * 标识前置和后置条件的code
 * @author han
 */
public enum CondtionTriger {
    PRE_CONDITION("pre", "前置条件"),
    POST_CONDITION("after", "后置条件"),
    CREATED_CONDITION("created", "创建条件");


    CondtionTriger(String code, String name) {
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
