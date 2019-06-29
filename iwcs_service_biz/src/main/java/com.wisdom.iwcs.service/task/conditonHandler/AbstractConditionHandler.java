package com.wisdom.iwcs.service.task.conditonHandler;

import com.wisdom.iwcs.domain.task.SubTaskConditions;

/**
 * 条件处理器
 */
public abstract class AbstractConditionHandler {

    public abstract boolean handlleConditions(SubTaskConditions subTaskConditions);

}
