package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.domain.task.AreaCondition;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 缓存区补充空货架前置条件--锁定老化区的一个空货架
 * @author han
 */
@Service
public class EmptyPodLockForAgingCacheHandler implements IConditionHandler {
    @Autowired
    BaseLockEmptyPodService baseLockEmptyPodService;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {

        AreaCondition areaCondition = new AreaCondition();
        //查找老化区缓存区的空货架
        areaCondition.setArea(InspurBizConstants.BizTypeConstants.AGINGCACHEAREA);

        return baseLockEmptyPodService.handleConditionService(subTaskCondition, Arrays.asList(areaCondition), InspurBizConstants.InStock.NO_GOODS);
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return baseLockEmptyPodService.rollbackConditionService(subTaskCondition);
    }
}
