package com.wisdom.iwcs.domain.system.dto;

import com.wisdom.iwcs.domain.system.SUser;

import java.util.Set;

/**
 * @author Devin
 * @date 2018-03-09.
 */
public class CurrentUserInfo extends SUser {
    private Integer currentCompanyId;
    private Boolean isSuperAdmin;
    private Set<String> authorities;
    /**
     * 当前登录用户的所属部门信息
     */
    private Integer departmentId;
    private String departmentName;
    private String currentCompanyName;

    public Integer getCurrentCompanyId() {
        return currentCompanyId;
    }

    public void setCurrentCompanyId(Integer currentCompanyId) {
        this.currentCompanyId = currentCompanyId;
    }

    public Boolean getSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCurrentCompanyName() {
        return currentCompanyName;
    }

    public void setCurrentCompanyName(String currentCompanyName) {
        this.currentCompanyName = currentCompanyName;
    }
}
