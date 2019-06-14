package com.wisdom.iwcs.domain.system.dto;

import com.wisdom.iwcs.domain.system.DepartUser;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Devin
 * @date 2018-03-06.
 */
public class UsersToDepartmentDTO {

    private List<Integer> userIdList;
    private Integer departmentId;

    public List<DepartUser> getUserDepartmentList() {
        return this.userIdList.stream().map(u -> {
            DepartUser departUser = new DepartUser();
            departUser.setDepartId(this.departmentId);
            departUser.setUserId(u);
            return departUser;
        }).collect(toList());
    }

    public List<Integer> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Integer> userIdList) {
        this.userIdList = userIdList;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
