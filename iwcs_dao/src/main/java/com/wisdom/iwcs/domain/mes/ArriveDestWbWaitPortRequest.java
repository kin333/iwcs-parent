package com.wisdom.iwcs.domain.mes;

/**
 * Agv到达等待点 通知MES
 * @Author george
 * @Date 2019/8/27 9:54 
 */
public class ArriveDestWbWaitPortRequest {
    /**
     * 唯一任务号，必填项
     */
    private String taskCode;
    /**
     * 等待点
     */
    private String waitPort;
    /**
     * AGV编号
     */
    private String agvCode;

    public String getDoorAction() {
        return doorAction;
    }

    public void setDoorAction(String doorAction) {
        this.doorAction = doorAction;
    }

    /**
     * 到达时间
     */

    private String arriveTime;
    private  String doorAction;

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getWaitPort() {
        return waitPort;
    }

    @Override
    public String toString() {
        return "ArriveDestWbWaitPortRequest{" +
                "taskCode='" + taskCode + '\'' +
                ", waitPort='" + waitPort + '\'' +
                ", agvCode='" + agvCode + '\'' +
                ", arriveTime='" + arriveTime + '\'' +
                ", doorAction='" + doorAction + '\'' +
                '}';
    }

    public void setWaitPort(String waitPort) {
        this.waitPort = waitPort;
    }

    public String getAgvCode() {
        return agvCode;
    }

    public void setAgvCode(String agvCode) {
        this.agvCode = agvCode;
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(String arriveTime) {
        this.arriveTime = arriveTime;
    }
}
