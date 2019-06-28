package com.wisdom.iwcs.domain.elevator;

import java.util.Date;
import javax.persistence.*;

public class Elevator {
    @Id
    @Column(name = "elevator_id")
    private Integer elevatorId;

    @Column(name = "elevator_code")
    private Integer elevatorCode;

    @Column(name = "elev_status")
    private String elevStatus;

    @Column(name = "status_update_time")
    private Date statusUpdateTime;

    /**
     * 	当前所在楼层
     */
    @Column(name = "current_floor")
    private Integer currentFloor;

    private String remark;

    @Column(name = "status_remark")
    private String statusRemark;

    /**
     * @return elevator_id
     */
    public Integer getElevatorId() {
        return elevatorId;
    }

    /**
     * @param elevatorId
     */
    public void setElevatorId(Integer elevatorId) {
        this.elevatorId = elevatorId;
    }

    /**
     * @return elevator_code
     */
    public Integer getElevatorCode() {
        return elevatorCode;
    }

    /**
     * @param elevatorCode
     */
    public void setElevatorCode(Integer elevatorCode) {
        this.elevatorCode = elevatorCode;
    }

    /**
     * @return elev_status
     */
    public String getElevStatus() {
        return elevStatus;
    }

    /**
     * @param elevStatus
     */
    public void setElevStatus(String elevStatus) {
        this.elevStatus = elevStatus == null ? null : elevStatus.trim();
    }

    /**
     * @return status_update_time
     */
    public Date getStatusUpdateTime() {
        return statusUpdateTime;
    }

    /**
     * @param statusUpdateTime
     */
    public void setStatusUpdateTime(Date statusUpdateTime) {
        this.statusUpdateTime = statusUpdateTime;
    }

    /**
     * 当前所在楼层
     *
     * @return current_floor - 	当前所在楼层
     */
    public Integer getCurrentFloor() {
        return currentFloor;
    }

    /**
     * 当前所在楼层
     *
     * @param currentFloor 	当前所在楼层
     */
    public void setCurrentFloor(Integer currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * @return status_remark
     */
    public String getStatusRemark() {
        return statusRemark;
    }

    /**
     * @param statusRemark
     */
    public void setStatusRemark(String statusRemark) {
        this.statusRemark = statusRemark == null ? null : statusRemark.trim();
    }
}
