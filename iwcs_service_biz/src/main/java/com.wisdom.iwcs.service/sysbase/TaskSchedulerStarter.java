package com.wisdom.iwcs.service.sysbase;

import com.wisdom.iwcs.service.task.scheduler.WcsTaskScheduler;
import com.wisdom.iwcs.service.task.wcsSimulator.QuaAutoCallPodWorker;
import com.wisdom.iwcs.service.task.wcsSimulator.QuaAutoToAgingWorker;
import com.wisdom.iwcs.service.task.scheduler.WorkLineScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 任务调度启动器
 */
@Component
public class TaskSchedulerStarter implements ApplicationListener<ContextRefreshedEvent> {
    private final Logger logger = LoggerFactory.getLogger(TaskSchedulerStarter.class);

    @Autowired
    private WcsTaskScheduler wcsTaskScheduler;
    @Autowired
    WorkLineScheduler workLineScheduler;
    @Autowired
    private QuaAutoToAgingWorker quaAutoToAgingWorker;
    @Autowired
    private QuaAutoCallPodWorker quaAutoCallPodWorker;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//        logger.info("开始启动任务调度器线程");
//        Thread thread = new Thread(wcsTaskScheduler);
//        thread.start();
//        logger.info("启动任务调度器线程成功");

//        logger.info("开始产线工作台任务生成器");
//        Thread workLineThread = new Thread(workLineScheduler);
//        workLineThread.start();
//        logger.info("启动产线工作台任务生成器成功");
//
//        logger.info("开始启动模拟创建检验区货架到老化区任务调度器线程");
//        Thread quaAutoToAgingThread = new Thread(quaAutoToAgingWorker);
//        quaAutoToAgingThread.start();
//        logger.info("启动模拟创建检验区货架到老化区任务调度器线程成功");
//
//        logger.info("开始启动创建模拟老化区货架到检验区任务调度器线程");
//        Thread quaAutoCallPodThread = new Thread(quaAutoCallPodWorker);
//        quaAutoCallPodThread.start();
//        logger.info("启动创建模拟老化区货架到检验区调度器线程成功");

    }
}
