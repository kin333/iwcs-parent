package com.wisdom.iwcs.service.task.scheduler;

import com.wisdom.iwcs.common.utils.YZConstants;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.TaskCreateRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.PAGECACHEAREA;
import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.PAGEWORKAREA;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.PACKWBCALLPOD;

/**
 * 包装体缓存区去包装体的自动化任务
 */
@Component
public class PackWlCacheWorker implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(PackWlCacheWorker.class);

    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    private ITaskCreateService taskCreateService;
    /**
     * 计数器,用于包装体二楼缓存区和三楼缓存区轮流前往包装区
     */
    private int count = 0;

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
        logger.info("开始创建去包装区的任务");
        List<BaseMapBerth> mapBerthList = baseMapBerthMapper.selectByBizTye(PAGEWORKAREA);
        Preconditions.checkBusinessError(mapBerthList.size() <= 0, "基础数据异常: 找不到包装区");

        //如果目标点有货架,则跳过
        BaseMapBerth baseMapBerth = mapBerthList.get(0);
        if (StringUtils.isNotEmpty(baseMapBerth.getPodCode()) || YZConstants.LOCK.equals(baseMapBerth.getInLock())) {
            return;
        }
        TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
        taskCreateRequest.setTaskTypeCode(PACKWBCALLPOD);

        //查找起点(包装体缓存区)
        List<BaseMapBerth> berthList = baseMapBerthMapper.selectLikeBizTye(PAGECACHEAREA);

        Preconditions.checkBusinessError(berthList.size() <= 0, "无包装体缓存区");
        BaseMapBerth packageCahce;
        if (berthList.size() <= 1) {
            packageCahce = berthList.get(0);
        } else {
            packageCahce = berthList.get(count++ % 2);
        }

        if (StringUtils.isNotBlank(packageCahce.getPodCode())) {
            taskCreateRequest.setStartPointAlias(packageCahce.getPointAlias());
            taskCreateService.creatTask(taskCreateRequest);
        }
    }
}
