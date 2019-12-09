package com.wisdom.iwcs.service.task.maintask;


import com.rabbitmq.client.Channel;
import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.TaskConstants;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.service.log.logImpl.RabbitMQPublicService;
import com.wisdom.iwcs.service.task.AbstractTaskWorker;
import com.wisdom.iwcs.service.task.impl.MainTaskService;
import com.wisdom.iwcs.service.task.impl.MessageService;
import com.wisdom.iwcs.service.task.impl.SubTaskService;
import com.wisdom.iwcs.service.task.scheduler.WcsTaskScheduler;
import com.wisdom.iwcs.service.task.subtask.impl.SubTaskWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTaskWorker extends AbstractTaskWorker {
    private final Logger logger = LoggerFactory.getLogger(MainTaskWorker.class);
    private SubTaskWorker subTaskWorker;
    private Thread subTaskWorkerThread;
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
//        SubTask currentPendingSubtask = getCurrentPendingSubtask();
        subTaskWorker = null;
        logger.info("主任务执行器{}接收到子任务执行器的完成信号", mainTaskNum, subTask.getSubTaskNum());
        synchronized (this) {
            this.notifyAll();
        }
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
        while (true&&!stopMeFlag.get()) {
            try {
                //若已有执行中的子任务worker，等待
                logger.info("当前subTaskWorker{}", subTaskWorker);
                if (subTaskWorker != null) {
                    try {
                        synchronized (this) {
                            logger.debug("主任务{}已有执行中的子任务执行器{}，休眠等待", mainTask.getMainTaskNum(), subTaskWorker.getSubTask().getSubTaskNum());
                            this.wait(10000);
                        }
                    } catch (InterruptedException e) {
                        logger.error("主任务{}执行器尝试随眠失败", mainTask.getMainTaskNum(), e);
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
                        logger.info("调用更新{}主任务结束方法", mainTaskNum);
                        boolean endMainTaskRes = mainTaskService.endMainTask(mainTaskNum);
                        logger.debug("结束主任务{}结果：{}", mainTask.getMainTaskNum(), endMainTaskRes);
                        break;
                    } else {
                        logger.info("检测到主任务{}包含有未结束子任务单{}，开始启动子任务执行线程开始执行", mainTask.getMainTaskNum(), currentPendingSubtask.getSubTaskNum());
                        //TODO channel 待赋值
                        SubTaskWorker subTaskWorker = new SubTaskWorker(null, this, currentPendingSubtask);
                        logger.info("子任务单{}启动子任务worker线程", currentPendingSubtask.getSubTaskNum());
                        Thread subTaskWorkerThread = new Thread(subTaskWorker);
                        subTaskWorkerThread.setName("subtaskWorker-" + currentPendingSubtask.getSubTaskNum() + "ThreadID-" + subTaskWorkerThread.getId());
                        subTaskWorkerThread.start();

                        MessageService messageService = AppContext.getBean("messageService");
                        //向消息队列发送消息
                        String message = messageService.get("start_task");
                        RabbitMQPublicService.successTaskLog(new TaskOperationLog(currentPendingSubtask.getSubTaskNum(), TaskConstants.operationStatus.START_TASK,message));

                        //将当前已启动的subtaskWork注入主任务对象
                        this.subTaskWorker = subTaskWorker;
                        this.subTaskWorkerThread = subTaskWorkerThread;
                      }
                }
            } catch (Exception e) {
                logger.error("调度器出错，主任务{}执行出错", mainTask.getMainTaskNum());
                logger.error("错误信息:", e);
                synchronized (this) {
                    try {
                        this.wait(60 * 1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }

            }
        }
    }

    @Override
    public void loginListenner() {

    }

    @Override
    public void deleteListenner() {

    }


    /**
     * 获取当前待执行子任务（当前正在执行,若没有，尝试动态创建下一步子任务）
     *
     * @return
     */
    private SubTask getCurrentPendingSubtask() {
        SubTaskService subTaskService = (SubTaskService) AppContext.getBean("subTaskService");
        SubTask nextSubtask = subTaskService.getCurrentPendingSubtask(mainTask.getMainTaskNum());
        return nextSubtask;
    }

    public SubTaskWorker getSubTaskWorker() {
        return subTaskWorker;
    }


    public void stopCurrentSubtaskThreadAndMainTask(){
        subTaskWorker.stoppedMe();
        this.stoppedMe();
    }

    /**
     * MainTask, Include all its sub-tasks and sub task conditions.
     */
    private MainTask mainTask;
    private SubTaskWorker curSubtaskWorker;

}
