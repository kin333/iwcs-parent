package com.wisdom.iwcs.service.task.maintask;


import com.rabbitmq.client.Channel;
import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.service.task.AbstractTaskWorker;
import com.wisdom.iwcs.service.task.impl.SubTaskService;
import com.wisdom.iwcs.service.task.intf.IMainTaskService;
import com.wisdom.iwcs.service.task.subtask.impl.SubTaskWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainTaskWorker extends AbstractTaskWorker {
    private final Logger logger = LoggerFactory.getLogger(MainTaskWorker.class);
    private SubTaskWorker subTaskWorker;

    public MainTaskWorker(Channel channel, MainTask mainTask) {
        super(channel);
        this.mainTask = mainTask;
    }

    SubTask getNextSubTask(){
        return null;
    }

    public void onSubTaskDone(){
        // 切换下一个子任务，如果没有子任务，则该主任务标记为完成，已下发


    }

    @Override
    public void preConditions() {
        while (true){
            try {
                synchronized (waitLock){
                    System.out.println("Main task is going to wait " + waitLock);
                    waitLock.wait();
                    System.out.println("End to wait for wait thread " + waitLock);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void postConditions() {

    }

    @Override
    public void process() {
        logger.info("主任务{}执行器线程被启动", mainTask.getMainTaskNum());
        logger.info("开始");
        boolean hasUnEndSubtask = true;
        while (hasUnEndSubtask) {
            //若已创建子任务worker，等待
            if (subTaskWorker != null) {
                try {
                    this.wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            } else {
                SubTask currentPendingSubtask = getCurrentPendingSubtask();
                if (currentPendingSubtask == null) {
                    logger.info("主任务{}不包含未结束子任务，主任务自动结束", mainTask.getMainTaskNum());
                    hasUnEndSubtask = false;
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

        // 1. 从数据库获取下一个subTask，生成一个subtaskworker

        // 2. 判断subtask是否可以执行，是否条件满足

//        logger.info("主任务{}执行器线程被启动", mainTaskWithSubTaskInfos.getMainTaskNum());
//        List<SubTaskInfo> subTaskList = mainTaskWithSubTaskInfos.getSubTaskInfos();
//        //按照执行顺序正向排序
//        subTaskList = subTaskList.stream().sorted(Comparator.comparing(SubTask::getSubTaskSeq)).collect(Collectors.toList());
//        while (true) {
//            logger.info("开始检查主任务{}开始执行下一步子任务{}", mainTaskWithSubTaskInfos.getMainTaskNum(), curSubTaskNum);
//            SubTask nextPendingSubtask = getNextPendingSubtask();
//            if (curSubTaskExecutor != null) {
//                String subTaskNum = curSubTaskExecutor.getSubtask().getSubTaskNum();
//            } else {
//                logger.debug("启动子任务{}线程", curSubTaskNum);
//                curSubTaskExecutorThread = new Thread(new SubTaskExecutor(mainTaskWithSubTaskInfos, subTaskList.get(indexOfCurSubTask)));
//                curSubTaskExecutorThread.start();
//            }
//            logger.info("开始检查主任务{}开始执行下一步子任务{}", mainTaskWithSubTaskInfos.getMainTaskNum(), curSubTaskNum);
//        }
    }



    /**
     * 获取当前待执行子任务（当前正在执行或）
     *
     * @return
     */
    private SubTask getCurrentPendingSubtask() {

        IMainTaskService mainTaskService = (IMainTaskService) AppContext.getBean("mainTaskService");
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
