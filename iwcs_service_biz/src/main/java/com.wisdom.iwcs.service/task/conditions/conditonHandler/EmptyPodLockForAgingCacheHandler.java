package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.domain.task.AreaCondition;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 缓存区补充空货架前置条件--锁定老化区的一个空货架 (优先老化区缓存区，其次老化区)
 * @author snn
 */
@Service
public class EmptyPodLockForAgingCacheHandler implements IConditionHandler {
    @Autowired
    BaseLockEmptyPodService baseLockEmptyPodService;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {

        AreaCondition areaCondition1 = new AreaCondition();
        //优先查找老化区缓存区
        areaCondition1.setBizType(InspurBizConstants.BizTypeConstants.AGINGCACHEAREA);
        AreaCondition areaCondition2 = new AreaCondition();
        //其次查找老化区
        areaCondition2.setArea(InspurBizConstants.OperateAreaCodeConstants.AGINGREA);

        return baseLockEmptyPodService.handleConditionService(subTaskCondition,
                Arrays.asList(areaCondition1, areaCondition2),
                InspurBizConstants.InStock.NO_GOODS);

    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return baseLockEmptyPodService.rollbackConditionService(subTaskCondition);
    }
}
