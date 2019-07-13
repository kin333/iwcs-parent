package com.wisdom.iwcs.service.task.scheduler;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.common.utils.YZConstants;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.task.TaskCreateRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成  线体工作台补充空货架,线体去老化区  的主任务
 */
@Component
public class WorkLineScheduler implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(WcsTaskScheduler.class);
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    BasePodDetailMapper basePodDetailMapper;
    @Autowired
    private ITaskCreateService taskCreateService;

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    this.todo();
                    logger.debug("产线工作台主任务生成器线程主动睡眠 2 min");
                    this.wait(60 * 1000 * 2);
                }

            } catch (Exception e) {
                logger.error("产线工作台主任务生成器线程尝试休眠失败！");
                e.printStackTrace();
            }
        }
    }

    private void todo() {
        //查询三个产线工作台
        List<String> workLineList = new ArrayList<>();
        workLineList.add("WL3-1");
        workLineList.add("WL3-2");
        workLineList.add("WL3-3");
        workLineList.add("WL1");
        workLineList.add("WL2");
        workLineList.add("WL3");

        for (String name : workLineList) {
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
