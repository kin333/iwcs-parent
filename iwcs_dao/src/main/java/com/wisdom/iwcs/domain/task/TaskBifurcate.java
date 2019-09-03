package com.wisdom.iwcs.domain.task;

import java.util.List;

public class TaskBifurcate {

    private String subTaskCode;
    private String subTaskName;
    private String templCode;
    private String overFlow;

    public String getSubTaskCode() {
        return subTaskCode;
    }

    public void setSubTaskCode(String subTaskCode) {
        this.subTaskCode = subTaskCode;
    }

    public String getSubTaskName() {
        return subTaskName;
    }

    public void setSubTaskName(String subTaskName) {
        this.subTaskName = subTaskName;
    }

    public String getTemplCode() {
        return templCode;
    }

    public void setTemplCode(String templCode) {
        this.templCode = templCode;
    }

    public String getOverFlow() {
        return overFlow;
    }

    public void setOverFlow(String overFlow) {
        this.overFlow = overFlow;
    }

    public List<SubTaskTyp> getSubTaskType() {
        return subTaskType;
    }

    public void setSubTaskType(List<SubTaskTyp> subTaskType) {
        this.subTaskType = subTaskType;
    }

    private List<SubTaskTyp> subTaskType;

}
