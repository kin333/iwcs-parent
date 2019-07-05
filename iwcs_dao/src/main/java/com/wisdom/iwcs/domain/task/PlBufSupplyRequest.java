package com.wisdom.iwcs.domain.task;

/**
 * 补充产线空货架缓存区
 * @Author george
 * @Date 2019/7/3 17:15 
 */
public class PlBufSupplyRequest {

    /**
     * 任务编号
     */
    private String taskTypeCode;

    /**
     * 货架号
     */
    private String podCode;

    /**
     * 任务优先级
     */
    private Integer priority;

    /**
     * 目标点
     */
    private String targetPoint;

    /**
     * 库区
     */
    private String areaCode;

    //TODO 扩展

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

    public String getTargetPoint() {
        return targetPoint;
    }

    public void setTargetPoint(String targetPoint) {
        this.targetPoint = targetPoint;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }
}
