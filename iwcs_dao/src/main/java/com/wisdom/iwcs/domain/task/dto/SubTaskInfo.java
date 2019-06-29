package com.wisdom.iwcs.domain.task.dto;

import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskConditions;

import java.util.List;

public class SubTaskInfo extends SubTask {
    /**
     * 前置任务条件
     */
    private List<SubTaskConditions> preTaskRelConditionsList;
    /**
     * 后置任务条件
     */
    private List<SubTaskConditions> afterTaskRelConditionsList;

    public List<SubTaskConditions> getPreTaskRelConditionsList() {
        return preTaskRelConditionsList;
    }

    public void setPreTaskRelConditionsList(List<SubTaskConditions> preTaskRelConditionsList) {
        this.preTaskRelConditionsList = preTaskRelConditionsList;
    }

    public List<SubTaskConditions> getAfterTaskRelConditionsList() {
        return afterTaskRelConditionsList;
    }

    public void setAfterTaskRelConditionsList(List<SubTaskConditions> afterTaskRelConditionsList) {
        this.afterTaskRelConditionsList = afterTaskRelConditionsList;
    }
}
