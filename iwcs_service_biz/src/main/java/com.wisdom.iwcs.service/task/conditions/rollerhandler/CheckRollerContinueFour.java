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
public class CheckRollerContinueFour implements IConditionHandler {
    private Logger logger = LoggerFactory.getLogger(CheckRollerContinueFour.class);

    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    TaskContextMapper taskContextMapper;

    /**
     * 滚动是否继续滚动 -------
     * @param subTaskCondition
     * @return
     */
    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        logger.info("任务单{}CheckRollerContinueTwo前置条件检查开始", subTaskCondition.getSubTaskNum());

        SubTask subTaskDTO = new SubTask();

        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());
        String mainTaskCode = subTask.getMainTaskNum();

        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(mainTaskCode);
        String context = taskContext.getContext();
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(context, ContextDTO.class);

        subTaskDTO.setSubTaskNum(subTask.getSubTaskNum());
        if (contextDTO.getRollerUpEmpty() != null && contextDTO.getRollerUpEmpty()){
            subTaskDTO.setJsonData("");
            subTaskMapper.updateJsonData(subTaskDTO.getSubTaskNum(), subTaskDTO.getJsonData());
            return true;
        }

        return false;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}
