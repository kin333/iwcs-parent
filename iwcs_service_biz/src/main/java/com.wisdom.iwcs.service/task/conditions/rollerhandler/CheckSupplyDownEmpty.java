package com.wisdom.iwcs.service.task.conditions.rollerhandler;

import com.wisdom.iwcs.common.utils.taskUtils.TaskContextUtils;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.domain.task.TaskContext;
import com.wisdom.iwcs.domain.task.dto.ContextDTO;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskContextMapper;
import com.wisdom.iwcs.service.task.conditions.conditonHandler.IConditionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckSupplyDownEmpty implements IConditionHandler {
    private Logger logger = LoggerFactory.getLogger(CheckSupplyDownEmpty.class);

    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    TaskContextMapper taskContextMapper;

    /**
     * 供送料点回收
     * @param subTaskCondition
     * @return
     */

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        logger.info("任务单{}CheckSupplyDownEmpty前置条件检查开始", subTaskCondition.getSubTaskNum());
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());
        String mainTaskCode = subTask.getMainTaskNum();

        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(mainTaskCode);
        String context = taskContext.getContext();
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(context, ContextDTO.class);

        if (contextDTO.getAgvProcessNotify().equals("8")) {
            logger.info("任务单{}CheckSupplyDownEmpty前置条件检查成功", subTaskCondition.getSubTaskNum());
            return true;
        }else if (contextDTO.getAgvProcessNotify().equals("4")) {
            logger.info("任务单{}CheckSupplyDownEmpty前置条件检查成功", subTaskCondition.getSubTaskNum());
            return true;
        }
        return false;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return false;
    }
}
