package com.wisdom.iwcs.service.task.wcsSimulator;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.base.dto.LockPodCondition;
import com.wisdom.iwcs.domain.task.TaskCreateRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.AGINGTOQUAINSP;

@Service
public class QuaAutoCallPodService {
    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private ITaskCreateService taskCreateService;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;

    public Result checkEmptyQua(String mapCode) {

        Result result = new Result();
        //首先判断检验点是否需要补充
        LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
        lockMapBerthCondition.setOperateAreaCode(InspurBizConstants.OperateAreaCodeConstants.QUAINSPAREA);
        lockMapBerthCondition.setMapCode(mapCode);
        List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.selectEmptyStorage(lockMapBerthCondition);
        //从老化区找一个符合条件货架
        LockPodCondition lockPodCondition = new LockPodCondition();
        lockPodCondition.setMapCode(mapCode);
        lockPodCondition.setOperateAreaCode(InspurBizConstants.OperateAreaCodeConstants.AGINGREA);
        List<BasePodDetail> basePodDetails = basePodDetailMapper.selectByLockPodConfigtion(lockPodCondition);

        //有空的检验点和老化区有货架才可以产生任务
        if(baseMapBerthList.size() > 0 && basePodDetails.size() > 0) {
            TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
            taskCreateRequest.setTaskTypeCode(AGINGTOQUAINSP);
            taskCreateRequest.setPodCode(basePodDetails.get(0).getPodCode());
            result = taskCreateService.creatTask(taskCreateRequest);
        }

        return result;

    }
}
