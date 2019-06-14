package com.wisdom.iwcs.common.utils;

/**
 * @author Devin
 * @date 2018-04-25.
 */
public enum ApproveStatusEnum {

    NO_START(0, "审批未开始"),

    WAIT_APPROVE(1, "等待审批/(正在审批)"),

    APPROVED(2, "审批完成");


    private Integer code;
    private String name;


    ApproveStatusEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

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
