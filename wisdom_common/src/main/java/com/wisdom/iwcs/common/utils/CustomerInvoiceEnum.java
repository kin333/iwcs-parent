package com.wisdom.iwcs.common.utils;

/**
 * @author cecilia.yang
 * @date 2018-03-31.
 * (NORL=一般纳税人 SMAL=小规模纳税人 NONE=非增值税纳税人)
 */
public enum CustomerInvoiceEnum {
    NORL("NORL", "一般纳税人"),
    SMAL("SMAL", "小规模纳税人"),
    NONE("NONE", "非增值税纳税人");


    private String code;
    private String name;

    CustomerInvoiceEnum(String code, String name) {
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
