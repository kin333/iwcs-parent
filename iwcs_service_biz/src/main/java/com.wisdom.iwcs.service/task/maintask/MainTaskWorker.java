package com.wisdom.iwcs.service.task.maintask;


import com.rabbitmq.client.Channel;
import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.service.task.AbstractTaskWorker;
import com.wisdom.iwcs.service.task.impl.MainTaskService;
import com.wisdom.iwcs.service.task.impl.SubTaskService;
import com.wisdom.iwcs.service.task.scheduler.WcsTaskScheduler;
import com.wisdom.iwcs.service.task.subtask.impl.SubTaskWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTaskWorker extends AbstractTaskWorker {
    private final Logger logger = LoggerFactory.getLogger(MainTaskWorker.class);
    private SubTaskWorker subTaskWorker;
    private WcsTaskScheduler wcsTaskScheduler;

    public MainTaskWorker(Channel channel, MainTask mainTask, WcsTaskScheduler wcsTaskScheduler) {
        super(channel);
        this.mainTask = mainTask;
        this.wcsTaskScheduler = wcsTaskScheduler;
    }


    SubTask getNextSubTask(){
        return null;
    }

    public void onSubTaskDone(SubTask subTask) {
        String mainTaskNum = mainTask.getMainTaskNum();
        // 切换下一个子任务，如果没有子任务，则该主任务标记为完成，已下发
        SubTask currentPendingSubtask = getCurrentPendingSubtask();
        logger.info("主任务执行器{}接收到子任务执行器的完成信号", mainTaskNum, subTask.getSubTaskNum());
    }

    @Override
    public void preConditions() {


    }

    @Override
    public void postConditions() {

    }

    @Override
    public void process() {
        logger.info("主任务{}执行器线程被启动", mainTask.getMainTaskNum());
        logger.info("开始执行子任务");
        while (true) {
            //若已有执行中的子任务worker，等待
            if (subTaskWorker != null) {
                try {
                    logger.debug("主任务{}已有执行中的子任务{}，休眠等待", mainTask.getMainTaskNum(), subTaskWorker.getSubTask().getSubTaskNum());
                    this.wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                SubTask currentPendingSubtask = getCurrentPendingSubtask();
                String mainTaskNum = mainTask.getMainTaskNum();
                if (currentPendingSubtask == null) {
                    if (wcsTaskScheduler != null) {
                        logger.info("主任务{}没有待完成的子任务,上报任务调度器，主任务完成", mainTaskNum);
                        wcsTaskScheduler.onMainTaskEnd(mainTaskNum);
                    }
                    MainTaskService mainTaskService = (MainTaskService) AppContext.getBean("mainTaskService");
                    logger.info("更新{}主任务状态为已完成9", mainTaskNum);
                    boolean endMainTaskRes = mainTaskService.endMainTask(mainTaskNum);
                    logger.debug("结束主任务{}结果：{}", mainTask.getMainTaskNum(), endMainTaskRes);

                    break;
                } else {
                    logger.info("检测到主任务{}包含有未结束子任务单{}，开始启动子任务执行线程开始执行", mainTask.getMainTaskNum(), currentPendingSubtask.getSubTaskNum());
                    //TODO channel 待赋值
                    logger.debug("启动子任务worker");
                    SubTaskWorker subTaskWorker = new SubTaskWorker(null, this, currentPendingSubtask);
                    logger.info("子任务单{}启动子任务worker线程", currentPendingSubtask.getSubTaskNum());
                    Thread subTaskWorkerThread = new Thread(subTaskWorker);
                    subTaskWorkerThread.setName("subtaskWorker-" + currentPendingSubtask.getSubTaskNum());
                    subTaskWorkerThread.start();
                    //将当前已启动的subtaskWork注入主任务对象
                    this.subTaskWorker = subTaskWorker;
                    //TODO 注册子任务事件监听
                    logger.debug("注册当前子任务监听事件");
                }
            }
        }
        logger.info("主任务执行器{}结束", mainTask.getMainTaskNum());
        //TODO 是否需要做线程相关处理、事件监听等。待定

    }



    /**
     * 获取当前待执行子任务（当前正在执行或）
     *
     * @return
     */
    private SubTask getCurrentPendingSubtask() {
        SubTaskService subTaskService = (SubTaskService) AppContext.getBean("subTaskService");
        SubTask nextSubtask = subTaskService.getCurrentPendingSubtask(mainTask.getMainTaskNum());
        return nextSubtask;
    }


    /**
     * MainTask, Include all its sub-tasks and sub task conditions.
     */
    private MainTask mainTask;
    private SubTaskWorker curSubtaskWorker;

}
