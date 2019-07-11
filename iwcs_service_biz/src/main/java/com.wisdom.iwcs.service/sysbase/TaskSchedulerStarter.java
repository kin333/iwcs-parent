package com.wisdom.iwcs.service.sysbase;

import com.wisdom.iwcs.service.task.scheduler.WcsTaskScheduler;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logger.info("开始启动任务调度器线程");
        Thread thread = new Thread(wcsTaskScheduler);
        thread.start();
        logger.info("启动任务调度器线程成功");

    }
}
