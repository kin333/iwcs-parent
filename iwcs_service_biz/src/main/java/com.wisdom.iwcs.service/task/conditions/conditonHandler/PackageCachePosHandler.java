package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.domain.task.AreaCondition;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * 包装体缓存区查找空储位
 */
@Service
public class PackageCachePosHandler implements IConditionHandler{

    @Autowired
    BaseLockEmptyMapService baseLockEmptyMapService;
    @Autowired
    SubTaskMapper subTaskMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskCondition.getSubTaskNum());

        AreaCondition areaCondition = new AreaCondition();
        //查找包装体缓存区空储位
        areaCondition.setArea(InspurBizConstants.BizTypeConstants.PAGECACHEAREA + subTask.getMapCode());

        return baseLockEmptyMapService.handleConditionService(subTaskCondition, Arrays.asList(areaCondition));
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return baseLockEmptyMapService.rollbackConditionService(subTaskCondition);
    }
}
