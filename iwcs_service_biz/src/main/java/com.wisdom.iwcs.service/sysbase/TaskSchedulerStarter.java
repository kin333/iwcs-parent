package com.wisdom.iwcs.service.sysbase;

import com.wisdom.iwcs.mapper.log.TaskOperationLogMapper;
import com.wisdom.iwcs.netty.DoorNettyClient;
import com.wisdom.iwcs.service.task.scheduler.WcsTaskScheduler;
import com.wisdom.iwcs.service.task.wcsSimulator.NodeActionSendThread;
import com.wisdom.iwcs.service.task.wcsSimulator.NodeActionThreadService;
import com.wisdom.iwcs.service.task.wcsSimulator.TaskLogThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
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
    TaskOperationLogMapper taskOperationLogMapper;
    @Autowired
    TaskLogThreadService taskLogThreadService;
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    NodeActionSendThread nodeActionSendThread;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //防止上下文多次刷新时，重复启动
        if (contextRefreshedEvent.getApplicationContext().getParent() == null) {
//            Thread taskthread = new Thread(wcsTaskScheduler);
//            taskthread.start();
            logger.info("开始启动任务调度器线程");
//            logger.info("开始启动任务调度器线程");
            //启动消息日志
//            threadPoolTaskExecutor.execute(taskLogThreadService);
//
//            //启动节点活动消费线程(发送节点通知),3为临时值,应为自动配置值
//            for (int i = 0; i < 3; i++) {
//                threadPoolTaskExecutor.execute(new NodeActionThreadService());
//            }
//
//            logger.info("开始启动节点通知调度线程");
//            threadPoolTaskExecutor.execute(nodeActionSendThread);

            //自动门 plc连接启动
            DoorNettyClient doorNettyClient = DoorNettyClient.getInstance();
            Thread doorNettyClientThread = new Thread(doorNettyClient);
            doorNettyClientThread.start();

//            Thread taskthread = new Thread(wcsTaskScheduler);
//            taskthread.start();
//            logger.info("启动任务调度器线程成功");

//            LineNettyClient lineNettyClient = LineNettyClient.getInstance();
//            Thread lineNettyClientThread = new Thread(lineNettyClient);
//            lineNettyClientThread.start();
////
//            ElevatorNettyClient elevatorNettyClient = ElevatorNettyClient.getInstance();
//            Thread elevatorThread = new Thread(elevatorNettyClient);
//            elevatorThread.start();
//
//            Thread serverThread = new Thread(new NettyServer());
//            serverThread.start();
//
//        logger.info("开始产线工作台任务生成器");
//        Thread workLineThread = new Thread(workLineScheduler, "超级线程" + UUID.randomUUID().toString().substring(0, 4));
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
}
