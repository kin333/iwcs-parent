package com.wisdom.iwcs.domain.task;

/**
 * 自动老化区前往检验点
 * @Author george
 * @Date 2019/7/3 21:49 
 */
public class AgingToQuaInspRequest {
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
}
