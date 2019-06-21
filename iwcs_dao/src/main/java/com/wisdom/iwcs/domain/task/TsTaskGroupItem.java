package com.wisdom.iwcs.domain.task;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ts_task_group_item")
public class TsTaskGroupItem {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 子任务类型编号
     */
    @Column(name = "sub_task_type_code")
    private String subTaskTypeCode;

    /**
     * 子任务执行顺序
     */
    @Column(name = "sub_task_seq")
    private Integer subTaskSeq;

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
     * 任务组编号
     */
    @Column(name = "task_group_code")
    private String taskGroupCode;

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
     * 获取子任务类型编号
     *
     * @return sub_task_type_code - 子任务类型编号
     */
    public String getSubTaskTypeCode() {
        return subTaskTypeCode;
    }

    /**
     * 设置子任务类型编号
     *
     * @param subTaskTypeCode 子任务类型编号
     */
    public void setSubTaskTypeCode(String subTaskTypeCode) {
        this.subTaskTypeCode = subTaskTypeCode == null ? null : subTaskTypeCode.trim();
    }

    /**
     * 获取子任务执行顺序
     *
     * @return sub_task_seq - 子任务执行顺序
     */
    public Integer getSubTaskSeq() {
        return subTaskSeq;
    }

    /**
     * 设置子任务执行顺序
     *
     * @param subTaskSeq 子任务执行顺序
     */
    public void setSubTaskSeq(Integer subTaskSeq) {
        this.subTaskSeq = subTaskSeq;
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
}