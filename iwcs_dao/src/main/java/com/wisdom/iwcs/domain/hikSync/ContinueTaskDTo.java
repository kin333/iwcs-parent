package com.wisdom.iwcs.domain.hikSync;


public class ContinueTaskDTo {

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
     * 令牌号, 由调度系统颁发
     */
    private String tokenCode;

    /**
     * continueTask
     * TCP 协议必传，REST 协议不用传， 传了也不影响
     */
    private String interfaceName;

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
