package com.wisdom.iwcs.domain.system.dto;

import java.util.List;

/**
 * @author Devin
 * @date 2018-03-05.
 */
public class RoleAuthDTO {
    private Integer roleId;
    private Integer authType;
    private List<Integer> authIdList;

    private Integer authParentId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public List<Integer> getAuthIdList() {
        return authIdList;
    }

    public void setAuthIdList(List<Integer> authIdList) {
        this.authIdList = authIdList;
    }

    public Integer getAuthParentId() {
        return authParentId;
    }

    public void setAuthParentId(Integer authParentId) {
        this.authParentId = authParentId;
    }
}
