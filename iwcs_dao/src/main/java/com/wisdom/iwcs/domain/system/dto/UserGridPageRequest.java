package com.wisdom.iwcs.domain.system.dto;

import com.wisdom.iwcs.common.utils.GridPageRequest;

/**
 * @author Devin
 * @date 2018-03-10.
 */
public class UserGridPageRequest extends GridPageRequest {
    private Integer companyId;
    private Integer id;

    private String dutyType;

    public String getDutyType() {
        return dutyType;
    }

    public void setDutyType(String dutyType) {
        this.dutyType = dutyType;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
