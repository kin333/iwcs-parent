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
 * 电梯缓存区查找有货货架
 */
@Service
public class EleCachePodHandler implements IConditionHandler{
    @Autowired
    MapResouceService mapResouceService;
    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    BaseLockEmptyPodService baseLockEmptyPodService;


    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        AreaCondition areaCondition = new AreaCondition();
        //查找电梯缓存区货架
        areaCondition.setArea(InspurBizConstants.BizTypeConstants.ELEVATORCACHEAREA);

        return baseLockEmptyPodService.handleConditionService(subTaskCondition,
                Arrays.asList(areaCondition),
                InspurBizConstants.InStock.HAVE_GOODS);
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return baseLockEmptyPodService.rollbackConditionService(subTaskCondition);
    }
}
