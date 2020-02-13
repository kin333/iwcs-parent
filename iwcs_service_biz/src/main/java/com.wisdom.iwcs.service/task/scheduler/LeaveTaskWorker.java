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

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.LINEWORKAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.OperateAreaCodeConstants.*;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.LINEINSERTINGAREALEAVE;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.QUAINSPAREALEAVE;

/**
 * 长春搬离任务 的自动化任务
 */
public class LeaveTaskWorker implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(LeaveTaskWorker.class);

    private String mainTaskType;

    public LeaveTaskWorker() {
    }

    public LeaveTaskWorker(String mainTaskType) {
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
        if (LINEINSERTINGAREALEAVE.equals(mainTaskType)){
            //起点
            lockMapBerthCondition.setBizType(LINEWORKAREA);
            lockMapBerthCondition.setOperateAreaCode(LINEAREA);
            //终点
            eleCacheMapBerthCondition.setOperateAreaCode(AGINGREA);
        }else if (QUAINSPAREALEAVE.equals(mainTaskType)){
            lockMapBerthCondition.setOperateAreaCode(QUAINSPAREA);
            eleCacheMapBerthCondition.setOperateAreaCode(AGINGREA);
        }

        BaseMapBerthMapper baseMapBerthMapper = (BaseMapBerthMapper) AppContext.getBean("baseMapBerthMapper");
        TaskCreateService taskCreateService = (TaskCreateService) AppContext.getBean("taskCreateService");
        List<BaseMapBerth> mapBerthList = baseMapBerthMapper.selectNotEmptyStorageOfInspectionArea(lockMapBerthCondition);
        List<BaseMapBerth> berthList = baseMapBerthMapper.selectEmptyStorage(eleCacheMapBerthCondition);
        //创建主任务
        if(mapBerthList.size() > 0 && berthList.size() > 0) {
            int random = (int)((Math.random()*9+1)*100000);
            List<String> staticViaPaths = new ArrayList<>();
            staticViaPaths.add(mapBerthList.get(0).getPointAlias());
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
