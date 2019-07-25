package com.wisdom.iwcs.domain.elevator.dto;

import java.util.Date;
import javax.persistence.*;

public class ElevatorDTO {
    @Id
    @Column(name = "elevator_id")
    private Integer elevatorId;

    /**
     * 电梯编号
     */
    @Column(name = "elevator_code")
    private String elevatorCode;

    /**
     * 电梯名称
     */
    @Column(name = "elevator_name")
    private String elevatorName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 电梯状态: 正常、异常1，异常2等
     */
    @Column(name = "ele_status")
    private String eleStatus;

    /**
     * 状态更新时间
     */
    @Column(name = "status_update_time")
    private Date statusUpdateTime;

    /**
     * 通信ip
     */
    @Column(name = "ele_ip")
    private String eleIp;

    /**
     * 电梯任务状态：空闲0、有任务1
     */
    @Column(name = "ele_task_status")
    private String eleTaskStatus;

    /**
     * 当前业务号（业务主任务号）
     */
    @Column(name = "main_task_num")
    private String mainTaskNum;

    /**
     * 当前业务号梯控任务号
     */
    @Column(name = "ele_task_code")
    private String eleTaskCode;

    /**
     * 电梯任务状态变更时间
     */
    @Column(name = "ele_task_update_time")
    private Date eleTaskUpdateTime;

    /**
     * 电梯任务状态版本号
     */
    private Integer version;

    /**
     * 最后上报所在楼层
     */
    @Column(name = "current_floor")
    private Integer currentFloor;

    /**
     * 楼层上报时间
     */
    @Column(name = "floor_update_time")
    private Date floorUpdateTime;

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
     * 获取电梯编号
     *
     * @return elevator_code - 电梯编号
     */
    public String getElevatorCode() {
      return elevatorCode;
    }

    /**
     * 设置电梯编号
     *
     * @param elevatorCode 电梯编号
     */
    public void setElevatorCode(String elevatorCode) {
      this.elevatorCode = elevatorCode == null ? null : elevatorCode.trim();
    }

    /**
     * 获取电梯名称
     *
     * @return elevator_name - 电梯名称
     */
    public String getElevatorName() {
      return elevatorName;
    }

    /**
     * 设置电梯名称
     *
     * @param elevatorName 电梯名称
     */
    public void setElevatorName(String elevatorName) {
      this.elevatorName = elevatorName == null ? null : elevatorName.trim();
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
     * 获取电梯状态: 正常、异常1，异常2等
     *
     * @return ele_status - 电梯状态: 正常、异常1，异常2等
     */
    public String getEleStatus() {
      return eleStatus;
    }

    /**
     * 设置电梯状态: 正常、异常1，异常2等
     *
     * @param eleStatus 电梯状态: 正常、异常1，异常2等
     */
    public void setEleStatus(String eleStatus) {
      this.eleStatus = eleStatus == null ? null : eleStatus.trim();
    }

    /**
     * 获取状态更新时间
     *
     * @return status_update_time - 状态更新时间
     */
    public Date getStatusUpdateTime() {
      return statusUpdateTime;
    }

    /**
     * 设置状态更新时间
     *
     * @param statusUpdateTime 状态更新时间
     */
    public void setStatusUpdateTime(Date statusUpdateTime) {
      this.statusUpdateTime = statusUpdateTime;
    }

    /**
     * 获取通信ip
     *
     * @return ele_ip - 通信ip
     */
    public String getEleIp() {
      return eleIp;
    }

    /**
     * 设置通信ip
     *
     * @param eleIp 通信ip
     */
    public void setEleIp(String eleIp) {
      this.eleIp = eleIp == null ? null : eleIp.trim();
    }

    /**
     * 获取电梯任务状态：空闲0、有任务1
     *
     * @return ele_task_status - 电梯任务状态：空闲0、有任务1
     */
    public String getEleTaskStatus() {
      return eleTaskStatus;
    }

    /**
     * 设置电梯任务状态：空闲0、有任务1
     *
     * @param eleTaskStatus 电梯任务状态：空闲0、有任务1
     */
    public void setEleTaskStatus(String eleTaskStatus) {
      this.eleTaskStatus = eleTaskStatus == null ? null : eleTaskStatus.trim();
    }

    /**
     * 获取当前业务号（业务主任务号）
     *
     * @return main_task_num - 当前业务号（业务主任务号）
     */
    public String getMainTaskNum() {
      return mainTaskNum;
    }

    /**
     * 设置当前业务号（业务主任务号）
     *
     * @param mainTaskNum 当前业务号（业务主任务号）
     */
    public void setMainTaskNum(String mainTaskNum) {
      this.mainTaskNum = mainTaskNum == null ? null : mainTaskNum.trim();
    }

    /**
     * 获取当前业务号梯控任务号
     *
     * @return ele_task_code - 当前业务号梯控任务号
     */
    public String getEleTaskCode() {
      return eleTaskCode;
    }

    /**
     * 设置当前业务号梯控任务号
     *
     * @param eleTaskCode 当前业务号梯控任务号
     */
    public void setEleTaskCode(String eleTaskCode) {
      this.eleTaskCode = eleTaskCode == null ? null : eleTaskCode.trim();
    }

    /**
     * 获取电梯任务状态变更时间
     *
     * @return ele_task_update_time - 电梯任务状态变更时间
     */
    public Date getEleTaskUpdateTime() {
      return eleTaskUpdateTime;
    }

    /**
     * 设置电梯任务状态变更时间
     *
     * @param eleTaskUpdateTime 电梯任务状态变更时间
     */
    public void setEleTaskUpdateTime(Date eleTaskUpdateTime) {
      this.eleTaskUpdateTime = eleTaskUpdateTime;
    }

    /**
     * 获取电梯任务状态版本号
     *
     * @return version - 电梯任务状态版本号
     */
    public Integer getVersion() {
      return version;
    }

    /**
     * 设置电梯任务状态版本号
     *
     * @param version 电梯任务状态版本号
     */
    public void setVersion(Integer version) {
      this.version = version;
    }

    /**
     * 获取最后上报所在楼层
     *
     * @return current_floor - 最后上报所在楼层
     */
    public Integer getCurrentFloor() {
      return currentFloor;
    }

    /**
     * 设置最后上报所在楼层
     *
     * @param currentFloor 最后上报所在楼层
     */
    public void setCurrentFloor(Integer currentFloor) {
      this.currentFloor = currentFloor;
    }

    /**
     * 获取楼层上报时间
     *
     * @return floor_update_time - 楼层上报时间
     */
    public Date getFloorUpdateTime() {
      return floorUpdateTime;
    }

    /**
     * 设置楼层上报时间
     *
     * @param floorUpdateTime 楼层上报时间
     */
    public void setFloorUpdateTime(Date floorUpdateTime) {
    this.floorUpdateTime = floorUpdateTime;
  }
}
