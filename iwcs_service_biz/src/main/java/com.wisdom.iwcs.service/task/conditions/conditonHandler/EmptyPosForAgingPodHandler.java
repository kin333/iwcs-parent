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

import java.util.ArrayList;
import java.util.List;

/**
 * 产线工作点去老化区前置条件---目标区域有空储位并锁定一个(自动区模式优先放置自动取,手动模式优先放置手动区)
 * @author han
 */
public class EmptyPosForAgingPodHandler implements IConditionHandler{
    @Autowired
    MapResouceService mapResouceService;
    @Autowired
    SubTaskMapper subTaskMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        Long subTaskId = subTaskCondition.getId();
        SubTask subTask = subTaskMapper.selectByPrimaryKey(subTaskId);

        List<LockMapBerthCondition> conditionList = new ArrayList<>();
        //自动区条件
        LockMapBerthCondition lockConditionAuto = new LockMapBerthCondition();
        lockConditionAuto.setMapCode(subTask.getMapCode());
        lockConditionAuto.setOperateAreaCode(InspurBizConstants.BizTypeConstants.AGINGAREAAUTO);
        lockConditionAuto.setLockSource(subTask.getSubTaskNum());
        //手动区条件
        LockMapBerthCondition lockConditionManual = new LockMapBerthCondition();
        lockConditionManual.setMapCode(subTask.getMapCode());
        lockConditionManual.setOperateAreaCode(InspurBizConstants.BizTypeConstants.AGINGAREAMANUAL);
        lockConditionManual.setLockSource(subTask.getSubTaskNum());

        if (InspurBizConstants.AgingAreaPriorityProp.AUTO_FIRST.equals(subTask.getSubTaskBizProp())) {
            //自动区优先
            conditionList.add(lockConditionAuto);
            conditionList.add(lockConditionManual);
        } else {
            //手动区优先
            conditionList.add(lockConditionManual);
            conditionList.add(lockConditionAuto);
        }

        //锁定该任务点
        Result result = mapResouceService.lockEmptyStorageByBizTypeList(conditionList);
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
