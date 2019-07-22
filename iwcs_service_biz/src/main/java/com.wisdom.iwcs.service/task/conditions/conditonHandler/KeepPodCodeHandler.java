package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.domain.task.TaskRelCondition;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskRelConditionMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 多任务时,子任务间保持(传递)货架信息的后置条件
 * 作用是把当前的货架号不解锁,直接赋值给下一个子任务
 * @author han
 */
public class KeepPodCodeHandler implements IConditionHandler {
    private Logger logger = LoggerFactory.getLogger(KeepPodCodeHandler.class);
    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    TaskRelConditionMapper taskRelConditionMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        logger.info("任务单{}KeepPodCodeHandler后置条件检查开始", subTaskCondition.getSubTaskNum());
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());
        Preconditions.checkArgument(StringUtils.isNotEmpty(subTask.getPodCode()), subTask.getSubTaskNum() + "的货架号为空");
        Preconditions.checkArgument(StringUtils.isNotEmpty(subTask.getEndBercode()), subTask.getSubTaskNum() + "的终点为空");

        //判断实际工作状态已结束
        if (TaskConstants.workTaskStatus.END.equals(subTask.getWorkTaskStatus())) {
            List<TaskRelCondition> taskRelConditions = taskRelConditionMapper.selectByMainTaskTypeCodeAndSubCode(subTask.getMainTaskType(), subTask.getSubTaskTyp());
            if (taskRelConditions == null || taskRelConditions.size() != 1) {
                throw new BusinessException(subTask.getMainTaskType() + "的" +  subTask.getSubTaskTyp() + "的任务模板有错误");
            }
            Integer subTaskSeq = taskRelConditions.get(0).getSubTaskSeq();
            //获取下一个子任务的任务类型
            TaskRelCondition taskRelCondition = taskRelConditionMapper.selectByMainTaskTypeAndPriority(subTask.getMainTaskType(), subTaskSeq + 1);
            SubTask nextSubTask = new SubTask();
            nextSubTask.setMainTaskNum(subTask.getMainTaskNum());
            nextSubTask.setSubTaskTyp(taskRelCondition.getSubTaskTypeCode());
            nextSubTask.setPodCode(subTask.getPodCode());
            nextSubTask.setStartBercode(subTask.getEndBercode());
            int num = subTaskMapper.updateByMainTaskNumAndSubTaskType(subTask);
            if (num > 0) {
                logger.info("货架传递成功,子任务{}已成功发送货架号{}给下一个子任务", subTask.getSubTaskNum(), subTask.getPodCode());
                return true;
            }
            logger.error("货架传递失败,子任务{}未能成功发送货架号{}给下一个子任务", subTask.getSubTaskNum(), subTask.getPodCode());

        }

        return false;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}
