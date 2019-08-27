package com.wisdom.iwcs.domain.upstream.mes;

public class ConWaitToDestWbRequest {

    /**
     * 唯一任务号，必填项
     */
    private String taskCode;
    /**
     * 搬运任务终点
     */
    private String destWb;

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getDestWb() {
        return destWb;
    }

    public void setDestWb(String destWb) {
        this.destWb = destWb;
    }
}
