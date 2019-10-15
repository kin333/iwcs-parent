package com.wisdom.iwcs.service.task.conditions.rollerhandler;

import com.wisdom.iwcs.common.utils.TaskConstants;
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
public class CheckTaskEndUpdateInfoHandler implements IConditionHandler {
    private Logger logger = LoggerFactory.getLogger(CheckTaskEndUpdateInfoHandler.class);

    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    TaskContextMapper taskContextMapper;
    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {

        logger.info("任务单{}CheckWorkStatusHandler后置条件检查开始", subTaskCondition.getSubTaskNum());
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());
        String mainTaskCode = subTask.getMainTaskNum();

        //判断实际工作状态已结束
        if (TaskConstants.workTaskStatus.END.equals(subTask.getWorkTaskStatus())) {
            logger.info("任务单{}后置条件检查成功", subTaskCondition.getSubTaskNum());

            TaskContext taskContext = taskContextMapper.selectByMainTaskNum(mainTaskCode);
            String context = taskContext.getContext();
            ContextDTO contextDTO = TaskContextUtils.jsonToObject(context, ContextDTO.class);

            contextDTO.setNodeType("");
            contextDTO.setRecyleWb("");
            contextDTO.setChaLeaveUpEmpty(false);
            contextDTO.setChaLeaveUpGood(false);
            contextDTO.setChaLeaveDownEmpty(false);
            contextDTO.setChaLeaveGood(false);

            String contextJson = TaskContextUtils.objectToJson(contextDTO);
            //更新task_context表
            TaskContext taskContextDTO = new TaskContext();
            taskContext.setMainTaskNum(mainTaskCode);
            taskContext.setContext(contextJson);
            taskContextMapper.updateByMainTaskNum(taskContextDTO);
            return true;
        }
        return false;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}
