package com.wisdom.iwcs.service.task.scheduler;


import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.service.task.impl.MainTaskService;
import com.wisdom.iwcs.service.task.maintask.MainTaskWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WcsTaskScheduler implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(WcsTaskScheduler.class);

    private ConcurrentHashMap<String, MainTaskWorker> maintaskWorkerMaps = new ConcurrentHashMap<String, MainTaskWorker>();


    public WcsTaskScheduler() {

    }

    /**
     * 完成一次主任务下发检测及下发
     */
    public void dispatchMaintask() {
        MainTaskService mainTaskService = (MainTaskService) AppContext.getBean("mainTaskService");
        logger.info("调度主任务开始,开始时间{}", new Date());
        //获取所有待下发的任务
        List<MainTask> allUnDispatchedTask = mainTaskService.getAllUnDispatchedTask();
        logger.info("取得{}条未完成主任务", allUnDispatchedTask.size());
        //顺序执行主任务可执行性检查及下发
        allUnDispatchedTask.stream().forEach(t -> {
            //检查主任务第一条子任务是否满足执行条件
            MainTaskWorker existWorker = maintaskWorkerMaps.get(t.getMainTaskNum());
            logger.debug("检查主任务{}是否已在调度器中", t.getMainTaskNum());
            if (existWorker == null) {
                logger.debug("主任务{}不在调度器中，新建主任务执行器", t.getMainTaskNum());
                MainTaskWorker mainTaskWorker = new MainTaskWorker(null, t, this);
                Thread thread = new Thread(mainTaskWorker);
                thread.setName("线程主任务执行器-" + t.getMainTaskNum());
                thread.start();
                maintaskWorkerMaps.put(t.getMainTaskNum(), mainTaskWorker);
            } else {
                logger.debug("主任务{}已经在调度器中，跳过", t.getMainTaskNum());
            }

        });


    }


    @Override
    public void run() {
        // 检查主任务列表，拿到所有可以执行的主任务列表，判断主任务是否可以执行，以主任务当前的子任务是否可以执行为标准
        while (true) {
            this.dispatchMaintask();
            try {
                synchronized (this) {
                    logger.error("主任务调度器线程主动随眠60*1000*2");
                    this.wait(60 * 1000 * 1);
                }

            } catch (InterruptedException e) {
                logger.error("主任务调度器线程尝试休眠失败！");
                e.printStackTrace();
            }
        }
    }

    public void onMainTaskEnd(String mainTaskNum) {
        logger.info("主任务收到结束信号{}：", mainTaskNum);
        MainTaskWorker mainTaskWorker = maintaskWorkerMaps.get(mainTaskNum);
        logger.info("主任务在线程中");
        if (mainTaskWorker != null) {
            logger.info("主任务在线程中存在，maintaskWorkerMaps中移除主任务{}", mainTaskNum);
            this.maintaskWorkerMaps.remove(mainTaskNum);
        }
        MainTaskService mainTaskService = (MainTaskService) AppContext.getBean("mainTaskService");
        mainTaskService.loopMaintTask(mainTaskNum);
    }

}


