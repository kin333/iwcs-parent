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
     * 作业区域(如老化区、检验区)
     */
    private String operateAreaCode;

    //TODO 扩展

    public String getOperateAreaCode() {
        return operateAreaCode;
    }

    public void setOperateAreaCode(String operateAreaCode) {
        this.operateAreaCode = operateAreaCode;
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

    public String getTaskTypeCode() {
        return taskTypeCode;
    }

    public void setTaskTypeCode(String taskTypeCode) {
        this.taskTypeCode = taskTypeCode;
    }
}
