package com.wisdom.iwcs.domain.control;

public class CancelTaskRequestDTO {

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
