package com.wisdom.iwcs.service.task.wcsSimulator;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.YZConstants;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.TaskCreateRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.elevator.EleControlTaskMapper;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.PAGECACHEAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.EleControlTaskWorkType.ELE_DOWN;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.ELVBUFTOPACKBUF;

/**
 * 电梯自动下楼任务
 */
@Component
public class EleAutoDownWorker implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(EleAutoDownWorker.class);
    @Autowired
    EleControlTaskMapper eleControlTaskMapper;
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private ITaskCreateService taskCreateService;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    autoDown();
                    this.wait(30 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void autoDown() {
        logger.info("开始创建电梯下楼任务");
        //检查是否有未完成的电梯任务
        long count = eleControlTaskMapper.countUnEndTask();
        if (count > 0) {
            return;
        }

        TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
        taskCreateRequest.setTaskTypeCode(ELVBUFTOPACKBUF);
        taskCreateRequest.setSourceFloor("1");
        taskCreateRequest.setEleWorkType(ELE_DOWN);
        String mapCode;
//        if (System.currentTimeMillis() % 2 == 0) {
//            mapCode = "AB";
//        } else {
//            mapCode = "DD";
//        }
//
//        if ("AB".equals(mapCode)) {
//        } else {
//            taskCreateRequest.setTargetPointAlias("A301");
//        }
        mapCode = "AB";
        taskCreateRequest.setTargetPointAlias("A201");
        boolean result = checkMapCodeEleCache(mapCode, taskCreateRequest);

        if (!result) {
            mapCode = "DD";
            taskCreateRequest.setTargetPointAlias("A301");
            checkMapCodeEleCache(mapCode, taskCreateRequest);
        }

    }

    private boolean checkMapCodeEleCache(String mapCode, TaskCreateRequest taskCreateRequest) {
        //检查指定楼层对应的空货架是否有货架
        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectByBizTye(PAGECACHEAREA + mapCode).get(0);

        if (StringUtils.isEmpty(baseMapBerth.getPodCode()) && YZConstants.UNLOCK.equals(baseMapBerth.getInLock())) {
            List<BaseMapBerth> list = baseMapBerthMapper.selectHavePodByBizType(mapCode, InspurBizConstants.BizTypeConstants.ELEVATORCACHEAREA);
            if (list != null && list.size() > 0) {
                taskCreateRequest.setPodCode(list.get(0).getPodCode());
                logger.info("开始生成电梯下楼任务");
                taskCreateService.creatTask(taskCreateRequest);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}