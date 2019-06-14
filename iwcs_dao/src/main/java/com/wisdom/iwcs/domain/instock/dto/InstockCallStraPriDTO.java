package com.wisdom.iwcs.domain.instock.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "instock_call_stra_pri")
public class InstockCallStraPriDTO {
    @Id
    private Integer id;

    /**
     * 策略优先级code
     */
    @Column(name = "priority_code")
    private String priorityCode;

    /**
     * 优先级参数
     */
    private String priority;

    /**
     * 业务代码
     */
    @Column(name = "biz_code")
    private String bizCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 激活状态
     */
    private Byte status;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Integer createdBy;

    /**
     * 创建时间
     */
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
     * 删除标记 0为正常 1为删除
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
     * 获取策略优先级code
     *
     * @return priority_code - 策略优先级code
     */
    public String getPriorityCode() {
        return priorityCode;
    }

    /**
     * 设置策略优先级code
     *
     * @param priorityCode 策略优先级code
     */
    public void setPriorityCode(String priorityCode) {
        this.priorityCode = priorityCode == null ? null : priorityCode.trim();
    }

    /**
     * 获取优先级参数
     *
     * @return priority - 优先级参数
     */
    public String getPriority() {
        return priority;
    }

    /**
     * 设置优先级参数
     *
     * @param priority 优先级参数
     */
    public void setPriority(String priority) {
        this.priority = priority == null ? null : priority.trim();
    }

    /**
     * 获取业务代码
     *
     * @return biz_code - 业务代码
     */
    public String getBizCode() {
        return bizCode;
    }

    /**
     * 设置业务代码
     *
     * @param bizCode 业务代码
     */
    public void setBizCode(String bizCode) {
        this.bizCode = bizCode == null ? null : bizCode.trim();
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取激活状态
     *
     * @return status - 激活状态
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * 设置激活状态
     *
     * @param status 激活状态
     */
    public void setStatus(Byte status) {
        this.status = status;
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
     * 获取创建时间
     *
     * @return created_time - 创建时间
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置创建时间
     *
     * @param createdTime 创建时间
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
     * 获取删除标记 0为正常 1为删除
     *
     * @return delete_flag - 删除标记 0为正常 1为删除
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置删除标记 0为正常 1为删除
     *
     * @param deleteFlag 删除标记 0为正常 1为删除
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}