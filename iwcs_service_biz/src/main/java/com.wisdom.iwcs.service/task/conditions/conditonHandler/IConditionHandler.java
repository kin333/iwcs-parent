package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.domain.task.SubTaskCondition;

/**
 * 条件处理器
 */
public interface IConditionHandler {

    public abstract boolean handlleCondition(SubTaskCondition subTaskCondition);

    public abstract boolean rollbackCondition(SubTaskCondition subTaskCondition);
}
