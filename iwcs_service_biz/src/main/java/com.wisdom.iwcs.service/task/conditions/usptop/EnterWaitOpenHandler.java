package com.wisdom.iwcs.service.task.conditions.usptop;

import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.conditions.conditonHandler.IConditionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.wisdom.iwcs.common.utils.TaskConstants.bizProcess.ENTER_ALLOW_LEAVE_OUT_WAIT;


/**
 * 进围栏等待开门时,继续Hik任务的前置条件
 * @author han
 */
@Component
public class EnterWaitOpenHandler implements IConditionHandler {
    @Autowired
    MainTaskMapper mainTaskMapper;
    @Autowired
    SubTaskMapper subTaskMapper;
    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(subTask.getMainTaskNum());
        return ENTER_ALLOW_LEAVE_OUT_WAIT.equals(mainTask.getBizProcess());
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}
