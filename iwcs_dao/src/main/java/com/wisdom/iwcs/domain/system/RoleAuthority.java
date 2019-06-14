package com.wisdom.iwcs.domain.system;

import com.google.common.base.MoreObjects;

public class RoleAuthority {
    private Integer id;

    private Integer roleid;

    private Integer authorityId;

    private String dataRule;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }

    public String getDataRule() {
        return dataRule;
    }

    public void setDataRule(String dataRule) {
        this.dataRule = dataRule;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
                .add("Hash", hashCode())
                .add("id", id).add("roleid", roleid).add("authorityId", authorityId)
                .add("dataRule", dataRule)
                .omitNullValues().toString();
    }
}