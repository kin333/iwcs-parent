package com.wisdom.iwcs.common.utils;

/**
 * Created by cecilia.yang
 */
public class GridBillPageRequest extends GridPageRequest {
    private String billType;

    private String billTypeComplex;

    public String getBillTypeComplex() {
        return billTypeComplex;
    }

    public void setBillTypeComplex(String billTypeComplex) {
        this.billTypeComplex = billTypeComplex;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }
}
