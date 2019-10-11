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

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.AGINGCACHEAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.LINECACHEAREA;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.PLBUFSUPPLY;

/**
 * 线体缓存区补充空货架 的自动化任务
 */
@Component
public class PlBufSupplyWorker implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(PlBufSupplyWorker.class);

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
        logger.info("开始创建 线体缓存区补充空货架 的任务");
        List<BaseMapBerth> mapBerthList = baseMapBerthMapper.selectByBizTye(LINECACHEAREA);
        Preconditions.checkBusinessError(mapBerthList.size() <= 0, "基础数据异常: 找不到线体缓存区");
        List<BaseMapBerth> berthList = baseMapBerthMapper.selectByBizTye(AGINGCACHEAREA);
        Preconditions.checkBusinessError(berthList.size() <= 0, "基础数据异常: 找不到老化缓存区");
        String srcWb = "";
        String podCode = "";
        //查找线体缓存区的一个空位置
        for (BaseMapBerth baseMapBerth:mapBerthList){
            if (StringUtils.isEmpty(baseMapBerth.getPodCode()) && YZConstants.UNLOCK.equals(baseMapBerth.getInLock()) && StringUtils.isEmpty(baseMapBerth.getLockSource())){
                srcWb = baseMapBerth.getPointAlias();
                break;
            }
        }

        //查找老化缓存区的一个空货架
        for (BaseMapBerth baseMapBerth:berthList){
            if (StringUtils.isNotEmpty(baseMapBerth.getPodCode()) && YZConstants.UNLOCK.equals(baseMapBerth.getInLock()) && StringUtils.isEmpty(baseMapBerth.getLockSource()) ){
                BasePodDetail basePodDetail = basePodDetailMapper.selectByPodCode(baseMapBerth.getPodCode());
                if (YZConstants.UNLOCK.equals(basePodDetail.getInStock())  && YZConstants.UNLOCK.equals(basePodDetail.getInLock()) && StringUtils.isEmpty(basePodDetail.getLockSource()) ){
                    podCode = basePodDetail.getPodCode();
                    break;
                }
            }
        }

        if (StringUtils.isNotEmpty(srcWb) && StringUtils.isNotEmpty(podCode)) {
            CreateTaskRequest createTaskRequest = new CreateTaskRequest();
            createTaskRequest.setTaskType(PLBUFSUPPLY);
            //createTaskRequest.setSrcWb(srcWb);
            createTaskRequest.setTaskPri("normal");
            createTaskRequest.setTaskCode("SKUNO20180331");
            String reqCode = "T6000001970";
            taskCreateService.pToPHandlingTask(createTaskRequest, reqCode);
        }
    }
}
