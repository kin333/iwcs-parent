package com.wisdom.iwcs.common.utils;

/**
 * @author Devin
 * @date 2018-06-29.
 * oa 审批类型
 */
public enum OfficeApproveEnum {

    BECOME_APPROVE("become_approve", "转正审批", false),
    VACATION_APPROVE("vacation_approve", "假期审批", false),
    TRAVEL_APPROVE("travel_approve", "出差审批", false),
    REIMBURSEMENT_APPROVE("reimbursement_approve", "报销审批", false),
    CUSTOMER_VISIT("customer_visit", "拜访客户审批", false),

    TRAVEL_CANCEL("travel_cancel", "出差取消", true),
    VACATION_ROLLBACK("vacation_rollback", "假期取消", true);


    private String code;
    private String name;
    private Boolean cancelApprove;


    OfficeApproveEnum(String code, String name, Boolean cancelApprove) {
        this.code = code;
        this.name = name;
        this.cancelApprove = cancelApprove;
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

    public Boolean getCancelApprove() {
        return cancelApprove;
    }

    public void setCancelApprove(Boolean cancelApprove) {
        this.cancelApprove = cancelApprove;
    }
}
