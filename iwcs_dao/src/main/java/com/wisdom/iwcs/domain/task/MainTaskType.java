package com.wisdom.iwcs.domain.task;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ts_main_task_type")
public class MainTaskType {
    @Id
    private Long id;

    /**
     * 主任务类型编号
     */
    @Column(name = "main_task_type_code")
    private String mainTaskTypeCode;

    /**
     * 主任务类型名称
     */
    @Column(name = "main_task_type_name")
    private String mainTaskTypeName;

    /**
     * 执行类
     */
    @Column(name = "exec_bean")
    private String execBean;

    /**
     * 任务优先级
     */
    private Integer priority;

    /**
     * 循环执行
     */
    @Column(name = "loop_exec")
    private String loopExec;

    /**
     * 循环间隔时间
     */
    @Column(name = "interval_time")
    private Integer intervalTime;

    /**
     * 目标物是否多任务
     */
    @Column(name = "multi_target_task")
    private String multiTargetTask;

    /**
     * 关联附任务模板
     */
    @Column(name = "rel_vice_task_typ")
    private String relViceTaskTyp;

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
     * 是否是系统默认模板
     */
    @Column(name = "is_default")
    private Integer isDefault;

    /**
     * 分解任务个数
     */
    @Column(name = "decomp_num")
    private Integer decompNum;

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
     * 获取主任务类型编号
     *
     * @return main_task_type_code - 主任务类型编号
     */
    public String getMainTaskTypeCode() {
        return mainTaskTypeCode;
    }

    /**
     * 设置主任务类型编号
     *
     * @param mainTaskTypeCode 主任务类型编号
     */
    public void setMainTaskTypeCode(String mainTaskTypeCode) {
        this.mainTaskTypeCode = mainTaskTypeCode == null ? null : mainTaskTypeCode.trim();
    }

    /**
     * 获取主任务类型名称
     *
     * @return main_task_type_name - 主任务类型名称
     */
    public String getMainTaskTypeName() {
        return mainTaskTypeName;
    }

    /**
     * 设置主任务类型名称
     *
     * @param mainTaskTypeName 主任务类型名称
     */
    public void setMainTaskTypeName(String mainTaskTypeName) {
        this.mainTaskTypeName = mainTaskTypeName == null ? null : mainTaskTypeName.trim();
    }

    /**
     * 获取执行类
     *
     * @return exec_bean - 执行类
     */
    public String getExecBean() {
        return execBean;
    }

    /**
     * 设置执行类
     *
     * @param execBean 执行类
     */
    public void setExecBean(String execBean) {
        this.execBean = execBean == null ? null : execBean.trim();
    }

    /**
     * 获取任务优先级
     *
     * @return priority - 任务优先级
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置任务优先级
     *
     * @param priority 任务优先级
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * 获取循环执行
     *
     * @return loop_exec - 循环执行
     */
    public String getLoopExec() {
        return loopExec;
    }

    /**
     * 设置循环执行
     *
     * @param loopExec 循环执行
     */
    public void setLoopExec(String loopExec) {
        this.loopExec = loopExec == null ? null : loopExec.trim();
    }

    /**
     * 获取循环间隔时间
     *
     * @return interval_time - 循环间隔时间
     */
    public Integer getIntervalTime() {
        return intervalTime;
    }

    /**
     * 设置循环间隔时间
     *
     * @param intervalTime 循环间隔时间
     */
    public void setIntervalTime(Integer intervalTime) {
        this.intervalTime = intervalTime;
    }

    /**
     * 获取目标物是否多任务
     *
     * @return multi_target_task - 目标物是否多任务
     */
    public String getMultiTargetTask() {
        return multiTargetTask;
    }

    /**
     * 设置目标物是否多任务
     *
     * @param multiTargetTask 目标物是否多任务
     */
    public void setMultiTargetTask(String multiTargetTask) {
        this.multiTargetTask = multiTargetTask == null ? null : multiTargetTask.trim();
    }

    /**
     * 获取关联附任务模板
     *
     * @return rel_vice_task_typ - 关联附任务模板
     */
    public String getRelViceTaskTyp() {
        return relViceTaskTyp;
    }

    /**
     * 设置关联附任务模板
     *
     * @param relViceTaskTyp 关联附任务模板
     */
    public void setRelViceTaskTyp(String relViceTaskTyp) {
        this.relViceTaskTyp = relViceTaskTyp == null ? null : relViceTaskTyp.trim();
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
     * 获取是否是系统默认模板
     *
     * @return is_default - 是否是系统默认模板
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * 设置是否是系统默认模板
     *
     * @param isDefault 是否是系统默认模板
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 获取分解任务个数
     *
     * @return decomp_num - 分解任务个数
     */
    public Integer getDecompNum() {
        return decompNum;
    }

    /**
     * 设置分解任务个数
     *
     * @param decompNum 分解任务个数
     */
    public void setDecompNum(Integer decompNum) {
        this.decompNum = decompNum;
    }
}