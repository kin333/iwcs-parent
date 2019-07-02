package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.SubTaskConditionMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.base.baseImpl.BaseMapBerthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 产线工作台空闲处理类
 */
@Service
public class PlWbAvaliableConHandler implements IConditionHandler {

    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private BaseMapBerthService baseMapBerthService;
    @Autowired
    private SubTaskConditionMapper subTaskConditionsMapper;


    @Override
    public boolean handlleCondition(SubTaskCondition subTaskCondition) {
        Long subTaskId = subTaskCondition.getId();
        SubTask subTask = subTaskMapper.selectByPrimaryKey(subTaskId);
        boolean lockSuc = baseMapBerthService.lockMapBerth(subTask.getEndBercode(), null, subTask.getSubTaskNum());
        subTaskCondition.setConditionMetStatus("1");
        subTaskConditionsMapper.updateByPrimaryKeySelective(subTaskCondition);
        return lockSuc;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {

        Long subTaskId = subTaskCondition.getId();
        SubTask subTask = subTaskMapper.selectByPrimaryKey(subTaskId);
        boolean unlock = baseMapBerthService.unlockMapBerth(subTask.getEndBercode(), null, subTask.getSubTaskNum());
        subTaskCondition.setConditionMetStatus("0");
        subTaskConditionsMapper.updateByPrimaryKeySelective(subTaskCondition);
        return unlock;
    }
}
