package com.wisdom.iwcs.service.task.wcsSimulator;

import com.google.common.base.Strings;
import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.YZConstants;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.task.TaskCreateRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import com.wisdom.iwcs.service.task.scheduler.WcsTaskScheduler;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.ELEVATORCACHEAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.QUAINSPWORKAREA;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.PLTOAGING;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.QUAINSPTOELVBUF;

@Service
public class QuaAutoToAgingService {
    private final Logger logger = LoggerFactory.getLogger(QuaAutoToAgingService.class);
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
            taskCreateRequest.setTaskTypeCode(QUAINSPTOELVBUF);
            taskCreateRequest.setPodCode(baseMapBerthList.get(0).getPodCode());
            taskCreateRequest.setStartPointAlias(baseMapBerthList.get(0).getPointAlias());
//            taskCreateRequest.setSubTaskBizProp(InspurBizConstants.AgingAreaPriorityProp.AUTO_FIRST);
            result = taskCreateService.creatTask(taskCreateRequest);

        }
        return result;

    }

    /**
     * 自动生成  线体工作台补充空货架,线体去老化区  的主任务
     * @param mapCode
     * @return
     */
    public void workLineScheduler(String mapCode) {
        List<String> workLineList = baseMapBerthMapper.selectAliasByMapCode(mapCode);
        if (workLineList == null || workLineList.size() <= 0) {
            throw new BusinessException("您输入的地图编号不存在");
        }

        for (String name : workLineList) {
            if (name == null) {
                continue;
            }
            BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByPointAlias(name);
            if (StringUtils.isBlank(baseMapBerth.getPodCode()) && YZConstants.UNLOCK.equals(baseMapBerth.getInLock())) {
                //调用生成 工作台点位呼叫空货架 的任务
                TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
                taskCreateRequest.setTaskTypeCode(TaskConstants.taskCodeType.PLAUTOWBCALLPOD);
                taskCreateRequest.setTargetPointAlias(baseMapBerth.getPointAlias());
                taskCreateService.creatTask(taskCreateRequest);
                logger.info("{}工作台点位呼叫空货架主任务生成成功", baseMapBerth.getPointAlias());
            } else if (StringUtils.isNotBlank(baseMapBerth.getPodCode()) && YZConstants.UNLOCK.equals(baseMapBerth.getInLock())){
                BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(baseMapBerth.getPodCode());
                if (YZConstants.UNLOCK.equals(basePodDetail.getInLock())) {
                    //调用生成 产线去老化区搬运 的任务
                    TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
                    taskCreateRequest.setTaskTypeCode(TaskConstants.taskCodeType.PLTOAGING);
                    taskCreateRequest.setStartPointAlias(baseMapBerth.getPointAlias());
                    taskCreateRequest.setSubTaskBizProp(InspurBizConstants.AgingAreaPriorityProp.AUTO_FIRST);
                    taskCreateService.creatTask(taskCreateRequest);
                    logger.info("{}工作台去老化区搬运主任务生成成功", baseMapBerth.getPointAlias());
                }
            }

        }
    }

}
