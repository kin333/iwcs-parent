package com.wisdom.iwcs.domain.task;

/**
 * 产线去老化区搬运
 * @Author george
 * @Date 2019/7/3 19:52 
 */
public class PlToAgingRequest {

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
