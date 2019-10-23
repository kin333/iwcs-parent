package com.wisdom.iwcs.service.task.conditions.rollerhandler;

import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.conditions.conditonHandler.BaseLockEmptyMapService;
import com.wisdom.iwcs.service.task.conditions.conditonHandler.IConditionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 检查任务是否完成的后置条件
 * @author han
 */
@Component
public class CheckRollerTaskEndHandler implements IConditionHandler {
    private Logger logger = LoggerFactory.getLogger(CheckRollerTaskEndHandler.class);

    @Autowired
    SubTaskMapper subTaskMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        logger.info("任务单{}CheckWorkStatusHandler后置条件检查开始", subTaskCondition.getSubTaskNum());
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());

        //判断实际工作状态已结束
        if (TaskConstants.workTaskStatus.END.equals(subTask.getWorkTaskStatus())) {
            logger.info("任务单{}后置条件检查成功", subTaskCondition.getSubTaskNum());
            return true;
        }
        return false;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}
