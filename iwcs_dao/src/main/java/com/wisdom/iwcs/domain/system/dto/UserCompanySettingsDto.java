package com.wisdom.iwcs.domain.system.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "s_user_company_settings")
public class UserCompanySettingsDto {
    @Id
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 公司id,depart表
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 是否是商务 1为是
     */
    @Column(name = "is_business")
    private Integer isBusiness;

    /**
     * 是否销售1为是
     */
    @Column(name = "is_sales")
    private Integer isSales;

    /**
     * 是否是操作1为是
     */
    @Column(name = "is_operation")
    private Integer isOperation;

    /**
     * 是否是客服,1为是
     */
    @Column(name = "is_service")
    private Integer isService;

    /**
     * 是否是单证
     */
    @Column(name = "is_document")
    private Integer isDocument;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_time")
    private Date createdTime;

    /**
     * 更新人
     */
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    /**
     * 更新时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    /**
     * 删除标识：0-未删除，1-已删除
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取公司id,depart表
     *
     * @return company_id - 公司id,depart表
     */
    public Integer getCompanyId() {
        return companyId;
    }

    /**
     * 设置公司id,depart表
     *
     * @param companyId 公司id,depart表
     */
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    /**
     * 获取是否是商务 1为是
     *
     * @return is_business - 是否是商务 1为是
     */
    public Integer getIsBusiness() {
        return isBusiness;
    }

    /**
     * 设置是否是商务 1为是
     *
     * @param isBusiness 是否是商务 1为是
     */
    public void setIsBusiness(Integer isBusiness) {
        this.isBusiness = isBusiness;
    }

    /**
     * 获取是否销售1为是
     *
     * @return is_sales - 是否销售1为是
     */
    public Integer getIsSales() {
        return isSales;
    }

    /**
     * 设置是否销售1为是
     *
     * @param isSales 是否销售1为是
     */
    public void setIsSales(Integer isSales) {
        this.isSales = isSales;
    }

    /**
     * 获取是否是操作1为是
     *
     * @return is_operation - 是否是操作1为是
     */
    public Integer getIsOperation() {
        return isOperation;
    }

    /**
     * 设置是否是操作1为是
     *
     * @param isOperation 是否是操作1为是
     */
    public void setIsOperation(Integer isOperation) {
        this.isOperation = isOperation;
    }

    /**
     * 获取是否是客服,1为是
     *
     * @return is_service - 是否是客服,1为是
     */
    public Integer getIsService() {
        return isService;
    }

    /**
     * 设置是否是客服,1为是
     *
     * @param isService 是否是客服,1为是
     */
    public void setIsService(Integer isService) {
        this.isService = isService;
    }

    /**
     * 获取是否是单证
     *
     * @return is_document - 是否是单证
     */
    public Integer getIsDocument() {
        return isDocument;
    }

    /**
     * 设置是否是单证
     *
     * @param isDocument 是否是单证
     */
    public void setIsDocument(Integer isDocument) {
        this.isDocument = isDocument;
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public Integer getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return created_time
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取更新人
     *
     * @return last_modified_by - 更新人
     */
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * 设置更新人
     *
     * @param lastModifiedBy 更新人
     */
    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * 获取更新时间
     *
     * @return last_modified_time - 更新时间
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * 设置更新时间
     *
     * @param lastModifiedTime 更新时间
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    /**
     * 获取删除标识：0-未删除，1-已删除
     *
     * @return delete_flag - 删除标识：0-未删除，1-已删除
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置删除标识：0-未删除，1-已删除
     *
     * @param deleteFlag 删除标识：0-未删除，1-已删除
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}