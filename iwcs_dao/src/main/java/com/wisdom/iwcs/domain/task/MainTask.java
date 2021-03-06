package com.wisdom.iwcs.domain.task;

import org.springframework.stereotype.Component;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ts_main_task")
public class MainTask {
    @Id
    private Long id;

    /**
     * 主任务编号
     */
    @Column(name = "main_task_num")
    private String mainTaskNum;

    /**
     * 任务类型
     */
    @Column(name = "main_task_type_code")
    private String mainTaskTypeCode;

    /**
     * 任务状态
     */
    @Column(name = "task_status")
    private String taskStatus;

    /**
     * 状态变更时间
     */
    @Column(name = "date_chg")
    private Date dateChg;

    /**
     * 主任务顺序
     */
    @Column(name = "main_task_seq")
    private Integer mainTaskSeq;

    /**
     * 组编号
     */
    @Column(name = "group_id")
    private String groupId;

    /**
     * 执行顺序
     */
    private Integer sequence;

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
     * 优先级
     */
    private Integer priority;

    /**
     * 任务组编号
     */
    @Column(name = "task_group_code")
    private String taskGroupCode;

    /**
     * 执行者编号、设备号、系统名称
     */
    @Column(name = "exector_num")
    private String exectorNum;

    /**
     * 库区编号
     */
    @Column(name = "area_code")
    private String areaCode;

    /**
     * 电梯任务，开始楼层
     */
    @Column(name = "source_floor")
    private String sourceFloor;

    /**
     * 电梯任务，结束楼层"
     */
    @Column(name = "dest_floor")
    private String destFloor;

    /**
     * 电梯任务作业类型up/down
     */
    @Column(name = "elevator_work_type")
    private String elevatorWorkType;
    /**
     * 静态站点集合
     */
    @Column(name = "static_via_paths")
    private String staticViaPaths;
    /**
     * 静态载具
     */
    @Column(name = "static_pod_code")
    private String staticPodCode;

    /**
     * 主任务节点
     */
    @Column(name = "biz_process")
    private String bizProcess;


    /**
     * 取消操作人
     */
    @Column(name = "cancel_operator")
    private Integer cancelOperator;

    @Column(name = "cancel_time")
    private Date cancelTime;

    @Column(name = "cancel_remark")
    private String cancelRemark;
    /**
     * 现场恢复状态,-1：无需恢复，默认值；0：待恢复；1：已恢复
     */
    @Column(name = "cancel_scene_recovery_status")
    private String cancelSceneRecoveryStatus;
    /**
     * 恢复操作编号
     */
    @Column(name = "scene_recovery_num")
    private String sceneRecoveryNum;

    public String getBizProcess() {
        return bizProcess;
    }

    public void setBizProcess(String bizProcess) {
        this.bizProcess = bizProcess;
    }

    public String getStaticViaPaths() {
        return staticViaPaths;
    }

    public void setStaticViaPaths(String staticViaPaths) {
        this.staticViaPaths = staticViaPaths;
    }

    public String getStaticPodCode() {
        return staticPodCode;
    }

    public void setStaticPodCode(String staticPodCode) {
        this.staticPodCode = staticPodCode;
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
     * 获取主任务编号
     *
     * @return main_task_num - 主任务编号
     */
    public String getMainTaskNum() {
        return mainTaskNum;
    }

    /**
     * 设置主任务编号
     *
     * @param mainTaskNum 主任务编号
     */
    public void setMainTaskNum(String mainTaskNum) {
        this.mainTaskNum = mainTaskNum == null ? null : mainTaskNum.trim();
    }

    /**
     * 获取任务类型
     *
     * @return main_task_type_code - 任务类型
     */
    public String getMainTaskTypeCode() {
        return mainTaskTypeCode;
    }

    /**
     * 设置任务类型
     *
     * @param mainTaskTypeCode 任务类型
     */
    public void setMainTaskTypeCode(String mainTaskTypeCode) {
        this.mainTaskTypeCode = mainTaskTypeCode == null ? null : mainTaskTypeCode.trim();
    }

    /**
     * 获取任务状态
     *
     * @return task_status - 任务状态
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置任务状态
     *
     * @param taskStatus 任务状态
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    /**
     * 获取状态变更时间
     *
     * @return date_chg - 状态变更时间
     */
    public Date getDateChg() {
        return dateChg;
    }

    /**
     * 设置状态变更时间
     *
     * @param dateChg 状态变更时间
     */
    public void setDateChg(Date dateChg) {
        this.dateChg = dateChg;
    }

    /**
     * 获取主任务顺序
     *
     * @return main_task_seq - 主任务顺序
     */
    public Integer getMainTaskSeq() {
        return mainTaskSeq;
    }

    /**
     * 设置主任务顺序
     *
     * @param mainTaskSeq 主任务顺序
     */
    public void setMainTaskSeq(Integer mainTaskSeq) {
        this.mainTaskSeq = mainTaskSeq;
    }

    /**
     * 获取组编号
     *
     * @return group_id - 组编号
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置组编号
     *
     * @param groupId 组编号
     */
    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }

    /**
     * 获取执行顺序
     *
     * @return sequence - 执行顺序
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置执行顺序
     *
     * @param sequence 执行顺序
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
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
     * 获取优先级
     *
     * @return priority - 优先级
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * 设置优先级
     *
     * @param priority 优先级
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
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
     * 获取执行者编号、设备号、系统名称
     *
     * @return exector_num - 执行者编号、设备号、系统名称
     */
    public String getExectorNum() {
        return exectorNum;
    }

    /**
     * 设置执行者编号、设备号、系统名称
     *
     * @param exectorNum 执行者编号、设备号、系统名称
     */
    public void setExectorNum(String exectorNum) {
        this.exectorNum = exectorNum == null ? null : exectorNum.trim();
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getSourceFloor() {
        return sourceFloor;
    }

    public void setSourceFloor(String sourceFloor) {
        this.sourceFloor = sourceFloor;
    }

    public String getDestFloor() {
        return destFloor;
    }

    public void setDestFloor(String destFloor) {
        this.destFloor = destFloor;
    }

    public String getElevatorWorkType() {
        return elevatorWorkType;
    }

    public void setElevatorWorkType(String elevatorWorkType) {
        this.elevatorWorkType = elevatorWorkType;
    }

    public Integer getCancelOperator() {
        return cancelOperator;
    }

    public void setCancelOperator(Integer cancelOperator) {
        this.cancelOperator = cancelOperator;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getCancelRemark() {
        return cancelRemark;
    }

    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
    }

    public String getCancelSceneRecoveryStatus() {
        return cancelSceneRecoveryStatus;
    }

    public void setCancelSceneRecoveryStatus(String cancelSceneRecoveryStatus) {
        this.cancelSceneRecoveryStatus = cancelSceneRecoveryStatus;
    }

    public String getSceneRecoveryNum() {
        return sceneRecoveryNum;
    }

    public void setSceneRecoveryNum(String sceneRecoveryNum) {
        this.sceneRecoveryNum = sceneRecoveryNum;
    }
}