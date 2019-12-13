package com.wisdom.iwcs.domain.upstream.mes;

public class MesCancelTaskRequest extends MesBaseRequest{
    private String taskCode;
    /**
     * 新的小车号
     */
    private String robotCode;

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getRobotCode() {
        return robotCode;
    }

    public void setRobotCode(String robotCode) {
        this.robotCode = robotCode;
    }
}
