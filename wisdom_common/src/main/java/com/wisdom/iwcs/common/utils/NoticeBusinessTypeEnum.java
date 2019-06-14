package com.wisdom.iwcs.common.utils;

/**
 * Created by centling on 2018/5/30.
 */
public enum NoticeBusinessTypeEnum {

    //账单审批通过
    APPROVAL_SALE_PASS("sy", "您的账单通过了审核", "您的账单通过了审核，请注意查看账单的信息！"),
    APPROVAL_SALE_REJECT("sn", "您的账单了没有通过审核", "您的账单审批被驳回，请注意查看账单的驳回详情！"),
    /*APPROVAL_FINANCE_PASS("finy","您的账单通过了财务审核","您的账单通过了审核，请注意查看账单的信息！"),
    APPROVAL_FINANCE_REJECT_PASS("finn","您的账单了没有通过审核","您的账单审批被驳回，请注意查看账单的驳回详情！"),
    APPROVAL_MANAGER_PASS("my","您的账单通过了审核","您的账单通过了审核，请注意查看账单的信息！"),
    APPROVAL_MANAGER_REJECT_PASS("mn","您的账单了没有通过审核","您的账单审批被驳回，请注意查看账单的驳回详情！"),*/
    // 付费单审批
    APPROVAL_PAYBILL_PASS("pby", "您的付费账单通过审批", "您的付费单通过了审批，请留意查看付费单信息。"),
    APPROVAL_PAYBILL_REJECT("pbn", "您的付费账单了没有通过审批", "您的付费账单审批被驳回，请注意查看账单的驳回详情！");

    private String code;
    private String title;
    private String content;

    NoticeBusinessTypeEnum(String code, String title, String content) {
        this.code = code;
        this.title = title;
        this.content = content;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
