package com.wisdom.iwcs.domain.task;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sub_condition_event_log")
public class SubConditionEventLog {
    @Id
    private Integer id;

    /**
     * 子任务单号
     */
    @Column(name = "sub_task_num")
    private String subTaskNum;

    /**
     * routeKey中文名
     */
    @Column(name = "route_key_name")
    private String routeKeyName;

    /**
     * routeKey代码
     */
    @Column(name = "route_key_code")
    private String routeKeyCode;

    /**
     * routeKey
     */
    @Column(name = "route_key")
    private String routeKey;

    /**
     * 事件下发状态
     */
    @Column(name = "event_status")
    private String eventStatus;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private Date createdTime;

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
     * 获取子任务单号
     *
     * @return sub_task_num - 子任务单号
     */
    public String getSubTaskNum() {
        return subTaskNum;
    }

    /**
     * 设置子任务单号
     *
     * @param subTaskNum 子任务单号
     */
    public void setSubTaskNum(String subTaskNum) {
        this.subTaskNum = subTaskNum == null ? null : subTaskNum.trim();
    }

    /**
     * 获取routeKey中文名
     *
     * @return route_key_name - routeKey中文名
     */
    public String getRouteKeyName() {
        return routeKeyName;
    }

    /**
     * 设置routeKey中文名
     *
     * @param routeKeyName routeKey中文名
     */
    public void setRouteKeyName(String routeKeyName) {
        this.routeKeyName = routeKeyName == null ? null : routeKeyName.trim();
    }

    /**
     * 获取routeKey代码
     *
     * @return route_key_code - routeKey代码
     */
    public String getRouteKeyCode() {
        return routeKeyCode;
    }

    /**
     * 设置routeKey代码
     *
     * @param routeKeyCode routeKey代码
     */
    public void setRouteKeyCode(String routeKeyCode) {
        this.routeKeyCode = routeKeyCode == null ? null : routeKeyCode.trim();
    }

    /**
     * 获取routeKey
     *
     * @return route_key - routeKey
     */
    public String getRouteKey() {
        return routeKey;
    }

    /**
     * 设置routeKey
     *
     * @param routeKey routeKey
     */
    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey == null ? null : routeKey.trim();
    }

    /**
     * 获取事件下发状态
     *
     * @return event_status - 事件下发状态
     */
    public String getEventStatus() {
        return eventStatus;
    }

    /**
     * 设置事件下发状态
     *
     * @param eventStatus 事件下发状态
     */
    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus == null ? null : eventStatus.trim();
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
}