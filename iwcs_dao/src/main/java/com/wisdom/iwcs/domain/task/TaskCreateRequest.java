package com.wisdom.iwcs.domain.task;

/**
 * 任务创建请求
 * @Author george
 * @Date 2019/7/3 8:49
 */
public class TaskCreateRequest {

    /**
     * 任务类型编号 必填
     */
    private String taskTypeCode;

    /**
     * 作业区域(如老化区、检验区)
     */
    private String operateAreaCode;

    /**
     * 业务次级区域(如老化区下的自动区、手动区)
     */
    private String  bizSecondAreaCode;

    /**
     * 子任务单业务属性 老化区 自动区/手动区
     */
    private String subTaskBizProp;

    /**
     * 起始点编号
     */
    private String startPointAlias;

    /**
     * 目标点位编号
     */
    private String targetPointAlias;

    /**
     * 货架号
     */
    private String podCode;

    /**
     * 任务优先级 不填
     */
    private Integer priority;

    /**
     * 点到点细分任务类型
     */
    private String pTopTaskSubTaskType;

    /**
     * 库区
     */
    private String areaCode;

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

    public String getTaskTypeCode() {
        return taskTypeCode;
    }

    public void setTaskTypeCode(String taskTypeCode) {
        this.taskTypeCode = taskTypeCode;
    }

    public String getOperateAreaCode() {
        return operateAreaCode;
    }

    public void setOperateAreaCode(String operateAreaCode) {
        this.operateAreaCode = operateAreaCode;
    }

    public String getBizSecondAreaCode() {
        return bizSecondAreaCode;
    }

    public void setBizSecondAreaCode(String bizSecondAreaCode) {
        this.bizSecondAreaCode = bizSecondAreaCode;
    }

    public String getSubTaskBizProp() {
        return subTaskBizProp;
    }

    public void setSubTaskBizProp(String subTaskBizProp) {
        this.subTaskBizProp = subTaskBizProp;
    }

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

    public String getTargetPointAlias() {
        return targetPointAlias;
    }

    public void setTargetPointAlias(String targetPointAlias) {
        this.targetPointAlias = targetPointAlias;
    }

    public String getStartPointAlias() {
        return startPointAlias;
    }

    public void setStartPointAlias(String startPointAlias) {
        this.startPointAlias = startPointAlias;
    }

    public String getpTopTaskSubTaskType() {
        return pTopTaskSubTaskType;
    }

    public void setpTopTaskSubTaskType(String pTopTaskSubTaskType) {
        this.pTopTaskSubTaskType = pTopTaskSubTaskType;
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

    public String getEleWorkType() {
        return eleWorkType;
    }

    public void setEleWorkType(String eleWorkType) {
        this.eleWorkType = eleWorkType;
    }
}
