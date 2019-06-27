package com.wisdom.iwcs.domain.task.dto;

import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.TaskRelConditions;

import java.util.List;

public class SubTaskInfo extends SubTask {
    /**
     * 前置任务条件
     */
    private List<TaskRelConditions> preTaskRelConditionsList;
    /**
     * 后置任务条件
     */
    private List<TaskRelConditions> afterTaskRelConditionsList;

    public List<TaskRelConditions> getPreTaskRelConditionsList() {
        return preTaskRelConditionsList;
    }

    public void setPreTaskRelConditionsList(List<TaskRelConditions> preTaskRelConditionsList) {
        this.preTaskRelConditionsList = preTaskRelConditionsList;
    }

    public List<TaskRelConditions> getAfterTaskRelConditionsList() {
        return afterTaskRelConditionsList;
    }

    public void setAfterTaskRelConditionsList(List<TaskRelConditions> afterTaskRelConditionsList) {
        this.afterTaskRelConditionsList = afterTaskRelConditionsList;
    }
}
