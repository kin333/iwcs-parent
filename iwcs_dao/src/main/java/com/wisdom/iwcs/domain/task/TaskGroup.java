package com.wisdom.iwcs.domain.task;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ts_task_group")
public class TaskGroup {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 任务组编号
     */
    @Column(name = "task_group_code")
    private String taskGroupCode;

    /**
     * 任务组名称
     */
    @Column(name = "task_group_name")
    private String taskGroupName;

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
     * 任务组类型
     */
    @Column(name = "task_group_type")
    private String taskGroupType;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取任务组编号
     *
     * @return task_group_code - 任务组编号
     */
    public String getTaskGroupCode() {
        return taskGroupCode;
    }

    /**
     * 设置任务组编号
     *
     * @param taskGroupCode 任务组编号
     */
    public void setTaskGroupCode(String taskGroupCode) {
        this.taskGroupCode = taskGroupCode == null ? null : taskGroupCode.trim();
    }

    /**
     * 获取任务组名称
     *
     * @return task_group_name - 任务组名称
     */
    public String getTaskGroupName() {
        return taskGroupName;
    }

    /**
     * 设置任务组名称
     *
     * @param taskGroupName 任务组名称
     */
    public void setTaskGroupName(String taskGroupName) {
        this.taskGroupName = taskGroupName == null ? null : taskGroupName.trim();
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
     * 获取任务组类型
     *
     * @return task_group_type - 任务组类型
     */
    public String getTaskGroupType() {
        return taskGroupType;
    }

    /**
     * 设置任务组类型
     *
     * @param taskGroupType 任务组类型
     */
    public void setTaskGroupType(String taskGroupType) {
        this.taskGroupType = taskGroupType == null ? null : taskGroupType.trim();
    }
}