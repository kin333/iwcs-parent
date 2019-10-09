package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.domain.task.AreaCondition;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.impl.MapResouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 线体缓存区有空货架
 * 查找空货架
 * @author
 */
@Service
public class EmptyPodForLineCacheHandler implements IConditionHandler{
    @Autowired
    MapResouceService mapResouceService;
    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    BaseLockEmptyPodService baseLockEmptyPodService;


    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        AreaCondition areaCondition1 = new AreaCondition();
        //线体缓存区空货架
        areaCondition1.setBizType(InspurBizConstants.BizTypeConstants.LINECACHEAREA);

        return baseLockEmptyPodService.handleConditionService(subTaskCondition,
                                    Arrays.asList(areaCondition1),
                                    InspurBizConstants.InStock.NO_GOODS);
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return baseLockEmptyPodService.rollbackConditionService(subTaskCondition);
    }
}
