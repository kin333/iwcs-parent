package com.wisdom.iwcs.domain.system;

/**
 * Created by Administrator on 2017/9/28 11:21.
 *
 * @Description
 * @Version
 */
public class DepartUser {
    private Integer id;

    private Integer departId;

    private Integer userId;

    private Integer companyId;
    /**
     * 1管辖部门，2是所属部门
     **/
    private Integer relationType;

    /**
     * 1为查看所有
     **/
    private Integer selectAll;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRelationType() {
        return relationType;
    }

    public void setRelationType(Integer relationType) {
        this.relationType = relationType;
    }

    public Integer getSelectAll() {
        return selectAll;
    }

    public void setSelectAll(Integer selectAll) {
        this.selectAll = selectAll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DepartUser that = (DepartUser) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (departId != null ? !departId.equals(that.departId) : that.departId != null) return false;
        if (companyId != null ? !companyId.equals(that.companyId) : that.companyId != null) return false;
        return userId != null ? userId.equals(that.userId) : that.userId == null;

    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (departId != null ? departId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
