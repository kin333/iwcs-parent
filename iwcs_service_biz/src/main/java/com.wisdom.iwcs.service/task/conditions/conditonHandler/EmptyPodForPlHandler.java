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
 * 已选中补充货架并锁定(优先空货架缓存点,其次老化区空货架)
 * 查找空货架
 * @author han
 */
@Service
public class EmptyPodForPlHandler implements IConditionHandler{
    @Autowired
    MapResouceService mapResouceService;
    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    BaseLockEmptyPodService baseLockEmptyPodService;


    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        AreaCondition areaCondition1 = new AreaCondition();
        //优先查找缓存区货架
        areaCondition1.setBizType(InspurBizConstants.BizTypeConstants.LINECACHEAREA);
        AreaCondition areaCondition2 = new AreaCondition();
        //次优先查找老化区货架
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
