package com.wisdom.iwcs.common.utils;

/**
 * @author Devin
 * @date 2018-03-20.
 * 客户性质：（使用英文逗号分隔）a:订舱客户,b:订舱代理, c:场站, d:车队, e:船代, f:海外代理, g:国内代理, h:报关行,i:快递公司,j:部门, k:其他
 */
public enum CustomerPropertyEnum {
    A("a", "订舱客户"),
    B("b", "订舱代理"),
    C("c", "场站"),
    D("d", "车队"),
    E("e", "船代"),
    F("f", "海外代理"),
    G("g", "国内代理"),
    H("h", "报关行"),
    I("i", "快递公司"),
    J("j", "部门"),
    K("k", "地面服务"),
    L("l", "其他"),
    M("m", "换单代理"),
    N("n", "仓储"),
    O("o", "报检"),
    P("p", "提单代理"),
    Q("q", "监管车"),
    R("r", "保险公司");


    private String code;
    private String name;

    CustomerPropertyEnum(String code, String name) {
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
