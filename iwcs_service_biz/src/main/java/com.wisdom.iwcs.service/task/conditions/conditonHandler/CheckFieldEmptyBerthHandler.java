package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.domain.task.AreaCondition;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 检验区检查空储位前置条件
 * @author han
 */
@Service
public class CheckFieldEmptyBerthHandler implements IConditionHandler {

    @Autowired
    BaseLockEmptyMapService baseLockEmptyMapService;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        AreaCondition areaCondition = new AreaCondition();
        //查找检查区的空点位
        areaCondition.setArea(InspurBizConstants.BizTypeConstants.CHECKFIELD);

        return baseLockEmptyMapService.handleConditionService(subTaskCondition, Arrays.asList(areaCondition));
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return baseLockEmptyMapService.rollbackConditionService(subTaskCondition);
    }
}
