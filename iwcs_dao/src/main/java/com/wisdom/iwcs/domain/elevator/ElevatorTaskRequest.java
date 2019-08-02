package com.wisdom.iwcs.domain.elevator;

/**
 * 电梯缓存区到一楼包装线体缓存区
 * @Author george
 * @Date 2019/7/22 16:50
 */
public class ElevatorTaskRequest {
    /**
     * 货架号
     */
    private String podCode;

    /**
     * 任务优先级
     */
    private Integer priority;

    /**
     * 任务编号
     */
    private String taskTypeCode;

    /**
     * 起始点
     */
    private String startPoint;

    /**
     * 目标楼层储位
     */
    private String targetPoint;

    /**
     * 跨楼层任务 起始楼层
     */
    private String sourceFloor;

    /**
     * 跨楼层任务 目标楼层
     */
    private String destFloor;

    /**
     * 电梯工作状态
     */
    private String eleWorkType;

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTaskTypeCode() {
        return taskTypeCode;
    }

    public void setTaskTypeCode(String taskTypeCode) {
        this.taskTypeCode = taskTypeCode;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(String targetPoint) {
        this.targetPoint = targetPoint;
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

    public String getEleWorkType() {
        return eleWorkType;
    }

    public void setEleWorkType(String eleWorkType) {
        this.eleWorkType = eleWorkType;
    }
}
