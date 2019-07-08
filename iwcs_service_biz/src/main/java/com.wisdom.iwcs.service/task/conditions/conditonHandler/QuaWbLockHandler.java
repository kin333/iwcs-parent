package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.TaskLockSourceExecption;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.SubTaskConditionMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.impl.MapResouceService;
import org.springframework.beans.factory.annotation.Autowired;

public class QuaWbLockHandler implements IConditionHandler {

    @Autowired
    private MapResouceService mapResouceService;
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private SubTaskConditionMapper subTaskConditionsMapper;

    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        String subTaskNum = subTaskCondition.getSubTaskNum();
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);
        //从检验点工作区找一个空闲点锁住并更新子任务单终点坐标
        LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
        lockMapBerthCondition.setLockSource(subTask.getSubTaskNum());
        lockMapBerthCondition.setMapCode(subTask.getMapCode());
        lockMapBerthCondition.setPodCode(subTask.getPodCode());
        Result result = mapResouceService.selectQuaEmptyStorage(lockMapBerthCondition);
        if (200 != result.getReturnCode()) {
            //TODO 细化任务资源异常
            throw new TaskLockSourceExecption(result.getReturnMsg());
        }

        SubTaskCondition tmpCon = new SubTaskCondition();
        tmpCon.setId(subTaskCondition.getId());
        tmpCon.setConditionMetStatus("1");
        subTaskConditionsMapper.updateByPrimaryKeySelective(tmpCon);
        return false;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        String subTaskNum = subTaskCondition.getSubTaskNum();
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);

        LockStorageDto lockStorageDto = new LockStorageDto();
        lockStorageDto.setBerCode(subTask.getEndBercode());
        lockStorageDto.setMapCode(subTask.getMapCode());
        lockStorageDto.setLockSource(subTask.getSubTaskNum());
        Result result = mapResouceService.unlockMapBerth(lockStorageDto);
        if (200 != result.getReturnCode()) {
            //TODO 细化任务资源异常
            throw new TaskLockSourceExecption(result.getReturnMsg());
        }

        SubTaskCondition tmpCon = new SubTaskCondition();
        tmpCon.setId(subTaskCondition.getId());
        tmpCon.setConditionMetStatus("0");
        subTaskConditionsMapper.updateByPrimaryKeySelective(tmpCon);
        return false;
    }
}
