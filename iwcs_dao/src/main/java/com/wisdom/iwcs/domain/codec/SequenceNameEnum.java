package com.wisdom.iwcs.domain.codec;

public enum SequenceNameEnum {

    OrderNo("OrderNo", "业务流水号"),
    InternalBlNo("InternalBlNo", "分单号"),
    AirBlNo("AirBlNo", "空运提单号"),
    FOrderNo("FOrderNo", "财务账单号"),
    FSettlementNo("FSettlementNo", "收付费结算单号"),
    FPaymentNo("FPaymentNo", "收付费结费单号"),
    BillInfoNo("BillInfoNo", "收付费结费单号"),
    InvoiceNo("InvoiceNo", "国际发票号");

    private String sequenceName;
    private String remark;

    SequenceNameEnum(String sequenceName, String remark) {
        this.setSequenceName(sequenceName);
        this.setRemark(remark);
    }


    @Override
    public String toString() {
        return String.valueOf(this.getSequenceName());
    }


    /**
     * 获得描述字符串--用于记录log
     *
     * @param
     * @return
     */
    public String getDisplayStr() {
        return "{ 'sequenceName': '" + this.sequenceName + "';'" + "'remark':'" + this.remark + "'}";
    }


    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
