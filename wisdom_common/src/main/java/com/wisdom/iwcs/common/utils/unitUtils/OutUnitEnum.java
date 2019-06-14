package com.wisdom.iwcs.common.utils.unitUtils;

/**
 * @author cecilia
 * @date 2018-04-25.
 */
public enum OutUnitEnum {
    PACKAGE_UNIT("pkg", "件数");


    OutUnitEnum(String code, String name) {
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
