package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.TaskLockSourceExecption;
import com.wisdom.iwcs.domain.base.dto.LockStorageDto;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.SubTaskConditionMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.base.baseImpl.BaseMapBerthService;
import com.wisdom.iwcs.service.task.impl.MapResouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 产线工作台空闲处理类
 */
@Service
public class PlWbAvaliableConHandler implements IConditionHandler {

    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private BaseMapBerthService baseMapBerthService;
    @Autowired
    private SubTaskConditionMapper subTaskConditionsMapper;
    @Autowired
    private MapResouceService mapResouceService;


    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        String subTaskNum = subTaskCondition.getSubTaskNum();
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);
        LockStorageDto lockStorageDto = new LockStorageDto();
        lockStorageDto.setBerCode(subTask.getEndBercode());
        lockStorageDto.setMapCode(subTask.getMapCode());
        lockStorageDto.setLockSource(subTask.getSubTaskNum());
        Result result = mapResouceService.lockMapBerth(lockStorageDto);
        if (200 != result.getReturnCode()) {
            //TODO 细化任务资源异常

            throw new TaskLockSourceExecption(result.getReturnMsg());
        }
        SubTaskCondition tmpCon = new SubTaskCondition();
        tmpCon.setId(subTaskCondition.getId());
        tmpCon.setConditionMetStatus("1");
        subTaskConditionsMapper.updateByPrimaryKeySelective(tmpCon);
        return true;
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
        return true;
    }
}
