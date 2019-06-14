package com.wisdom.iwcs.domain.system.dto;

import com.wisdom.iwcs.domain.system.Role;

/**
 * @author Devin
 * @date 2018-03-06.
 */
public class RoleDTO extends Role {

    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
