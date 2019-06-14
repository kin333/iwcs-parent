package com.wisdom.iwcs.domain.system;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tl_t_custom_visit")
public class CustomVisit {
    @Id
    private Integer id;

    @Column(name = "plan_id")
    private Integer planId;

    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 客户Id
     */
    @Column(name = "customer_id")
    private Integer customerId;

    /**
     * 回访开始时间
     */
    @Column(name = "start_time")
    private Long startTime;

    /**
     * 回访结束时间
     */
    @Column(name = "end_time")
    private Long endTime;

    /**
     * 回访的内容记录
     */
    private String content;

    /**
     * 打卡id
     */
    @Column(name = "signin_id")
    private Integer signinId;

    /**
     * 创建人公司编码
     */
    @Column(name = "sys_company_code")
    private String sysCompanyCode;

    /**
     * 创建人部门编码
     */
    @Column(name = "sys_org_code")
    private String sysOrgCode;

    /**
     * 删除标志 0:否 1:是
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

    /**
     * 创建人/ 站长
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Long createdTime;

    /**
     * 修改人
     */
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    /**
     * 修改时间
     */
    @Column(name = "last_modified_time")
    private Long lastModifiedTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return plan_id
     */
    public Integer getPlanId() {
        return planId;
    }

    /**
     * @param planId
     */
    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    /**
     * 获取用户Id
     *
     * @return user_id - 用户Id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户Id
     *
     * @param userId 用户Id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取客户Id
     *
     * @return customer_id - 客户Id
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * 设置客户Id
     *
     * @param customerId 客户Id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取回访开始时间
     *
     * @return start_time - 回访开始时间
     */
    public Long getStartTime() {
        return startTime;
    }

    /**
     * 设置回访开始时间
     *
     * @param startTime 回访开始时间
     */
    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取回访结束时间
     *
     * @return end_time - 回访结束时间
     */
    public Long getEndTime() {
        return endTime;
    }

    /**
     * 设置回访结束时间
     *
     * @param endTime 回访结束时间
     */
    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取回访的内容记录
     *
     * @return content - 回访的内容记录
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置回访的内容记录
     *
     * @param content 回访的内容记录
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 获取打卡id
     *
     * @return signin_id - 打卡id
     */
    public Integer getSigninId() {
        return signinId;
    }

    /**
     * 设置打卡id
     *
     * @param signinId 打卡id
     */
    public void setSigninId(Integer signinId) {
        this.signinId = signinId;
    }

    /**
     * 获取创建人公司编码
     *
     * @return sys_company_code - 创建人公司编码
     */
    public String getSysCompanyCode() {
        return sysCompanyCode;
    }

    /**
     * 设置创建人公司编码
     *
     * @param sysCompanyCode 创建人公司编码
     */
    public void setSysCompanyCode(String sysCompanyCode) {
        this.sysCompanyCode = sysCompanyCode == null ? null : sysCompanyCode.trim();
    }

    /**
     * 获取创建人部门编码
     *
     * @return sys_org_code - 创建人部门编码
     */
    public String getSysOrgCode() {
        return sysOrgCode;
    }

    /**
     * 设置创建人部门编码
     *
     * @param sysOrgCode 创建人部门编码
     */
    public void setSysOrgCode(String sysOrgCode) {
        this.sysOrgCode = sysOrgCode == null ? null : sysOrgCode.trim();
    }

    /**
     * 获取删除标志 0:否 1:是
     *
     * @return delete_flag - 删除标志 0:否 1:是
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置删除标志 0:否 1:是
     *
     * @param deleteFlag 删除标志 0:否 1:是
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    /**
     * 获取创建人/ 站长
     *
     * @return created_by - 创建人/ 站长
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人/ 站长
     *
     * @param createdBy 创建人/ 站长
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Long getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
     */
    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取修改人
     *
     * @return last_modified_by - 修改人
     */
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * 设置修改人
     *
     * @param lastModifiedBy 修改人
     */
    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * 获取修改时间
     *
     * @return last_modified_time - 修改时间
     */
    public Long getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * 设置修改时间
     *
     * @param lastModifiedTime 修改时间
     */
    public void setLastModifiedTime(Long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}