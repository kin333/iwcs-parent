package com.wisdom.iwcs.domain.control;

public class ContinueTaskRequestDTO {

    /**
     * 工作位
     */
    private String wbCode;

    /**
     * 货架编号
     */
    private String podCode;

    /**
     * 任务单号
     */
    private String taskCode;

    /**
     * AGV 编号
     */
    private String agvCode;

    /**
     * 子任务的序列
     */
    private String taskSeq;

    /**
     * 下一个位置信息
     */
    private Object nextPositionCode;

    /**
     * 自定义字段
     */
    private String data;


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

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getAgvCode() {
        return agvCode;
    }

    public void setAgvCode(String agvCode) {
        this.agvCode = agvCode;
    }

    public String getTaskSeq() {
        return taskSeq;
    }

    public void setTaskSeq(String taskSeq) {
        this.taskSeq = taskSeq;
    }

    public Object getNextPositionCode() {
        return nextPositionCode;
    }

    public void setNextPositionCode(Object nextPositionCode) {
        this.nextPositionCode = nextPositionCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
