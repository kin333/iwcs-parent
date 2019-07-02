package com.wisdom.iwcs.domain.task.dto;

import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;

import java.util.List;

public class SubTaskInfo extends SubTask {
    /**
     * 前置任务条件
     */
    private List<SubTaskCondition> preTaskRelConditionsList;
    /**
     * 后置任务条件
     */
    private List<SubTaskCondition> afterTaskRelConditionsList;

    public List<SubTaskCondition> getPreTaskRelConditionsList() {
        return preTaskRelConditionsList;
    }

    public void setPreTaskRelConditionsList(List<SubTaskCondition> preTaskRelConditionsList) {
        this.preTaskRelConditionsList = preTaskRelConditionsList;
    }

    public List<SubTaskCondition> getAfterTaskRelConditionsList() {
        return afterTaskRelConditionsList;
    }

    public void setAfterTaskRelConditionsList(List<SubTaskCondition> afterTaskRelConditionsList) {
        this.afterTaskRelConditionsList = afterTaskRelConditionsList;
    }
}
