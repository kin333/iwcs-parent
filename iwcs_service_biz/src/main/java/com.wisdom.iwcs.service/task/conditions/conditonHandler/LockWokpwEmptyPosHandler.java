package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.domain.task.AreaCondition;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 超越 产线呼叫搬离货架 前置条件--锁定 人工插线区 的一个空储位
 *
 */
@Service
public class LockWokpwEmptyPosHandler implements IConditionHandler{
    @Autowired
    BaseLockEmptyMapService baseLockEmptyMapService;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        AreaCondition areaCondition = new AreaCondition();
        //查找人工插线区的空点位
        areaCondition.setArea(InspurBizConstants.OperateAreaCodeConstants.WOKPWAREA);

        return baseLockEmptyMapService.handleConditionService(subTaskCondition, Arrays.asList(areaCondition));
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return baseLockEmptyMapService.rollbackConditionService(subTaskCondition);
    }
}
