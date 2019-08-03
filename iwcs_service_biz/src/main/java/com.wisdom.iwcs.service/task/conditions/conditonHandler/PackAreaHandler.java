package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.domain.task.AreaCondition;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
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
    @Autowired
    SubTaskMapper subTaskMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        String subTaskNum = subTaskCondition.getSubTaskNum();
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);

        AreaCondition areaCondition = new AreaCondition();
        //查找包装区缓存区的空货架
        areaCondition.setArea(InspurBizConstants.OperateAreaCodeConstants.PAGEAREA);
        //包装区缓存区分楼层,每个楼层对应一个
        areaCondition.setBizType(InspurBizConstants.BizTypeConstants.PAGECACHEAREA + subTask.getMapCode());
        return baseLockEmptyPodService.handleConditionService(subTaskCondition, Arrays.asList(areaCondition), InspurBizConstants.InStock.HAVE_GOODS);

    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return baseLockEmptyPodService.rollbackConditionService(subTaskCondition);
    }
}
