package com.wisdom.iwcs.domain.task.dto;

import com.wisdom.iwcs.domain.task.TaskRel;
import com.wisdom.iwcs.domain.task.TaskRelCondition;

import java.util.List;

public class RelAndConditionDTO {

    private TaskRelDTO taskRel;
    private List<TaskRelConditionDTO> taskRelCondition;

    public TaskRelDTO getTaskRel() {
        return taskRel;
    }

    public void setTaskRel(TaskRelDTO taskRel) {
        this.taskRel = taskRel;
    }

    public List<TaskRelConditionDTO> getTaskRelCondition() {
        return taskRelCondition;
    }

    public void setTaskRelCondition(List<TaskRelConditionDTO> taskRelCondition) {
        this.taskRelCondition = taskRelCondition;
    }
}
