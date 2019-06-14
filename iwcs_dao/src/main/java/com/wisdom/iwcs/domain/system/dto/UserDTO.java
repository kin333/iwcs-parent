package com.wisdom.iwcs.domain.system.dto;

import com.wisdom.iwcs.domain.system.SUser;

import java.util.List;

/**
 * @author Devin
 * @date 2018-03-01.
 */
public class UserDTO extends SUser {

    private List<Integer> roleList;
    private List<Integer> departmentList;

    private List<String> dutyList;

    private Integer departmentId;

    private Integer selectAll;

    private Integer companyId;

    public List<Integer> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Integer> roleList) {
        this.roleList = roleList;
    }

    public List<Integer> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Integer> departmentList) {
        this.departmentList = departmentList;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getSelectAll() {
        return selectAll;
    }

    public void setSelectAll(Integer selectAll) {
        this.selectAll = selectAll;
    }

    public List<String> getDutyList() {
        return dutyList;
    }

    public void setDutyList(List<String> dutyList) {
        this.dutyList = dutyList;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
