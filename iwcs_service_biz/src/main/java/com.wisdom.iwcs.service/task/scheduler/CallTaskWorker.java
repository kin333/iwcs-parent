package com.wisdom.iwcs.service.task.scheduler;

import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.task.CreateMainTaskRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.service.task.impl.TaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.*;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.OperateAreaCodeConstants.LINEAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.OperateAreaCodeConstants.QUAINSPAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.PodInStockConstants.EMPTY_POD;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.PodInStockConstants.NOT_EMPTY_POD;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.LINEINSERTINGAREACALL;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.QUAINSPAREACALL;

/**
 * 长春呼叫任务 的自动化任务
 */

public class CallTaskWorker implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(CallTaskWorker.class);

    private String mainTaskType;

    public CallTaskWorker() {
    }

    public CallTaskWorker(String mainTaskType) {
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
        if (LINEINSERTINGAREACALL.equals(mainTaskType)){
            //起点
            lockMapBerthCondition.setOperateAreaCode(LINEAREA);
            lockMapBerthCondition.setBizType(LINECACHEAREA);
            lockMapBerthCondition.setInStock(String.valueOf(EMPTY_POD));
            //终点
            eleCacheMapBerthCondition.setBizType(LINEWORKAREA);
            eleCacheMapBerthCondition.setOperateAreaCode(LINEAREA);
        }else if (QUAINSPAREACALL.equals(mainTaskType)){
            lockMapBerthCondition.setBizType(QUAINSPCACHEAREA);
            lockMapBerthCondition.setOperateAreaCode(QUAINSPAREA);
            lockMapBerthCondition.setInStock(String.valueOf(NOT_EMPTY_POD));
            eleCacheMapBerthCondition.setOperateAreaCode(QUAINSPAREA);
        }

        BaseMapBerthMapper baseMapBerthMapper = (BaseMapBerthMapper) AppContext.getBean("baseMapBerthMapper");
        TaskCreateService taskCreateService = (TaskCreateService) AppContext.getBean("taskCreateService");
        List<BaseMapBerth> mapBerthList = baseMapBerthMapper.selectNotEmptyStorageOfInspectionArea(lockMapBerthCondition);
        List<BaseMapBerth> berthList = baseMapBerthMapper.selectEmptyStorage(eleCacheMapBerthCondition);
        //创建主任务
        if(mapBerthList.size() > 0 && berthList.size() > 0) {
            int random = (int)((Math.random()*9+1)*100000);
            List<String> staticViaPaths = new ArrayList<>();
            staticViaPaths.add(berthList.get(0).getPointAlias());
            CreateMainTaskRequest createMainTaskRequest = new CreateMainTaskRequest();
            createMainTaskRequest.setReqCode("T620000019890");
            createMainTaskRequest.setTaskCode(String.valueOf(random));
            createMainTaskRequest.setPriority(1);
            createMainTaskRequest.setMainTaskType(mainTaskType);
            createMainTaskRequest.setStaticViaPaths(staticViaPaths);
            taskCreateService.publicTaskCreate(createMainTaskRequest);
        }

    }
}
