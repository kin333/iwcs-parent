package com.wisdom.iwcs.service.task.conditions.rollerhandler;

import com.wisdom.iwcs.common.utils.TaskConstants;
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
 * AGV离开上料点前置条件
 * @author han
 */
@Component
public class AgvReceiveLeaveHandler implements IConditionHandler {
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
        return contextDTO.getCanLeaveUp() != null && contextDTO.getCanLeaveUp();
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}