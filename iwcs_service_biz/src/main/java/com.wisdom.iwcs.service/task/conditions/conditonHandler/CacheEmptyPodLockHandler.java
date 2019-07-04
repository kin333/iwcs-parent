package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.impl.MapResouceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

/**
 * 缓存区补充空货架前置条件--锁定缓存区的一个空储位
 */
public class CacheEmptyPodLockHandler implements IConditionHandler{

    @Autowired
    MapResouceService mapResouceService;
    @Autowired
    SubTaskMapper subTaskMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
//        Long subTaskId = subTaskCondition.getId();
//        SubTask subTask = subTaskMapper.selectByPrimaryKey(subTaskId);
//
//        LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
//        lockMapBerthCondition.setMapCode(subTask.getMapCode());
//        lockMapBerthCondition.setOperateAreaCode("缓存点");
//        lockMapBerthCondition.setLockSource(subTask.getSubTaskNum());
//
//        Result result = mapResouceService.lockEmptyStorageByBizTypeList(Arrays.asList(lockMapBerthCondition));

        return false;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        return false;
    }
}
