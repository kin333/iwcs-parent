package com.wisdom.iwcs.domain.log;

import java.util.Date;
import javax.persistence.*;

@Table(name = "task_operation_log")
public class TaskOperationLog {
    @Id
    private Integer id;

    /**
     * 子任务编号
     */
    @Column(name = "sub_task_num")
    private String subTaskNum;

    /**
     * 当前任务操作状态
     */
    @Column(name = "operation_status")
    private String operationStatus;

    /**
     * 任务下发消息体
     */
    @Column(name = "operation_content")
    private String operationContent;

    /**
     * 操作结果状态，0成功，1失败
     */
    @Column(name = "result_flag")
    private String resultFlag;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标记，0未删除，1删除
     */
    @Column(name = "delete_flag")
    private Integer deleteFlag;

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
     * 最后修改人
     */
    @Column(name = "last_modified_by")
    private Integer lastModifiedBy;

    /**
     * 最后修改时间
     */
    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

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
     * 获取子任务编号
     *
     * @return sub_task_num - 子任务编号
     */
    public String getSubTaskNum() {
        return subTaskNum;
    }

    /**
     * 设置子任务编号
     *
     * @param subTaskNum 子任务编号
     */
    public void setSubTaskNum(String subTaskNum) {
        this.subTaskNum = subTaskNum == null ? null : subTaskNum.trim();
    }

    /**
     * 获取当前任务操作状态
     *
     * @return send_status - 当前任务操作状态
     */
    public String getOperationStatus() {
        return operationStatus;
    }

    /**
     * 设置当前任务操作状态
     *
     * @param operationStatus 当前任务操作状态
     */
    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus == null ? null : operationStatus.trim();
    }

    /**
     * 获取任务下发消息体
     *
     * @return send_content - 任务下发消息体
     */
    public String getOperationContent() {
        return operationContent;
    }

    /**
     * 设置任务下发消息体
     *
     * @param operationContent 任务下发消息体
     */
    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent == null ? null : operationContent.trim();
    }

    /**
     * 获取操作结果状态，0成功，1失败
     *
     * @return result_flag - 操作结果状态，0成功，1失败
     */
    public String getResultFlag() {
        return resultFlag;
    }

    /**
     * 设置操作结果状态，0成功，1失败
     *
     * @param resultFlag 操作结果状态，0成功，1失败
     */
    public void setResultFlag(String resultFlag) {
        this.resultFlag = resultFlag == null ? null : resultFlag.trim();
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
     * 获取删除标记，0未删除，1删除
     *
     * @return delete_flag - 删除标记，0未删除，1删除
     */
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    /**
     * 设置删除标记，0未删除，1删除
     *
     * @param deleteFlag 删除标记，0未删除，1删除
     */
    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
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
     * 获取最后修改人
     *
     * @return last_modified_by - 最后修改人
     */
    public Integer getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * 设置最后修改人
     *
     * @param lastModifiedBy 最后修改人
     */
    public void setLastModifiedBy(Integer lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    /**
     * 获取最后修改时间
     *
     * @return last_modified_time - 最后修改时间
     */
    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param lastModifiedTime 最后修改时间
     */
    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}