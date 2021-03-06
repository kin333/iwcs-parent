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
    /**
     * 库区
     */
    private String areaCode;

    /**
     * 起点
     */
    private String startPoint;

    /**
     * 目标点
     */
    private String targetPoint;

    /**
     * 子任务单业务属性
     */
    private String subTaskBizProp;

    /**
     * 起始点别名
     */
    private String startPointAlias;

    /**t
     * 终点别名
     */
    private String targetPointAlias;

    /**
     * 终点选取规则
     */
    private String endBercodeAuto;

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

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
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

    public String getSubTaskBizProp() {
        return subTaskBizProp;
    }

    public void setSubTaskBizProp(String subTaskBizProp) {
        this.subTaskBizProp = subTaskBizProp;
    }

    public String getStartPointAlias() {
        return startPointAlias;
    }

    public void setStartPointAlias(String startPointAlias) {
        this.startPointAlias = startPointAlias;
    }

    public String getTargetPointAlias() {
        return targetPointAlias;
    }

    public void setTargetPointAlias(String targetPointAlias) {
        this.targetPointAlias = targetPointAlias;
    }

    public String getEndBercodeAuto() {
        return endBercodeAuto;
    }

    public void setEndBercodeAuto(String endBercodeAuto) {
        this.endBercodeAuto = endBercodeAuto;
    }
}
