package com.wisdom.iwcs.domain.system;

/**
 * Created by Administrator on 2017/9/28 11:21.
 *
 * @Description
 * @Version
 */
public class DepartRole {
    private Integer id;

    private Integer departId;

    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDepartId() {
        return departId;
    }

    public void setDepartId(Integer departId) {
        this.departId = departId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
