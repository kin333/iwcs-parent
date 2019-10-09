package com.wisdom.iwcs.service.task.scheduler;

import com.wisdom.iwcs.common.utils.YZConstants;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.upstream.mes.CreateTaskRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.LINEWORKAREA;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.PLTOWOKPW;

/**
 * 产线呼叫搬离货架 的自动化任务
 */
@Component
public class PlToWokpwWorker implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(PlToWokpwWorker.class);

    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private ITaskCreateService taskCreateService;
    @Autowired
    private BasePodDetailMapper basePodDetailMapper;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                try {
                    autoTask();
                    this.wait(30 * 1000);
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

    private void autoTask() {
        logger.info("开始创建 产线呼叫搬离货架 的任务");
        List<BaseMapBerth> mapBerthList = baseMapBerthMapper.selectByBizTye(LINEWORKAREA);
        Preconditions.checkBusinessError(mapBerthList.size() <= 0, "基础数据异常: 找不到线体工作区");
        String srcWb = "";

        //查找线体工作区的一个 满货架
        for (BaseMapBerth baseMapBerth:mapBerthList){
            if (StringUtils.isNotEmpty(baseMapBerth.getPodCode())){
                BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(baseMapBerth.getPodCode());
                if (YZConstants.UNLOCK.equals(basePodDetail.getInStock())){
                    srcWb = baseMapBerth.getPointAlias();
                    break;
                }

            }
        }

        CreateTaskRequest createTaskRequest = new CreateTaskRequest();
        createTaskRequest.setTaskCode(PLTOWOKPW);
        createTaskRequest.setSrcWb(srcWb);
        createTaskRequest.setTaskPri("normal");
        createTaskRequest.setTaskCode("SKUNO20180331");
        String reqCode = "T6000001970";
        taskCreateService.pToPHandlingTask(createTaskRequest,reqCode);
    }
}
