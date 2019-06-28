package com.wisdom.iwcs.domain.hikSync;

public class CancelTaskDTO {

    /**
     * 请求编号，每个请求都要一个唯一编号， 同一个请求重复提交， 使用同一编号。
     */
    private String reqCode;

    /**
     * 请求时间截 格式: “yyyy-MM-dd HH:mm:ss”
     */
    private String reqTime;

    /**
     * 客户端编号，如 PDA，HCWMS 等
     */
    private String clientCode;
    /**
     * continueTask
     * TCP 协议必传，REST 协议不用传， 传了也不影响
     */
    private String interfaceName;

    /**
     * 令牌号, 由调度系统颁发
     */
    private String tokenCode;

    /**
     * 任务单号
     */
    private String taskCode;

    /**
     * AGV 编号
     */
    private String agvCode;

    /**
     * 取消任务
     * 通过任务组批量取消, 在批量生成任务单后才能使用
     */
    private String taskGroupCode;

    public String getReqCode() {
        return reqCode;
    }

    public void setReqCode(String reqCode) {
        this.reqCode = reqCode;
    }

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

    public String getClientCode() {
        return clientCode;
    }

    public void setClientCode(String clientCode) {
        this.clientCode = clientCode;
    }

    public String getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
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

    public String getTaskGroupCode() {
        return taskGroupCode;
    }

    public void setTaskGroupCode(String taskGroupCode) {
        this.taskGroupCode = taskGroupCode;
    }

}
