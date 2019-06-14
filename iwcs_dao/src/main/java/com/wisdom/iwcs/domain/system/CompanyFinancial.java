package com.wisdom.iwcs.domain.system;

import java.util.Date;

/**
 * @author Devin
 * @date 2018-05-08.
 * 公司财务状态
 */
public class CompanyFinancial {

    private Integer companyId;
    private Date financeDate;
    private String financeStatus;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Date getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(Date financeDate) {
        this.financeDate = financeDate;
    }

    public String getFinanceStatus() {
        return financeStatus;
    }

    public void setFinanceStatus(String financeStatus) {
        this.financeStatus = financeStatus;
    }
}
