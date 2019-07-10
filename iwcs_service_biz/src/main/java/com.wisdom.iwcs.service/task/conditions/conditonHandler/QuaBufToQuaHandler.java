package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.domain.task.AreaCondition;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class QuaBufToQuaHandler implements IConditionHandler {
    @Autowired
    BaseLockEmptyPodService baseLockEmptyPodService;


    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        AreaCondition areaCondition = new AreaCondition();
        //查找检验点缓冲区的空货架
        areaCondition.setArea(InspurBizConstants.OperateAreaCodeConstants.QUAINSPAREA);
        areaCondition.setBizType(InspurBizConstants.BizTypeConstants.QUAINSPCACHEAREA);
        return baseLockEmptyPodService.handleConditionService(subTaskCondition, Arrays.asList(areaCondition), InspurBizConstants.InStock.NO_GOODS);

    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return baseLockEmptyPodService.rollbackConditionService(subTaskCondition);
    }
}
