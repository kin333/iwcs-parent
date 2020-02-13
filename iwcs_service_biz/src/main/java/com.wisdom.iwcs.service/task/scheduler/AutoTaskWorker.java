package com.wisdom.iwcs.service.task.scheduler;

import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.task.CreateMainTaskRequest;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.service.task.impl.TaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.LINECACHEAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.OperateAreaCodeConstants.AGINGREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.OperateAreaCodeConstants.LINEAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.PodInStockConstants.EMPTY_POD;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.PRODCACHEAUTOSUPPLY;

/**
 * 长春自动补充任务 的自动化任务
 */
public class AutoTaskWorker implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(AutoTaskWorker.class);

    private String mainTaskType;

    public AutoTaskWorker() {
    }

    public AutoTaskWorker(String mainTaskType) {
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
        if (PRODCACHEAUTOSUPPLY.equals(mainTaskType)){
            //起点
            lockMapBerthCondition.setOperateAreaCode(AGINGREA);
            lockMapBerthCondition.setInStock(String.valueOf(EMPTY_POD));
            //终点
            eleCacheMapBerthCondition.setOperateAreaCode(LINEAREA);
            eleCacheMapBerthCondition.setBizType(LINECACHEAREA);
        }

        BaseMapBerthMapper baseMapBerthMapper = (BaseMapBerthMapper) AppContext.getBean("baseMapBerthMapper");
        TaskCreateService taskCreateService = (TaskCreateService) AppContext.getBean("taskCreateService");
        List<BaseMapBerth> mapBerthList = baseMapBerthMapper.selectNotEmptyStorageOfInspectionArea(lockMapBerthCondition);
        List<BaseMapBerth> berthList = baseMapBerthMapper.selectEmptyStorage(eleCacheMapBerthCondition);
        //查询该自动补充任务是否有未完成的
        MainTaskMapper mainTaskMapper = (MainTaskMapper) AppContext.getBean("mainTaskMapper");
        List<MainTask> mainTaskList =  mainTaskMapper.selectByMainTaskTypeCode(mainTaskType);

        //创建主任务
        if(mapBerthList.size() > 0 && berthList.size() > 0 && mainTaskList.size() == 0) {
            int random = (int)((Math.random()*9+1)*100000);
            CreateMainTaskRequest createMainTaskRequest = new CreateMainTaskRequest();
            createMainTaskRequest.setReqCode("T620000019890");
            createMainTaskRequest.setTaskCode(String.valueOf(random));
            createMainTaskRequest.setPriority(1);
            createMainTaskRequest.setMainTaskType(mainTaskType);
            taskCreateService.publicTaskCreate(createMainTaskRequest);
        }

    }
}
