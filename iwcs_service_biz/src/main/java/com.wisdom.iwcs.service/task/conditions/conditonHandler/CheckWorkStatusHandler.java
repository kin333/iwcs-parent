package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.domain.task.dto.SubTaskStatusEnum;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.impl.SubTaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 后置条件检查,检查任务是否完成
 */
@Service
public class CheckWorkStatusHandler implements IConditionHandler {
    private Logger logger = LoggerFactory.getLogger(BaseLockEmptyMapService.class);

    @Autowired
    SubTaskMapper subTaskMapper;

    @Autowired
    SubTaskService subTaskService;


    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        logger.info("任务单{}后置条件检查开始", subTaskCondition.getSubTaskNum());
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());

        //如果实际工作状态已结束,则将任务状态设置为已结束
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
