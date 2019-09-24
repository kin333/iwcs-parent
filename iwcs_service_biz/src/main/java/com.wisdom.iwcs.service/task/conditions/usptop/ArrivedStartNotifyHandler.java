package com.wisdom.iwcs.service.task.conditions.usptop;

import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.conditions.conditonHandler.IConditionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.wisdom.iwcs.common.utils.TaskConstants.bizProcess.*;

/**
 * 到达起点通知前置条件
 * @author han
 */
@Component
public class ArrivedStartNotifyHandler implements IConditionHandler {
    @Autowired
    MainTaskMapper mainTaskMapper;
    @Autowired
    SubTaskMapper subTaskMapper;
    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(subTask.getMainTaskNum());
        //如果主任务状态在: 到达起点,离开起点,入围栏到达围栏外等待点,出围栏到达围栏内等待点  时,可发送到达起点通知
        return TASK_START.equals(mainTask.getBizProcess()) || TASK_LEAVE.equals(mainTask.getBizProcess())
                || ENTER_ARRIVED_OUT.equals(mainTask.getBizProcess()) || COME_ARRIVED_IN.equals(mainTask.getBizProcess());
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}
