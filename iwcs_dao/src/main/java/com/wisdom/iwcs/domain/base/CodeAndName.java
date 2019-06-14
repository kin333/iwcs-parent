package com.wisdom.iwcs.domain.base;

/**
 * 类型信息类
 *
 * @author han
 */
public class CodeAndName {
    /**
     * 仓位类型编码
     */
    private String Code;
    /**
     * 仓位类型名称
     */
    private String Name;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
