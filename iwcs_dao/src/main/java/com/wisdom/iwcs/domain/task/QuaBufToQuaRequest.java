package com.wisdom.iwcs.domain.task;

/**
 * 检验缓冲区去检验点
 * @Author george
 * @Date 2019/7/7 15:17 
 */
public class QuaBufToQuaRequest {

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
}
