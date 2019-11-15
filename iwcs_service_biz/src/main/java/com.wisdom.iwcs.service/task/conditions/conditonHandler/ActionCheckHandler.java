package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskAction;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.domain.task.TaskRelAction;
import com.wisdom.iwcs.mapper.task.SubTaskActionMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.TaskRelActionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wisdom.iwcs.common.utils.TaskConstants.actionStatus.SEND_SUCCESS;

/**
 * 检查Action是否全部完成处理器
 * @author han
 */
@Service
public class ActionCheckHandler implements IConditionHandler{
    private Logger logger = LoggerFactory.getLogger(ActionCheckHandler.class);

    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    TaskRelActionMapper taskRelActionMapper;
    @Autowired
    SubTaskActionMapper subTaskActionMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        String subTaskNum = subTaskCondition.getSubTaskNum();
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);
        //查询子任务含有多少活动
        List<TaskRelAction> actions = taskRelActionMapper.selectExecuteModeByTempCode(subTask.getTemplCode());
        if (actions.size() < 1) {
            return true;
        }
        //检查每一个Action是否已经生成并发送成功
        for (TaskRelAction action : actions) {
            SubTaskAction subTaskAction = subTaskActionMapper.selectByActionCode(action.getActionCode(), subTaskNum);
            if (subTaskAction == null && "false".equals(action.getCreateCondition())) {
                return true;
            }
            if (subTaskAction == null || !SEND_SUCCESS.equals(subTaskAction.getActionStatus())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}
