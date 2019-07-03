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
    private String wbCode;

    /**
     * 货架号
     */
    private String podCode;

    /**
     * 起始点
     */
    private String startBercode;

    /**
     * 任务优先级
     */
    private Integer priority;

    public String getTaskTypeCode() {
        return taskTypeCode;
    }

    public void setTaskTypeCode(String taskTypeCode) {
        this.taskTypeCode = taskTypeCode;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getStartBercode() {
        return startBercode;
    }

    public void setStartBercode(String startBercode) {
        this.startBercode = startBercode;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
