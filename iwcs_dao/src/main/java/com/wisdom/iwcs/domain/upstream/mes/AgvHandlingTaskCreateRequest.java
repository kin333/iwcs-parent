package com.wisdom.iwcs.domain.upstream.mes;

/**
 * 工产线、测试区、叉车AGV搬运任务
 * 创建搬运任务
 * @Author george
 * @Date 2019/8/29 15:48
 */
public class AgvHandlingTaskCreateRequest {
    /**
     * 唯一任务号，必填项
     */
    private String taskCode;
    /**
     * 搬运任务起点
     */
    private String srcWb;
    /**
     * 搬运任务终点
     */
    private String destWb;
    /**
     * 货架号，背负式任务必填、叉车不填
     */
    private String podCode;
    /**
     * 优先级—紧急（urgent）、普通（normal）必填项
     */
    private String taskPri;

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getSrcWb() {
        return srcWb;
    }

    public void setSrcWb(String srcWb) {
        this.srcWb = srcWb;
    }

    public String getDestWb() {
        return destWb;
    }

    public void setDestWb(String destWb) {
        this.destWb = destWb;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getTaskPri() {
        return taskPri;
    }

    public void setTaskPri(String taskPri) {
        this.taskPri = taskPri;
    }
}
