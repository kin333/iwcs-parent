package com.wisdom.iwcs.service.task.scheduler;


import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.mapper.log.TaskOperationLogMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.service.task.impl.MainTaskService;
import com.wisdom.iwcs.service.task.maintask.MainTaskWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class WcsTaskScheduler implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(WcsTaskScheduler.class);
    public  static AtomicBoolean waitLock = new AtomicBoolean(false);
    public static  AtomicBoolean stopped = new AtomicBoolean(false);


    @Autowired
    TaskOperationLogMapper taskOperationLogMapper;
    @Autowired
    MainTaskMapper mainTaskMapper;

    private  ConcurrentHashMap<String, MainTaskWorker> maintaskWorkerMaps = new ConcurrentHashMap<String, MainTaskWorker>();
    private  ConcurrentHashMap<String, Thread> maintaskWorkerThreadMaps = new ConcurrentHashMap<String, Thread>();

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
                maintaskWorkerThreadMaps.put(t.getMainTaskNum(), thread);

                //将主任务状态改为正在执行
                MainTask mainTaskTmp = new MainTask();
                mainTaskTmp.setId(t.getId());
                mainTaskTmp.setTaskStatus(TaskConstants.mainTaskStatus.MAIN_ISSUED);
                mainTaskTmp.setDateChg(new Date());
                mainTaskMapper.updateByPrimaryKeySelective(mainTaskTmp);
            } else {
                logger.debug("主任务{}已经在调度器中，跳过", t.getMainTaskNum());
            }
        });


    }


    @Override
    public void run() {
        // 检查主任务列表，拿到所有可以执行的主任务列表，判断主任务是否可以执行，以主任务当前的子任务是否可以执行为标准
        while (true) {
            try {
                synchronized (this) {
                    if(stopped.get()){
                        logger.warn("任务调度标志位false,暂不调用任务下发");
                    }else{
                    this.dispatchMaintask();
                    }
                    logger.info("主任务调度器线程主动随眠60*1000*1");
                    this.wait(5 * 1000 * 1);
                }

            } catch (InterruptedException e) {
                logger.error("主任务调度器线程尝试休眠失败！");
            }
        }
    }

    public void onMainTaskEnd(String mainTaskNum) {
        logger.info("主任务收到结束信号{}：", mainTaskNum);
        MainTaskWorker mainTaskWorker = maintaskWorkerMaps.get(mainTaskNum);
        logger.info("主任务在线程中");
        MainTaskService mainTaskService = (MainTaskService) AppContext.getBean("mainTaskService");
        mainTaskService.loopMaintTask(mainTaskNum);
        if (mainTaskWorker != null) {
            logger.info("主任务在线程中存在，maintaskWorkerMaps中移除主任务{}", mainTaskNum);
            this.maintaskWorkerMaps.remove(mainTaskNum);
            this.maintaskWorkerThreadMaps.remove(mainTaskNum);
        }
    }

    public ConcurrentHashMap<String, MainTaskWorker> getMaintaskWorkerMaps() {
        return maintaskWorkerMaps;
    }

    /**
     * 停止主任务线程
     * @param maintaskNum
     * @param subtaskNum
     */
    public void stopMainTaskThread(String maintaskNum,String subtaskNum){
        logger.info("尝试停止任务线程，主任务{}，子任务{}",maintaskNum,subtaskNum);
        MainTaskWorker mainTaskWorker = maintaskWorkerMaps.get(maintaskNum);
        if(mainTaskWorker != null){
            logger.info("查询到主任务{}正在执行,调用其stopCurrentSubtaskThreadAndMainTask方法，修改停止标记");
            mainTaskWorker.stopCurrentSubtaskThreadAndMainTask();
            maintaskWorkerMaps.remove(maintaskNum);
        }else{
            logger.warn("停止任务异常，该主任务{}不在执行器中",maintaskNum);
        }
    }

}


