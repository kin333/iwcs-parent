package com.wisdom.iwcs.domain.elevator.dto;

import java.util.Date;
import javax.persistence.*;


@Table(name = "ele_control_task")
public class EleControlTaskDTO {
    @Id
    private Integer id;

    /**
     * 电梯任务编号
     */
    @Column(name = "ele_task_code")
    private String eleTaskCode;

    /**
     * 业务请求编号（主任务编号）
     */
    @Column(name = "main_task_num")
    private String mainTaskNum;

    /**
     * 电梯任务作业类型up/down
     */
    @Column(name = "elevator_work_type")
    private String elevatorWorkType;

    /**
     * 电梯任务，开始楼层
     */
    @Column(name = "source_floor")
    private Integer sourceFloor;

    /**
     * 电梯任务，结束楼层
     */
    @Column(name = "dest_floor")
    private Integer destFloor;

    /**
     * 任务状态：创建0、起始接驳1、电梯运行2、目标接驳3 、结束9
     */
    @Column(name = "task_status")
    private String taskStatus;

    /**
     * 是否已呼叫电梯到达起始楼层：否0,是1
     */
    @Column(name = "call_ele_arr_floor")
    private String callEleArrFloor;

    /**
     * 起点业务通知已就绪
     */
    @Column(name = "source_biz_notify")
    private String sourceBizNotify;

    /**
     * 已通知电梯任务起始楼层就绪并检验是否可进
     */
    @Column(name = "wcs_notify_entry_source")
    private String wcsNotifyEntrySource;

    /**
     * 已通知电梯任务终点楼层就绪并检验是否可进
     */
    @Column(name = "wcs_notify_entry_dest")
    private String wcsNotifyEntryDest;

    /**
     * 电梯通知起始楼层可进
     */
    @Column(name = "plc_notify_entry_source")
    private String plcNotifyEntrySource;

    /**
     * 电梯通知目标楼层可进
     */
    @Column(name = "plc_notify_entry_dest")
    private String plcNotifyEntryDest;

    /**
     * AGV已离开起点轿厢
     */
    @Column(name = "agv_leave_source")
    private String agvLeaveSource;

    /**
     * AGV已离开终点轿厢
     */
    @Column(name = "agv_leave_dest")
    private String agvLeaveDest;

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
     * 获取电梯任务编号
     *
     * @return ele_task_code - 电梯任务编号
     */
    public String getEleTaskCode() {
      return eleTaskCode;
    }

    /**
     * 设置电梯任务编号
     *
     * @param eleTaskCode 电梯任务编号
     */
    public void setEleTaskCode(String eleTaskCode) {
      this.eleTaskCode = eleTaskCode == null ? null : eleTaskCode.trim();
    }

    /**
     * 获取业务请求编号（主任务编号）
     *
     * @return main_task_num - 业务请求编号（主任务编号）
     */
    public String getMainTaskNum() {
      return mainTaskNum;
    }

    /**
     * 设置业务请求编号（主任务编号）
     *
     * @param mainTaskNum 业务请求编号（主任务编号）
     */
    public void setMainTaskNum(String mainTaskNum) {
      this.mainTaskNum = mainTaskNum == null ? null : mainTaskNum.trim();
    }

    /**
     * 获取电梯任务作业类型up/down
     *
     * @return elevator_work_type - 电梯任务作业类型up/down
     */
    public String getElevatorWorkType() {
      return elevatorWorkType;
    }

    /**
     * 设置电梯任务作业类型up/down
     *
     * @param elevatorWorkType 电梯任务作业类型up/down
     */
    public void setElevatorWorkType(String elevatorWorkType) {
      this.elevatorWorkType = elevatorWorkType == null ? null : elevatorWorkType.trim();
    }

    /**
     * 获取电梯任务，开始楼层
     *
     * @return source_floor - 电梯任务，开始楼层
     */
    public Integer getSourceFloor() {
      return sourceFloor;
    }

    /**
     * 设置电梯任务，开始楼层
     *
     * @param sourceFloor 电梯任务，开始楼层
     */
    public void setSourceFloor(Integer sourceFloor) {
      this.sourceFloor = sourceFloor;
    }

    /**
     * 获取电梯任务，结束楼层
     *
     * @return dest_floor - 电梯任务，结束楼层
     */
    public Integer getDestFloor() {
      return destFloor;
    }

    /**
     * 设置电梯任务，结束楼层
     *
     * @param destFloor 电梯任务，结束楼层
     */
    public void setDestFloor(Integer destFloor) {
      this.destFloor = destFloor;
    }

    /**
     * 获取任务状态：创建0、起始接驳1、电梯运行2、目标接驳3 、结束9
     *
     * @return task_status - 任务状态：创建0、起始接驳1、电梯运行2、目标接驳3 、结束9
     */
    public String getTaskStatus() {
      return taskStatus;
    }

    /**
     * 设置任务状态：创建0、起始接驳1、电梯运行2、目标接驳3 、结束9
     *
     * @param taskStatus 任务状态：创建0、起始接驳1、电梯运行2、目标接驳3 、结束9
     */
    public void setTaskStatus(String taskStatus) {
      this.taskStatus = taskStatus == null ? null : taskStatus.trim();
    }

    /**
     * 获取是否已呼叫电梯到达起始楼层：否0,是1
     *
     * @return call_ele_arr_floor - 是否已呼叫电梯到达起始楼层：否0,是1
     */
    public String getCallEleArrFloor() {
      return callEleArrFloor;
    }

    /**
     * 设置是否已呼叫电梯到达起始楼层：否0,是1
     *
     * @param callEleArrFloor 是否已呼叫电梯到达起始楼层：否0,是1
     */
    public void setCallEleArrFloor(String callEleArrFloor) {
      this.callEleArrFloor = callEleArrFloor == null ? null : callEleArrFloor.trim();
    }

    /**
     * 获取起点业务通知已就绪
     *
     * @return source_biz_notify - 起点业务通知已就绪
     */
    public String getSourceBizNotify() {
      return sourceBizNotify;
    }

    /**
     * 设置起点业务通知已就绪
     *
     * @param sourceBizNotify 起点业务通知已就绪
     */
    public void setSourceBizNotify(String sourceBizNotify) {
      this.sourceBizNotify = sourceBizNotify == null ? null : sourceBizNotify.trim();
    }

    /**
     * 获取已通知电梯任务起始楼层就绪并检验是否可进
     *
     * @return wcs_notify_entry_source - 已通知电梯任务起始楼层就绪并检验是否可进
     */
    public String getWcsNotifyEntrySource() {
      return wcsNotifyEntrySource;
    }

    /**
     * 设置已通知电梯任务起始楼层就绪并检验是否可进
     *
     * @param wcsNotifyEntrySource 已通知电梯任务起始楼层就绪并检验是否可进
     */
    public void setWcsNotifyEntrySource(String wcsNotifyEntrySource) {
      this.wcsNotifyEntrySource = wcsNotifyEntrySource == null ? null : wcsNotifyEntrySource.trim();
    }

    /**
     * 获取已通知电梯任务终点楼层就绪并检验是否可进
     *
     * @return wcs_notify_entry_dest - 已通知电梯任务终点楼层就绪并检验是否可进
     */
    public String getWcsNotifyEntryDest() {
      return wcsNotifyEntryDest;
    }

    /**
     * 设置已通知电梯任务终点楼层就绪并检验是否可进
     *
     * @param wcsNotifyEntryDest 已通知电梯任务终点楼层就绪并检验是否可进
     */
    public void setWcsNotifyEntryDest(String wcsNotifyEntryDest) {
      this.wcsNotifyEntryDest = wcsNotifyEntryDest == null ? null : wcsNotifyEntryDest.trim();
    }

    /**
     * 获取电梯通知起始楼层可进
     *
     * @return plc_notify_entry_source - 电梯通知起始楼层可进
     */
    public String getPlcNotifyEntrySource() {
      return plcNotifyEntrySource;
    }

    /**
     * 设置电梯通知起始楼层可进
     *
     * @param plcNotifyEntrySource 电梯通知起始楼层可进
     */
    public void setPlcNotifyEntrySource(String plcNotifyEntrySource) {
      this.plcNotifyEntrySource = plcNotifyEntrySource == null ? null : plcNotifyEntrySource.trim();
    }

    /**
     * 获取电梯通知目标楼层可进
     *
     * @return plc_notify_entry_dest - 电梯通知目标楼层可进
     */
    public String getPlcNotifyEntryDest() {
      return plcNotifyEntryDest;
    }

    /**
     * 设置电梯通知目标楼层可进
     *
     * @param plcNotifyEntryDest 电梯通知目标楼层可进
     */
    public void setPlcNotifyEntryDest(String plcNotifyEntryDest) {
      this.plcNotifyEntryDest = plcNotifyEntryDest == null ? null : plcNotifyEntryDest.trim();
    }

    /**
     * 获取AGV已离开起点轿厢
     *
     * @return agv_leave_source - AGV已离开起点轿厢
     */
    public String getAgvLeaveSource() {
      return agvLeaveSource;
    }

    /**
     * 设置AGV已离开起点轿厢
     *
     * @param agvLeaveSource AGV已离开起点轿厢
     */
    public void setAgvLeaveSource(String agvLeaveSource) {
      this.agvLeaveSource = agvLeaveSource == null ? null : agvLeaveSource.trim();
    }

    /**
     * 获取AGV已离开终点轿厢
     *
     * @return agv_leave_dest - AGV已离开终点轿厢
     */
    public String getAgvLeaveDest() {
      return agvLeaveDest;
    }

    /**
     * 设置AGV已离开终点轿厢
     *
     * @param agvLeaveDest AGV已离开终点轿厢
     */
    public void setAgvLeaveDest(String agvLeaveDest) {
      this.agvLeaveDest = agvLeaveDest == null ? null : agvLeaveDest.trim();
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
