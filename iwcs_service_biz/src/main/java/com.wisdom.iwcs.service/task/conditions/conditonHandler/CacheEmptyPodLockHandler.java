package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.impl.MapResouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

/**
 * 缓存区补充空货架前置条件--锁定缓存区的一个空储位
 * @author  han
 */
public class CacheEmptyPodLockHandler implements IConditionHandler{

    @Autowired
    MapResouceService mapResouceService;
    @Autowired
    SubTaskMapper subTaskMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        Long subTaskId = subTaskCondition.getId();
        SubTask subTask = subTaskMapper.selectByPrimaryKey(subTaskId);

        LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
        lockMapBerthCondition.setMapCode(subTask.getMapCode());
        lockMapBerthCondition.setOperateAreaCode(InspurBizConstants.BizTypeConstants.LINECACHEAREA);
        lockMapBerthCondition.setLockSource(subTask.getSubTaskNum());
        //锁定该任务点
        Result result = mapResouceService.lockEmptyStorageByBizTypeList(Arrays.asList(lockMapBerthCondition));
        if (result.getReturnCode() != HttpStatus.OK.value()) {
            return false;
        }
        BaseMapBerth baseMapBerth = (BaseMapBerth)result.getReturnData();
        //更新子任务单中的结束点信息
        subTaskMapper.updateEndCodeBySubTaskCode(subTask.getSubTaskNum(), baseMapBerth);

        return true;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        //还原子任务单中的货架号
        subTaskMapper.updateEndCodeBySubTaskCode(subTaskCondition.getSubTaskNum(), new BaseMapBerth());
        //还原地图数据的锁定信息
        Long subTaskId = subTaskCondition.getId();
        SubTask subTask = subTaskMapper.selectByPrimaryKey(subTaskId);
        LockStorageDto lockStorageDto = new LockStorageDto();
        lockStorageDto.setMapCode(subTask.getMapCode());
        lockStorageDto.setBerCode(subTask.getEndBercode());
        lockStorageDto.setLockSource("null");
        Result result = mapResouceService.unlockMapBerth(lockStorageDto);
        return result.getReturnCode() ==  HttpStatus.OK.value();
    }
}
