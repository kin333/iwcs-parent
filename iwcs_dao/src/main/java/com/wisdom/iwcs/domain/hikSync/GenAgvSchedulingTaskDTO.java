package com.wisdom.iwcs.domain.hikSync;


import java.util.List;

/**
 * @author LNN
 * 生成任务单
 * 上层系统平台发送调度请求, RCS 通过请求参数, 生成调度 AGV 任务单。
 */
public class GenAgvSchedulingTaskDTO {

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
     * genAgvSchedulingTask
     * TCP 协议必传，REST 协议不用传， 传了也不影响
     */
    private String interfaceName;

    /**
     * 任务类型，与在 RCS-2000 端配置的主任务类型编号一致
     * F01: 厂内货架搬运
     * F02：厂内货架空满交换
     * F03：辊筒搬运接驳
     * F04：厂内货架出库 AGV 待命
     * F05：旋转货架
     */
    private String taskTyp;

    /**
     * 工作位
     */
    private String wbCode;

    /**
     * 站点集合
     */
    private List<PositionCodePathDTO> positionCodePath;

    /**
     * 货架编号
     */
    private String podCode;

    /**
     * 方向
     */
    private String podDir;

    /**
     * 货架类型
     */
    private String podTyp;

    /**
     * 物料批次或货架上的物料唯一编码, 生成任务单时,货架与物料直接绑定时使用
     */
    private String materialLot;

    /**
     * 优先级
     */
    private String priority;

    /**
     * 任务单号
     */
    private String taskCode;

    /**
     * AGV 编号
     */
    private String agvCode;

    private String robotCode;

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

    public String getTaskTyp() {
        return taskTyp;
    }

    public void setTaskTyp(String taskTyp) {
        this.taskTyp = taskTyp;
    }

    public String getWbCode() {
        return wbCode;
    }

    public void setWbCode(String wbCode) {
        this.wbCode = wbCode;
    }

    public List<PositionCodePathDTO> getPositionCodePath() {
        return positionCodePath;
    }

    public void setPositionCodePath(List<PositionCodePathDTO> positionCodePath) {
        this.positionCodePath = positionCodePath;
    }

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getPodDir() {
        return podDir;
    }

    public void setPodDir(String podDir) {
        this.podDir = podDir;
    }

    public String getPodTyp() {
        return podTyp;
    }

    public String getRobotCode() {
        return robotCode;
    }

    public void setRobotCode(String robotCode) {
        this.robotCode = robotCode;
    }

    public void setPodTyp(String podTyp) {
        this.podTyp = podTyp;
    }

    public String getMaterialLot() {
        return materialLot;
    }

    public void setMaterialLot(String materialLot) {
        this.materialLot = materialLot;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
