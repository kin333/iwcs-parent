package com.wisdom.iwcs.common.utils.syncUtils;

/**
 * @author Devin
 * @date 2018-04-25.
 */
public enum PodDirectionEnum {
    DIRECTION_EAST(1, "东"),
    DIRECTION_WEST(2, "西"),
    DIRECTION_SOUTH(3, "南"),
    DIRECTION_NORTH(4, "北"),
    DIRECTION_MIDDLE(5, "中");


    PodDirectionEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private Integer code;
    private String name;

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
