package com.wisdom.iwcs.service.task.conditions.rollerhandler;

import com.wisdom.iwcs.common.utils.taskUtils.TaskContextUtils;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.domain.task.TaskContext;
import com.wisdom.iwcs.domain.task.dto.ContextDTO;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskContextMapper;
import com.wisdom.iwcs.service.task.conditions.conditonHandler.BaseLockEmptyMapService;
import com.wisdom.iwcs.service.task.conditions.conditonHandler.IConditionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * AGV离开第二个下料点前置条件
 * @author han
 */
@Component
public class AgvSendSecondLeaveHandler implements IConditionHandler {
    private Logger logger = LoggerFactory.getLogger(BaseLockEmptyMapService.class);

    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    TaskContextMapper taskContextMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(subTask.getMainTaskNum());
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(taskContext.getContext(), ContextDTO.class);
        return contextDTO.getCanLeaveDownSecond() != null && contextDTO.getCanLeaveDownSecond();
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}