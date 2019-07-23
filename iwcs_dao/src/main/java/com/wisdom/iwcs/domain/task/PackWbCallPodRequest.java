package com.wisdom.iwcs.domain.task;

/**
 * 一楼包装线体区呼叫满货货架
 * @Author george
 * @Date 2019/7/22 19:02
 */
public class PackWbCallPodRequest {
    /**
     * 任务编号
     */
    private String taskTypeCode;

    /**
     * 任务优先级
     */
    private Integer priority;

    /**
     * 库区
     */
    private String areaCode;

    /**
     * 货架号
     */
    private String podCode;

    /**
     * 起点
     */
    private String startPoint;

    /**
     * 目标点
     */
    private String targetPoint;

    public String getTaskTypeCode() {
        return taskTypeCode;
    }

    public void setTaskTypeCode(String taskTypeCode) {
        this.taskTypeCode = taskTypeCode;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
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
}
