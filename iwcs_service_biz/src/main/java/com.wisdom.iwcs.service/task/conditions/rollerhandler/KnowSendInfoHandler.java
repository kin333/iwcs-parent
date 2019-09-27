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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 通知MES上料数量后置条件---MES通知WCS下料点和下料数量
 * @author han
 */
@Component
public class KnowSendInfoHandler implements IConditionHandler {
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
        return StringUtils.isNotEmpty(contextDTO.getSupplyUnLoadWbFirst());
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}