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

import static com.wisdom.iwcs.common.utils.InspurBizConstants.BizTypeConstants.PAGEWORKAREA;
import static com.wisdom.iwcs.common.utils.TaskConstants.pointAlias.PACK_CACHE_THREE;
import static com.wisdom.iwcs.common.utils.TaskConstants.pointAlias.PACK_CACHE_TWO;
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
        if (!StringUtils.isEmpty(baseMapBerth.getPodCode()) && YZConstants.LOCK.equals(baseMapBerth.getInLock())) {
            return;
        }
        TaskCreateRequest taskCreateRequest = new TaskCreateRequest();
        taskCreateRequest.setTaskTypeCode(PACKWBCALLPOD);

        if (count % 2 == 0) {
            //查找二楼包装体缓存区是否有货架
            BaseMapBerth packageCahceTwo = baseMapBerthMapper.selectByPointAlias(PACK_CACHE_TWO);
            if (StringUtils.isNotBlank(packageCahceTwo.getPodCode())) {
                taskCreateRequest.setStartPointAlias(PACK_CACHE_TWO);
                taskCreateService.creatTask(taskCreateRequest);
                count++;
                return;
            }
            //查找三楼包装体缓存区是否有货架
            BaseMapBerth packageCahceThree = baseMapBerthMapper.selectByPointAlias(PACK_CACHE_THREE);
            if (StringUtils.isNotBlank(packageCahceThree.getPodCode())) {
                taskCreateRequest.setStartPointAlias(PACK_CACHE_THREE);
                taskCreateService.creatTask(taskCreateRequest);
            }
        } else {
            //查找三楼包装体缓存区是否有货架
            BaseMapBerth packageCahceThree = baseMapBerthMapper.selectByPointAlias(PACK_CACHE_THREE);
            if (StringUtils.isNotBlank(packageCahceThree.getPodCode())) {
                count++;
                taskCreateRequest.setStartPointAlias(PACK_CACHE_THREE);
                taskCreateService.creatTask(taskCreateRequest);
                return;
            }
            //查找二楼包装体缓存区是否有货架
            BaseMapBerth packageCahceTwo = baseMapBerthMapper.selectByPointAlias(PACK_CACHE_TWO);
            if (StringUtils.isNotBlank(packageCahceTwo.getPodCode())) {
                taskCreateRequest.setStartPointAlias(PACK_CACHE_TWO);
                taskCreateService.creatTask(taskCreateRequest);
            }
        }
    }
}
