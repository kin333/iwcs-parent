package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.domain.task.EleControlTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.elevator.EleControlTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 查找是否有电梯任务的前置条件, 电梯同一时刻只能有一个任务
 */
@Service
public class CheckEleTaskHandler implements IConditionHandler{
    @Autowired
    EleControlTaskMapper eleControlTaskMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        long count = eleControlTaskMapper.countUnEndTask();
        return count < 1;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}
