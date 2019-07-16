package com.wisdom.iwcs.service.task.wcsSimulator;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.task.TaskCreateRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.QUAINSPWORKAREA;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.PLTOAGING;

@Service
public class QuaAutoToAgingService {
    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private ITaskCreateService taskCreateService;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;


    public Result checkQuaHavePodThenToAging(String mapCode) {

        Result result = new Result();
        LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
        lockMapBerthCondition.setMapCode(mapCode);
        lockMapBerthCondition.setBizType(QUAINSPWORKAREA);
        lockMapBerthCondition.setOperateAreaCode(InspurBizConstants.OperateAreaCodeConstants.QUAINSPAREA);
        List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.selectNotEmptyStorageOfInspectionArea(lockMapBerthCondition);

        if(baseMapBerthList.size() > 0) {
            TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
            taskCreateRequest.setTaskTypeCode(PLTOAGING);
            taskCreateRequest.setStartPointAlias(baseMapBerthList.get(0).getPointAlias());
            taskCreateRequest.setSubTaskBizProp(InspurBizConstants.AgingAreaPriorityProp.AUTO_FIRST);
            result = taskCreateService.creatTask(taskCreateRequest);

        }
        return result;

    }

}
