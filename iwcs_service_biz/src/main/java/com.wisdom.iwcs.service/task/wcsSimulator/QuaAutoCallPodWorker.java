package com.wisdom.iwcs.service.task.wcsSimulator;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.base.dto.LockPodCondition;
import com.wisdom.iwcs.domain.task.TaskCreateRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.QUAINSPWORKAREA;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.AGINGTOQUAINSP;

@Service
public class QuaAutoCallPodWorker implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(QuaAutoCallPodWorker.class);
    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private ITaskCreateService taskCreateService;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;


    public void checkEmptyQua() {
        //首先判断检验点是否需要补充
        LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
        lockMapBerthCondition.setOperateAreaCode(InspurBizConstants.OperateAreaCodeConstants.QUAINSPAREA);
        lockMapBerthCondition.setMapCode("AB");
        List<BaseMapBerth>baseMapBerthList = baseMapBerthMapper.selectEmptyStorage(lockMapBerthCondition);
        //从老化区找一个符合条件货架
        LockPodCondition lockPodCondition = new LockPodCondition();
        lockPodCondition.setMapCode("AB");
        lockPodCondition.setOperateAreaCode(InspurBizConstants.OperateAreaCodeConstants.AGINGREA);
        List<BasePodDetail> basePodDetails = basePodDetailMapper.selectByLockPodConfigtion(lockPodCondition);

        //有空的检验点和老化区有货架才可以产生任务
        if(baseMapBerthList.size() > 0 && basePodDetails.size() > 0) {
            TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
            taskCreateRequest.setTaskTypeCode(AGINGTOQUAINSP);
            taskCreateRequest.setPodCode(basePodDetails.get(0).getPodCode());
            Result result = taskCreateService.creatTask(taskCreateRequest);
            if(result.getReturnCode() != 200) {
                logger.error(result.getReturnMsg());
            }
        }
    }

    @Override
    public void run() {
        while (true) {

            try {
                //检验八个检验点和两个检验点缓冲区是否有空点，如果有创建一个老化区去检验区的任务
                this.checkEmptyQua();

                synchronized (this) {
                    logger.error("创建老化区货架到检验区调度器线程主动随眠60*1000*2");
                    this.wait(60 * 1000 * 1);
                }

            } catch (InterruptedException e) {
                logger.error("创建老化区货架到检验区主任务调度器线程尝试休眠失败！");
                e.printStackTrace();
            }
        }
    }
}
