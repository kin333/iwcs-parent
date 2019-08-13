package com.wisdom.iwcs.service.task.scheduler;

import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.service.task.wcsSimulator.QuaAutoToAgingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 自动生成  线体工作台补充空货架,线体去老化区  的主任务
 * @author han
 */
@Component
public class WorkLineScheduler implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(WcsTaskScheduler.class);


    private String mapCode;

    public WorkLineScheduler(String mapCode) {
        this.mapCode = mapCode;
    }
    public WorkLineScheduler() {
        this.mapCode = "DD";
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (this) {
                    QuaAutoToAgingService quaAutoToAgingService = AppContext.getBean("quaAutoToAgingService");
                    quaAutoToAgingService.workLineScheduler(mapCode);
                    logger.debug("产线工作台主任务生成器线程主动睡眠 2 min");
                    this.wait(60 * 1000 * 1);
                }
            } catch (InterruptedException e) {
                logger.error("产线工作台主任务生成器线程尝试休眠失败！");
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
