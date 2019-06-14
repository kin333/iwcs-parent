package com.wisdom.iwcs.domain.system;

import java.util.Date;

/**
 * Created by Administrator on 2017/9/27 13:34.
 *
 * @Description
 * @Version 这是 组织部门的 实体类
 */
public class Depart {
    private Integer id;

    private String departname;

    private String description;

    private Integer parentdepartid;

    private String orgCode;
    /**
     * 1=公司 2=部门
     */
    private String orgType;

    private String mobile;

    private String fax;

    private String address;

    private String defaultPortCode;

    private String defaultAirPortCode;

    private Date financeDate;

    private String financeStatus;

    private String departOrder;

    private Integer createdBy;

    private Integer lastModifiedBy;

    private Long lastModifiedTime;

    private Boolean deleteFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartname() {
        return departname;
    }

    public void setDepartname(String departname) {
        this.departname = departname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getParentdepartid() {
        return parentdepartid;
    }

    public void setParentdepartid(Integer parentdepartid) {
        this.parentdepartid = parentdepartid;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartOrder() {
        return departOrder;
    }

    public void setDepartOrder(String departOrder) {
        this.departOrder = departOrder;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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

    public String getDefaultPortCode() {
        return defaultPortCode;
    }

    public void setDefaultPortCode(String defaultPortCode) {
        this.defaultPortCode = defaultPortCode;
    }

    public Date getFinanceDate() {
        return financeDate;
    }

    public void setFinanceDate(Date financeDate) {
        this.financeDate = financeDate;
    }

    public String getFinanceStatus() {
        return financeStatus;
    }

    public void setFinanceStatus(String financeStatus) {
        this.financeStatus = financeStatus;
    }

    public String getDefaultAirPortCode() {
        return defaultAirPortCode;
    }

    public void setDefaultAirPortCode(String defaultAirPortCode) {
        this.defaultAirPortCode = defaultAirPortCode;
    }
}
