package com.wisdom.iwcs.domain.system.dto;

/**
 * @author Devin
 * @date 2018-03-07.
 */
public class CompanyRoleDTO {

    private Integer companyId;
    private Integer userId;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
