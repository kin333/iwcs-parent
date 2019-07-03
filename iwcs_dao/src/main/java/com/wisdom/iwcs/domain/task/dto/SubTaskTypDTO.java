package com.wisdom.iwcs.domain.task.dto;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ts_sub_task_typ")
public class SubTaskTypDTO {
    @Id
    private Long id;

    /**
     * 子任务类型编号
     */
    @Column(name = "sub_task_typ_code")
    private String subTaskTypCode;

    /**
     * 子任务类型名称
     */
    @Column(name = "sub_task_typ_name")
    private String subTaskTypName;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否是系统默认
     */
    @Column(name = "is_default")
    private Integer isDefault;

    /**
     * 任务类型标识
     */
    @Column(name = "task_typ")
    private String taskTyp;

    /**
     * 发送消息体
     */
    @Column(name = "sub_task_mes_send")
    private String subTaskMesSend;

    /**
     * 执行者类型
     */
    @Column(name = "worker_type")
    private String workerType;

    /**
     * 执行目标的url
     */
    @Column(name = "worker_url")
    private String workerUrl;

    public String getWorkerUrl() {
        return workerUrl;
    }

    public void setWorkerUrl(String workerUrl) {
        this.workerUrl = workerUrl;
    }

    /**
     * @return id
     */
    public Long getId() {
      return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
      this.id = id;
    }

    /**
     * 获取子任务类型编号
     *
     * @return sub_task_typ_code - 子任务类型编号
     */
    public String getSubTaskTypCode() {
      return subTaskTypCode;
    }

    /**
     * 设置子任务类型编号
     *
     * @param subTaskTypCode 子任务类型编号
     */
    public void setSubTaskTypCode(String subTaskTypCode) {
      this.subTaskTypCode = subTaskTypCode == null ? null : subTaskTypCode.trim();
    }

    /**
     * 获取子任务类型名称
     *
     * @return sub_task_typ_name - 子任务类型名称
     */
    public String getSubTaskTypName() {
      return subTaskTypName;
    }

    /**
     * 设置子任务类型名称
     *
     * @param subTaskTypName 子任务类型名称
     */
    public void setSubTaskTypName(String subTaskTypName) {
      this.subTaskTypName = subTaskTypName == null ? null : subTaskTypName.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
      return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
      this.createDate = createDate;
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
     * 获取是否是系统默认
     *
     * @return is_default - 是否是系统默认
     */
    public Integer getIsDefault() {
      return isDefault;
    }

    /**
     * 设置是否是系统默认
     *
     * @param isDefault 是否是系统默认
     */
    public void setIsDefault(Integer isDefault) {
      this.isDefault = isDefault;
    }

    /**
     * 获取任务类型标识
     *
     * @return task_typ - 任务类型标识
     */
    public String getTaskTyp() {
      return taskTyp;
    }

    /**
     * 设置任务类型标识
     *
     * @param taskTyp 任务类型标识
     */
    public void setTaskTyp(String taskTyp) {
      this.taskTyp = taskTyp == null ? null : taskTyp.trim();
    }

    /**
     * 获取发送消息体
     *
     * @return sub_task_mes_send - 发送消息体
     */
    public String getSubTaskMesSend() {
      return subTaskMesSend;
    }

    /**
     * 设置发送消息体
     *
     * @param subTaskMesSend 发送消息体
     */
    public void setSubTaskMesSend(String subTaskMesSend) {
      this.subTaskMesSend = subTaskMesSend == null ? null : subTaskMesSend.trim();
    }

    /**
     * 获取执行者类型
     *
     * @return worker_type - 执行者类型
     */
    public String getWorkerType() {
      return workerType;
    }

    /**
     * 设置执行者类型
     *
     * @param workerType 执行者类型
     */
    public void setWorkerType(String workerType) {
    this.workerType = workerType == null ? null : workerType.trim();
  }
}
