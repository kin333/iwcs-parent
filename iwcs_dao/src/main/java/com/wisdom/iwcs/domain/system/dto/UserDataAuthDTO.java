package com.wisdom.iwcs.domain.system.dto;

import java.util.List;

/**
 * 用户的数据权限
 *
 * @author Devin
 * @date 2018-04-03.
 */
public class UserDataAuthDTO {
    /**
     * 用户能查看制单(揽货)人id在这个集合里面的数据
     */
    private List<Integer> userIds;

    /**
     * 用户能查看制单(揽货)部门id在这个集合里面的数据
     */
    private List<Integer> departmentIds;

    /**
     * 能查看部门所有数据的部门id集合
     * 说明:用户能查看这些部门的所有数据
     */
    private List<Integer> globalDataDepartmentIds;

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public List<Integer> getDepartmentIds() {
        return departmentIds;
    }

    public void setDepartmentIds(List<Integer> departmentIds) {
        this.departmentIds = departmentIds;
    }

    public List<Integer> getGlobalDataDepartmentIds() {
        return globalDataDepartmentIds;
    }

    public void setGlobalDataDepartmentIds(List<Integer> globalDataDepartmentIds) {
        this.globalDataDepartmentIds = globalDataDepartmentIds;
    }

    @Override
    public String toString() {
        return "UserDataAuthDTO{" +
                "userIds=" + userIds +
                ", departmentIds=" + departmentIds +
                ", globalDataDepartmentIds=" + globalDataDepartmentIds +
                '}';
    }
}
