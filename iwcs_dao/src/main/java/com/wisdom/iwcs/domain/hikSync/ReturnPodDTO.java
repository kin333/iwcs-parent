package com.wisdom.iwcs.domain.hikSync;

public class ReturnPodDTO {
    /**
     * 出库点位号，如101，201等 必填
     */
    private String wbCode;
    /**
     * 任务类型，AgvTaskConstants.java中的任务类型常量（AgvTaskTypeConstants）
     */
    private String taskType;
    /**
     * 出库货架号 选填
     */
    private String bincode;
    /**
     * 选填，呼叫客户端类型，0手持，1PC端，2上游系统
     */
    private String srcClientType;
    /**
     * 选填，呼叫编号,上游系统指定
     */
    private String srcReqCode;
    /**
     * 选填，呼叫客户端编号，手持设备编号等
     */
    private String srcClientCode;
    /**
     * 选填，呼叫用户编号，登陆用户等
     */
    private String srcUserCode;

    @Override
    public String toString() {
        return "ReturnPodDTO{" +
                "wbCode='" + wbCode + '\'' +
                ", taskType='" + taskType + '\'' +
                ", bincode=" + bincode +
                ", srcClientType='" + srcClientType + '\'' +
                ", srcReqCode='" + srcReqCode + '\'' +
                ", srcClientCode='" + srcClientCode + '\'' +
                ", srcUserCode='" + srcUserCode + '\'' +
                '}';
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getBincode() {
        return bincode;
    }

    public void setBincode(String bincode) {
        this.bincode = bincode;
    }

    public String getSrcClientType() {
        return srcClientType;
    }

    public void setSrcClientType(String srcClientType) {
        this.srcClientType = srcClientType;
    }

    public String getSrcReqCode() {
        return srcReqCode;
    }

    public void setSrcReqCode(String srcReqCode) {
        this.srcReqCode = srcReqCode;
    }

    public String getSrcClientCode() {
        return srcClientCode;
    }

    public void setSrcClientCode(String srcClientCode) {
        this.srcClientCode = srcClientCode;
    }

    public String getSrcUserCode() {
        return srcUserCode;
    }

    public void setSrcUserCode(String srcUserCode) {
        this.srcUserCode = srcUserCode;
    }
}
