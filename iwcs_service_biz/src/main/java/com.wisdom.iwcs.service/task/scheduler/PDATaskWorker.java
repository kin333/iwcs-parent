package com.wisdom.iwcs.service.task.scheduler;

import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.upstream.mes.CreateTaskRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.service.task.impl.TaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.QUAINSPCACHEAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.OperateAreaCodeConstants.AGINGREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.OperateAreaCodeConstants.QUAINSPAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.PodInStockConstants.NOT_EMPTY_POD;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.AGINGTOINSPCACHE;

/**
 * 长春PDA 的自动化任务
 */
public class PDATaskWorker implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(PDATaskWorker.class);

    private String mainTaskType;

    public PDATaskWorker() {
    }

    public PDATaskWorker(String mainTaskType) {
        this.mainTaskType = mainTaskType;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    autoTask();
                    this.wait(60 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        this.wait(60 * 1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }

    }

    private void autoTask() {
        LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
        //筛选目标点
        LockMapBerthCondition eleCacheMapBerthCondition = new LockMapBerthCondition();

        //根据主任务类型判断
        if (AGINGTOINSPCACHE.equals(mainTaskType)){
            lockMapBerthCondition.setOperateAreaCode(AGINGREA);
            lockMapBerthCondition.setInStock(String.valueOf(NOT_EMPTY_POD));
            eleCacheMapBerthCondition.setOperateAreaCode(QUAINSPAREA);
            eleCacheMapBerthCondition.setBizType(QUAINSPCACHEAREA);
        }

        BaseMapBerthMapper baseMapBerthMapper = (BaseMapBerthMapper) AppContext.getBean("baseMapBerthMapper");
        TaskCreateService taskCreateService = (TaskCreateService) AppContext.getBean("taskCreateService");
        List<BaseMapBerth> mapBerthList = baseMapBerthMapper.selectNotEmptyStorageOfInspectionArea(lockMapBerthCondition);
        List<BaseMapBerth> berthList = baseMapBerthMapper.selectEmptyStorage(eleCacheMapBerthCondition);
        //创建主任务
        if(mapBerthList.size() > 0 && berthList.size() > 0) {
            CreateTaskRequest createTaskRequest = new CreateTaskRequest();
            createTaskRequest.setTaskType(mainTaskType);
            createTaskRequest.setPodCode(mapBerthList.get(0).getPodCode());
            String reqCode = "T6000001970";
            taskCreateService.pToPTaskCreate(createTaskRequest);
        }

    }
}
