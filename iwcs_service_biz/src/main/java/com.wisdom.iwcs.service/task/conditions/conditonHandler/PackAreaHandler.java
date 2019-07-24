package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.domain.task.AreaCondition;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 一楼包装线体区呼叫满货货架前置条件-->是否有有货的货架
 * @author han
 */
@Component
public class PackAreaHandler implements IConditionHandler {

    @Autowired
    BaseLockEmptyPodService baseLockEmptyPodService;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        AreaCondition areaCondition = new AreaCondition();
        //查找包装区缓存区的空货架
        areaCondition.setArea(InspurBizConstants.OperateAreaCodeConstants.PAGEAREA);
        areaCondition.setBizType(InspurBizConstants.BizTypeConstants.PAGECACHEAREA);
        return baseLockEmptyPodService.handleConditionService(subTaskCondition, Arrays.asList(areaCondition), InspurBizConstants.InStock.NO_GOODS);

    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return baseLockEmptyPodService.rollbackConditionService(subTaskCondition);
    }
}
