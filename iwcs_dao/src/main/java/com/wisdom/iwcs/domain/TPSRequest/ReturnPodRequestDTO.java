package com.wisdom.iwcs.domain.TPSRequest;

/**
 * 货架回库
 */
public class ReturnPodRequestDTO {

    /**
     * 请求编号，每个请求都要一个唯一编号， 同一个请求重复提交， 使用同一编号
     */
    private String reqCode;
    /**
     * 请求时间截 格式: “yyyy-MM-dd HH:mm:ss”
     */
    private String reqTime;

    /**
     * 客户端编号，如PDA，HCWMS等。
     */
    private String clientCode;

    /**
     * 令牌号, 由调度系统颁发
     */
    private String tokenCode;

    /**
     * returnPod
     */
    private String interfaceName;

    /**
     * 任务单号,当前工作台任务单号，多个时可传一个
     */
    private String taskCode;
    /**
     * 默认为空：货架回库
     * 任务类型：
     * 1：初始化入库
     * 2：循环入库回库
     * 3:  理货业务回库
     * 4: 自动化工作台回库（货架放下）
     */
    private String taskTyp;
    /**
     * 回库策略（大区域和小区域，多个组合，有优先级）
     */
    private String returnPodStrategy;

    /**
     * 货位编号(初始化入库必填)
     */
    private String binCode;

    /**
     * 工作台编号(初始化入库必填)
     */
    private String wbCode;

    private String prePick;

    public String getPrePick() {
        return prePick;
    }

    public void setPrePick(String prePick) {
        this.prePick = prePick;
    }

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

    public String getTaskTyp() {
        return taskTyp;
    }

    public void setTaskTyp(String taskTyp) {
        this.taskTyp = taskTyp;
    }

    public String getReturnPodStrategy() {
        return returnPodStrategy;
    }

    public void setReturnPodStrategy(String returnPodStrategy) {
        this.returnPodStrategy = returnPodStrategy;
    }

    public String getBinCode() {
        return binCode;
    }

    public void setBinCode(String binCode) {
        this.binCode = binCode;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }
}
