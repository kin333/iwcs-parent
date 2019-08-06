package com.wisdom.iwcs.domain.task;

/**
 * 工作台点位呼叫空货架请求
 * @Author george
 * @Date 2019/7/3 10:22
 */
public class PlAutoWbCallPodRequest {

    /**
     * 任务编号
     */
    private String taskTypeCode;

    /**
     * 工作点编号
     */
    private String targetPoint;

    /**
     * 任务优先级
     */
    private Integer priority;

    /**
     * 库区
     */
    private String areaCode;
    /**
     * 目标点点别名
     */
    private String endAlias;

    public String getEndAlias() {
        return endAlias;
    }

    public void setEndAlias(String endAlias) {
        this.endAlias = endAlias;
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
}
