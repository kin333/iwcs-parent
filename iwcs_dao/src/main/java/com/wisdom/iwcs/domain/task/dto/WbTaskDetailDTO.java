package com.wisdom.iwcs.domain.task.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "wb_task_detail")
public class WbTaskDetailDTO {
    @Id
    private Integer id;

    /**
     * 货架号
     */
    @Column(name = "wb_task_no")
    private String wbTaskNo;

    /**
     * 货架号
     */
    @Column(name = "pod_code")
    private String podCode;

    /**
     * 托盘号，该货架任意一个bincode，冗余用
     */
    @Column(name = "bin_code")
    private String binCode;

    /**
     * 任务状态，0已创建，9已结束
     */
    @Column(name = "task_status")
    private String taskStatus;

    /**
     * 任务code,和海康交互使用，海康叫task_code
     */
    @Column(name = "task_seq")
    private String taskSeq;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 任务完成类型:NORMALBATCH_CANCLED
     */
    @Column(name = "task_finished_type")
    private String taskFinishedType;

    /**
     * AGV旋转属性，支持单个的设置行为
     */
    @Column(name = "agv_action_rotate_type")
    private String agvActionRotateType;

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
     * 任务类型：出库＼入库＼盘点＼理货（冗余主表属性）
     */
    @Column(name = "task_type")
    private String taskType;

    /**
     * 工作台点位代码
     */
    private String wbCode;

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

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
     * 获取货架号
     *
     * @return wb_task_no - 货架号
     */
    public String getWbTaskNo() {
        return wbTaskNo;
    }

    /**
     * 设置货架号
     *
     * @param wbTaskNo 货架号
     */
    public void setWbTaskNo(String wbTaskNo) {
        this.wbTaskNo = wbTaskNo == null ? null : wbTaskNo.trim();
    }

    /**
     * 获取货架号
     *
     * @return pod_code - 货架号
     */
    public String getPodCode() {
        return podCode;
    }

    /**
     * 设置货架号
     *
     * @param podCode 货架号
     */
    public void setPodCode(String podCode) {
        this.podCode = podCode == null ? null : podCode.trim();
    }

    /**
     * 获取托盘号，该货架任意一个bincode，冗余用
     *
     * @return bin_code - 托盘号，该货架任意一个bincode，冗余用
     */
    public String getBinCode() {
        return binCode;
    }

    /**
     * 设置托盘号，该货架任意一个bincode，冗余用
     *
     * @param binCode 托盘号，该货架任意一个bincode，冗余用
     */
    public void setBinCode(String binCode) {
        this.binCode = binCode == null ? null : binCode.trim();
    }

    /**
     * 获取任务状态，0已创建，9已结束
     *
     * @return task_status - 任务状态，0已创建，9已结束
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置任务状态，0已创建，9已结束
     *
     * @param taskStatus 任务状态，0已创建，9已结束
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    /**
     * 获取任务code,和海康交互使用，海康叫task_code
     *
     * @return task_seq - 任务code,和海康交互使用，海康叫task_code
     */
    public String getTaskSeq() {
        return taskSeq;
    }

    /**
     * 设置任务code,和海康交互使用，海康叫task_code
     *
     * @param taskSeq 任务code,和海康交互使用，海康叫task_code
     */
    public void setTaskSeq(String taskSeq) {
        this.taskSeq = taskSeq == null ? null : taskSeq.trim();
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取任务完成类型:NORMALBATCH_CANCLED
     *
     * @return task_finished_type - 任务完成类型:NORMALBATCH_CANCLED
     */
    public String getTaskFinishedType() {
        return taskFinishedType;
    }

    /**
     * 设置任务完成类型:NORMALBATCH_CANCLED
     *
     * @param taskFinishedType 任务完成类型:NORMALBATCH_CANCLED
     */
    public void setTaskFinishedType(String taskFinishedType) {
        this.taskFinishedType = taskFinishedType == null ? null : taskFinishedType.trim();
    }

    /**
     * 获取AGV旋转属性，支持单个的设置行为
     *
     * @return agv_action_rotate_type - AGV旋转属性，支持单个的设置行为
     */
    public String getAgvActionRotateType() {
        return agvActionRotateType;
    }

    /**
     * 设置AGV旋转属性，支持单个的设置行为
     *
     * @param agvActionRotateType AGV旋转属性，支持单个的设置行为
     */
    public void setAgvActionRotateType(String agvActionRotateType) {
        this.agvActionRotateType = agvActionRotateType == null ? null : agvActionRotateType.trim();
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