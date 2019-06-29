package com.wisdom.iwcs.service.task.conditonHandler;

import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskConditions;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.SubTaskConditionsMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.base.baseImpl.BaseMapBerthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 产线工作台空闲处理类
 */
@Service
public class PlWbAvaliableConHandler extends AbstractConditionHandler {
    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private BaseMapBerthService baseMapBerthService;
    @Autowired
    private SubTaskConditionsMapper subTaskConditionsMapper;


    @Override
    public boolean handlleConditions(SubTaskConditions subTaskConditions) {
        Long subTaskId = subTaskConditions.getId();
        SubTask subTask = subTaskMapper.selectByPrimaryKey(subTaskId);
        boolean lockSuc = baseMapBerthService.lockMapBerth(subTask.getEndBercode(), null);
        subTaskConditions.setConditionMetStatus("1");
        subTaskConditionsMapper.updateByPrimaryKeySelective(subTaskConditions);
        return lockSuc;
    }
}
