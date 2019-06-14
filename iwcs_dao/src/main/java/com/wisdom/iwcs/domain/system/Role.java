package com.wisdom.iwcs.domain.system;

import com.google.common.base.MoreObjects;

public class Role {
    private Integer id;

    private String rolecode;

    private String roleName;

    private Boolean isSysRole;

    private Boolean available;

    private Integer companyId;

    private Integer createdBy;

    private Long createdTime;

    private Integer lastModifiedBy;

    private Long lastModifiedTime;

    private Boolean deleteFlag;

    public Boolean getSysRole() {
        return isSysRole;
    }

    public void setSysRole(Boolean sysRole) {
        isSysRole = sysRole;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRolecode() {
        return rolecode;
    }

    public void setRolecode(String rolecode) {
        this.rolecode = rolecode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getIsSysRole() {
        return isSysRole;
    }

    public void setIsSysRole(Boolean isSysRole) {
        this.isSysRole = isSysRole;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Boolean getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Boolean deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
                .add("Hash", hashCode())
                .add("id", id).add("rolecode", rolecode).add("roleName", roleName)
                .add("isSysRole", isSysRole).add("available", available).add("createdBy", createdBy).add("createdTime", createdTime)
                .add("lastModifiedBy", lastModifiedBy).add("lastModifiedTime", lastModifiedTime).add("deleteFlag", deleteFlag)
                .omitNullValues().toString();
    }
}