package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.domain.task.dto.StrategyHandlerDTO;

public interface IStrategyConditionHandler {

    public abstract StrategyHandlerDTO handleCondition(SubTaskCondition subTaskCondition);
}
