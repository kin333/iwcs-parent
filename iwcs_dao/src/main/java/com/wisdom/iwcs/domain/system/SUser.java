package com.wisdom.iwcs.domain.system;

import java.util.Date;

public class SUser {
    private Integer id;

    private String userName;

    private Integer isSysAccount;

    private String password;

    private String realName;

    private String email;

    private String mobile;

    private String employeeNumber;

    private String duty;

    private String composeName;

    private String sex;

    /**
     * 是否是商务 1为是
     **/
    private Integer isBusiness;
    /**
     * 是否销售 1为是
     **/
    private Integer isSales;
    /**
     * 是否是操作 1为是
     **/
    private Integer isOperation;

    private Byte status;

    private Integer createdBy;

    private Date createdTime;

    private Integer lastModifiedBy;

    private Date lastModifiedTime;

    private String workAddress;

    private String wechat;

    private Integer deleteFlag;

    private Integer superAdminFlag;

    private UserInfoExt userInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getIsSysAccount() {
        return isSysAccount;
    }

    public void setIsSysAccount(Integer isSysAccount) {
        this.isSysAccount = isSysAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber == null ? null : employeeNumber.trim();
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty == null ? null : duty.trim();
    }

    public String getComposeName() {
        return composeName;
    }

    public void setComposeName(String composeName) {
        this.composeName = composeName == null ? null : composeName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress == null ? null : workAddress.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public UserInfoExt getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoExt userInfo) {
        this.userInfo = userInfo;
    }

    public Integer getSuperAdminFlag() {
        return superAdminFlag;
    }

    public void setSuperAdminFlag(Integer superAdminFlag) {
        this.superAdminFlag = superAdminFlag;
    }


    public Integer getIsBusiness() {
        return isBusiness;
    }

    public void setIsBusiness(Integer isBusiness) {
        this.isBusiness = isBusiness;
    }

    public Integer getIsSales() {
        return isSales;
    }

    public void setIsSales(Integer isSales) {
        this.isSales = isSales;
    }

    public Integer getIsOperation() {
        return isOperation;
    }

    public void setIsOperation(Integer isOperation) {
        this.isOperation = isOperation;
    }
}