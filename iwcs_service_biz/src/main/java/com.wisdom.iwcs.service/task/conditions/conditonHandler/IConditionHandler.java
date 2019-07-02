package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.domain.task.SubTaskConditions;

/**
 * 条件处理器
 */
public interface IConditionHandler {

    public abstract boolean handlleCondition(SubTaskConditions subTaskConditions);

    public abstract boolean rollbackCondition(SubTaskConditions subTaskConditions);
}
