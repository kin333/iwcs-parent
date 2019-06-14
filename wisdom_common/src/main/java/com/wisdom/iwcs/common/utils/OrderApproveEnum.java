package com.wisdom.iwcs.common.utils;

/**
 * @author Devin
 * @date 2018-04-25.
 */
public enum OrderApproveEnum {

    SALES_APPROVE("SALES_APPROVE", 10, "销售审核", "sales"),

    FINANCE_APPROVE("FINANCE_APPROVE", 30, "财务审核", "other"),

    MANAGER_APPROVE("MANAGER_APPROVE", 20, "经理审核", "other");

    private String code;
    /**
     * 顺序
     */
    private Integer order;
    private String name;
    /**
     * 获取数据市权限时类型
     * sales，other
     */
    private String dataAuthType;

    OrderApproveEnum(String code, Integer order, String name, String dataAuthType) {
        this.code = code;
        this.order = order;
        this.name = name;
        this.dataAuthType = dataAuthType;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getDataAuthType() {
        return dataAuthType;
    }

    public void setDataAuthType(String dataAuthType) {
        this.dataAuthType = dataAuthType;
    }
}
