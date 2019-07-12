package com.wisdom.iwcs.service.task.wcsSimulator;

import com.wisdom.iwcs.common.utils.InspurBizConstants;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.dto.LockMapBerthCondition;
import com.wisdom.iwcs.domain.task.TaskCreateRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.QUAINSPWORKAREA;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.PLTOAGING;

@Service
public class QuaAutoToAgingWorker implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(QuaAutoToAgingWorker.class);

    @Autowired
    private BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private ITaskCreateService taskCreateService;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;


    /**
     * 检查检验区的工作点是否有货架，有货架的或创建任务
     */
    public void checkQuaHavePodThenToAging() {

        LockMapBerthCondition lockMapBerthCondition = new LockMapBerthCondition();
        lockMapBerthCondition.setMapCode("AB");
        lockMapBerthCondition.setBizType(QUAINSPWORKAREA);
        lockMapBerthCondition.setOperateAreaCode(InspurBizConstants.OperateAreaCodeConstants.QUAINSPAREA);
        List<BaseMapBerth> baseMapBerthList = baseMapBerthMapper.selectNotEmptyStorageOfInspectionArea(lockMapBerthCondition);

        if(baseMapBerthList.size() > 0) {
            TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
            taskCreateRequest.setTaskTypeCode(PLTOAGING);
            taskCreateRequest.setStartPointAlias(baseMapBerthList.get(0).getPointAlias());
            taskCreateRequest.setSubTaskBizProp(InspurBizConstants.AgingAreaPriorityProp.AUTO_FIRST);
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
                this.checkQuaHavePodThenToAging();
                synchronized (this) {
                    logger.error("创建检验区到老化区调度器线程主动随眠60*1000*2");
                    this.wait(60 * 1000 * 3);
                }

            } catch (Exception e) {
                logger.error("创建检验区到老化区主任务调度器线程尝试休眠失败！");
                e.printStackTrace();
            }
        }
    }
}
