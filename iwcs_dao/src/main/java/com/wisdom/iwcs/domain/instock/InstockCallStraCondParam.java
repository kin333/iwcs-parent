package com.wisdom.iwcs.domain.instock;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "instock_call_stra_cond_param")
public class InstockCallStraCondParam {
    @Id
    private Integer id;

    /**
     * 关系code
     */
    @Column(name = "cond_code")
    private String condCode;

    /**
     * 参数id
     */
    @Column(name = "param_id")
    private String paramId;

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
     * 获取关系code
     *
     * @return cond_code - 关系code
     */
    public String getCondCode() {
        return condCode;
    }

    /**
     * 设置关系code
     *
     * @param condCode 关系code
     */
    public void setCondCode(String condCode) {
        this.condCode = condCode == null ? null : condCode.trim();
    }

    /**
     * 获取参数id
     *
     * @return param_id - 参数id
     */
    public String getParamId() {
        return paramId;
    }

    /**
     * 设置参数id
     *
     * @param paramId 参数id
     */
    public void setParamId(String paramId) {
        this.paramId = paramId == null ? null : paramId.trim();
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