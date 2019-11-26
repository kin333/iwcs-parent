package com.wisdom.iwcs.domain.door.dto;

import java.util.Date;
import javax.persistence.*;

@Table(name = "auto_door_task")
public class AutoDoorTaskDTO {
    @Id
    private Integer id;

    /**
     * 门编号
     */
    @Column(name = "door_code")
    private String doorCode;

    /**
     * 请求编号（agv上报的uuid）
     */
    @Column(name = "task_code")
    private String taskCode;

    /**
     * 请求时间戳
     */
    @Column(name = "req_time")
    private Date reqTime;

    /**
     * 任务状态：0进行中，9结束
     */
    @Column(name = "task_status")
    private String taskStatus;

    /**
     * 任务状态变更时间
     */
    @Column(name = "task_update_time")
    private Date taskUpdateTime;

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
     * 获取门编号
     *
     * @return door_code - 门编号
     */
    public String getDoorCode() {
        return doorCode;
    }

    /**
     * 设置门编号
     *
     * @param doorCode 门编号
     */
    public void setDoorCode(String doorCode) {
        this.doorCode = doorCode == null ? null : doorCode.trim();
    }

    /**
     * 获取请求编号（agv上报的uuid）
     *
     * @return task_code - 请求编号（agv上报的uuid）
     */
    public String getTaskCode() {
        return taskCode;
    }

    /**
     * 设置请求编号（agv上报的uuid）
     *
     * @param taskCode 请求编号（agv上报的uuid）
     */
    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode == null ? null : taskCode.trim();
    }

    /**
     * 获取请求时间戳
     *
     * @return req_time - 请求时间戳
     */
    public Date getReqTime() {
        return reqTime;
    }

    /**
     * 设置请求时间戳
     *
     * @param reqTime 请求时间戳
     */
    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    /**
     * 获取任务状态：0进行中，9结束
     *
     * @return task_status - 任务状态：0进行中，9结束
     */
    public String getTaskStatus() {
        return taskStatus;
    }

    /**
     * 设置任务状态：0进行中，9结束
     *
     * @param taskStatus 任务状态：0进行中，9结束
     */
    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    /**
     * 获取任务状态变更时间
     *
     * @return task_update_time - 任务状态变更时间
     */
    public Date getTaskUpdateTime() {
        return taskUpdateTime;
    }

    /**
     * 设置任务状态变更时间
     *
     * @param taskUpdateTime 任务状态变更时间
     */
    public void setTaskUpdateTime(Date taskUpdateTime) {
        this.taskUpdateTime = taskUpdateTime;
    }
}
