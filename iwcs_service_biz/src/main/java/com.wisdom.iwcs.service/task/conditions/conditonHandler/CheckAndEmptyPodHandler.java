package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.InStock.NO_GOODS_INT;

/**
 * 后置条件检查,检查任务是否完成,并且将货架设置为空
 */
@Service
public class CheckAndEmptyPodHandler implements IConditionHandler {
    @Autowired
    private CheckWorkStatusHandler checkWorkStatusHandler;
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;
    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        if (checkWorkStatusHandler.handleCondition(subTaskCondition)) {
            SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());
            basePodDetailMapper.updateInStock(subTask.getPodCode(), NO_GOODS_INT);
            return true;
        }
        return false;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return true;
    }
}
