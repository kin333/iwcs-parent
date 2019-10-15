package com.wisdom.iwcs.service.task.conditions.rollerhandler;

import com.wisdom.iwcs.common.utils.taskUtils.TaskContextUtils;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.domain.task.TaskContext;
import com.wisdom.iwcs.domain.task.dto.ContextDTO;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskContextMapper;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.SupllyNodeType.*;
import com.wisdom.iwcs.service.task.conditions.conditonHandler.IConditionHandler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckRollerContinue implements IConditionHandler {
    private Logger logger = LoggerFactory.getLogger(CheckRollerContinue.class);

    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    TaskContextMapper taskContextMapper;

    /**
     * 滚动是否继续滚动
     * @param subTaskCondition
     * @return
     */
    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        logger.info("任务单{}CheckWorkStatusHandler后置条件检查开始", subTaskCondition.getSubTaskNum());

        SubTask subTaskDTO = new SubTask();

        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());
        String mainTaskCode = subTask.getMainTaskNum();

        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(mainTaskCode);
        String context = taskContext.getContext();
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(context, ContextDTO.class);

        if (!StringUtils.isEmpty(contextDTO.getNodeType())) {
            logger.info("任务单{}前置条件检查成功", subTaskCondition.getSubTaskNum());
            // 判断上料还是下料
            subTaskDTO.setSubTaskNum(subTask.getSubTaskNum());
            if (SEND_TYPE.equals(contextDTO.getNodeType())) {
                if (StringUtils.isNotEmpty(contextDTO.getRecyleWb())) {
                    subTaskDTO.setJsonData("1");
                } else {
                    subTaskDTO.setJsonData("0");
                }
            } else if (RECOVERY_TYPE.equals(contextDTO.getNodeType())) {
                    subTaskDTO.setJsonData(String.valueOf(contextDTO.getEmptyRecyleNum()));
            } else if (RECEIVE_TYPE.equals(contextDTO.getNodeType())) {
                subTaskDTO.setJsonData("");
            }
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
