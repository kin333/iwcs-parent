package com.wisdom.iwcs.service.task.wcsSimulator;

import com.wisdom.iwcs.common.utils.BudgetMakingStatus;
import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.TaskCreateRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.elevator.EleControlTaskMapper;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.PAGEWORKAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.EleControlTaskWorkType.ELE_DOWN;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.EleControlTaskWorkType.ELE_UP;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.ELVBUFTOPACKBUF;

/**
 * 电梯上楼任务自动生成器
 */
@Component
public class EleAutoUpWorker implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(EleAutoUpWorker.class);
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private ITaskCreateService taskCreateService;
    @Autowired
    EleControlTaskMapper eleControlTaskMapper;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    this.wait(15 * 1000);
                    eleAutoUp();
                    this.wait(15 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        this.wait(30 * 1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    public void eleAutoUp() {
        logger.info("开始创建电梯上楼任务");
        //检查是否有未完成的电梯任务
        long count = eleControlTaskMapper.countUnEndTask();
        if (count > 0) {
            return;
        }

        TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
        taskCreateRequest.setTaskTypeCode(ELVBUFTOPACKBUF);
        taskCreateRequest.setEleWorkType(ELE_UP);

        List<BaseMapBerth> list = baseMapBerthMapper.selectHavePodByBizType("CC", PAGEWORKAREA);
        if (list == null || list.size() <= 0) {
            return;
        }
        String podCode = list.get(0).getPodCode();
        String mapCode;
        if ("1002".equals(podCode.substring(0, 4))) {
            taskCreateRequest.setSourceFloor("2");
        } else if ("1003".equals(podCode.substring(0, 4))) {
            taskCreateRequest.setSourceFloor("3");
        } else{
            throw new BusinessException("货架号异常:" + podCode);
        }
        taskCreateRequest.setPodCode(podCode);
        //查找老化区空货架
//        BaseMapBerth baseMapBerth = baseMapBerthMapper.selectEmptyPosByOperateAreaCode(mapCode, InspurBizConstants.OperateAreaCodeConstants.AGINGREA);

//        if (baseMapBerth == null) {
//            return;
//        }

        logger.info("开始生成电梯上楼任务");
        taskCreateService.creatTask(taskCreateRequest);


    }
}
