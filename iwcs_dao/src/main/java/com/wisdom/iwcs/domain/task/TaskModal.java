package com.wisdom.iwcs.domain.task;

public class TaskModal {

    private String mainTaskCode;
    private String mainTaskName;
    private String subTaskCode;
    private String subTaskName;
    private String searchMainCode;
    private String searchSubCode;
    private String taskType;
    private String templCode;

    public String getMainTaskCode() {
        return mainTaskCode;
    }

    public void setMainTaskCode(String mainTaskCode) {
        this.mainTaskCode = mainTaskCode;
    }

    public String getMainTaskName() {
        return mainTaskName;
    }

    public void setMainTaskName(String mainTaskName) {
        this.mainTaskName = mainTaskName;
    }

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

    public String getSearchMainCode() {
        return searchMainCode;
    }

    public void setSearchMainCode(String searchMainCode) {
        this.searchMainCode = searchMainCode;
    }

    public String getSearchSubCode() {
        return searchSubCode;
    }

    public void setSearchSubCode(String searchSubCode) {
        this.searchSubCode = searchSubCode;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTemplCode() {
        return templCode;
    }

    public void setTemplCode(String templCode) {
        this.templCode = templCode;
    }
}
