package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.domain.task.TaskRelCondition;

/**
 * 任务创建条件判断
 */
public interface IRelConditionHandler {

    public abstract boolean handleCondition(String maintaskNum, TaskRelCondition taskRelCondition);

}
