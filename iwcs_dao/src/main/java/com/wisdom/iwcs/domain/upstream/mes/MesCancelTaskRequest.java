package com.wisdom.iwcs.domain.upstream.mes;

public class MesCancelTaskRequest extends MesBaseRequest{
    private String taskCode;

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }
}
