package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.linebody.impl.LineNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 离开线体工作台任务后置条件
 * @author han
 */
@Component
public class LeaveWorkLineHandler implements IConditionHandler {
    @Autowired
    LineNotifyService lineNotifyService;
    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());
        if (subTask == null) {
            throw new BusinessException(subTaskCondition.getSubTaskNum() + "子单号不存在");
        }
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectOneByBercode(subTask.getStartBercode());
        if (baseMapBerth == null) {
            throw new BusinessException(subTask.getStartBercode() + "地码不存在");
        }
        if (TaskConstants.workTaskStatus.END.equals(subTask.getWorkTaskStatus())) {
//            lineNotifyService.agvStatusine(baseMapBerth.getPointAlias(), TaskConstants.agvTaskType.LEAVE);
            return true;
        }
        return false;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}