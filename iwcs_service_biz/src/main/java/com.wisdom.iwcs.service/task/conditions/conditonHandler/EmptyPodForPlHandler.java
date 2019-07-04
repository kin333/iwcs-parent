package com.wisdom.iwcs.service.task.conditions.conditonHandler;

import com.wisdom.iwcs.common.utils.CompanyFinancialStatusEnum;
import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.base.dto.LockPodCondition;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.impl.MapResouceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * 已选中补充货架并锁定(优先空货架缓存点,其次老化区空货架)
 * 查找空货架
 * @author han
 */
public class EmptyPodForPlHandler implements IConditionHandler{
    @Autowired
    MapResouceService mapResouceService;
    @Autowired
    SubTaskMapper subTaskMapper;


    @Override
    public boolean handleCondition(SubTaskCondition subTaskCondition) {
        Long subTaskId = subTaskCondition.getId();
        SubTask subTask = subTaskMapper.selectByPrimaryKey(subTaskId);

        List<LockPodCondition> lockPodConditions = new ArrayList<>();
        //优先条件
        LockPodCondition lockPodCondition = new LockPodCondition();
        //无货货架
        lockPodCondition.setInStock("0");
        lockPodCondition.setMapCode(subTask.getMapCode());
        lockPodCondition.setOperateAreaCode(InspurBizConstants.BizTypeConstants.LINECACHEAREA);
        lockPodCondition.setLockSource(subTask.getSubTaskNum());
        lockPodConditions.add(lockPodCondition);
        //次优先条件
        lockPodCondition = new LockPodCondition();
        lockPodCondition.setInStock("0");
        lockPodCondition.setMapCode(subTask.getMapCode());
        lockPodCondition.setOperateAreaCode(InspurBizConstants.OperateAreaCodeConstants.AGINGREA);
        lockPodCondition.setLockSource(subTask.getSubTaskNum());
        lockPodConditions.add(lockPodCondition);

        //锁定货架
        Result result = mapResouceService.lockPodByCondition(lockPodConditions);
        if (result.getReturnCode() != HttpStatus.OK.value()) {
            return false;
        }
        BasePodDetail basePodDetail = (BasePodDetail)result.getReturnData();
        //更新子任务单中的货架号
        subTaskMapper.updatePodCodeBySubTaskCode(subTask.getSubTaskNum(), basePodDetail.getPodCode());
        return true;
    }

    @Override
    public boolean rollbackCondition(SubTaskCondition subTaskCondition) {
        //还原子任务单中的货架号
        subTaskMapper.updatePodCodeBySubTaskCode(subTaskCondition.getSubTaskNum(), "");
        //还原货架状态
        return mapResouceService.unlockPod(subTaskCondition.getSubTaskNum());
    }
}
