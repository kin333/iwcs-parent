package com.wisdom.iwcs.service.task.maintask;


import com.rabbitmq.client.Channel;
import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.service.task.AbstractTaskWorker;
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
        // 切换下一个子任务，如果没有子任务，则该主任务标记为完成，已下发，，，，
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
        Object mainTaskService = AppContext.getBean("mainTaskService");

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


//    @Override
//    public void onMessage(WcsObservable o, Object arg) {
//
//    }
    /**
     * 获取下一个子任务
     *
     * @return
     */
    private SubTask getNextPendingSubtask() {
//        List<SubTaskInfo> subTaskInfos = mainTaskWithSubTaskInfos.getSubTaskInfos();
//        if (indexOfCurSubTask == -1) {
//            return subTaskInfos.get(0);
//        } else if (indexOfCurSubTask == subTaskInfos.size() - 1) {
//            return null;
//        } else {
//            indexOfCurSubTask = indexOfCurSubTask + 1;
//            return subTaskInfos.get(indexOfCurSubTask);
//        }

        return null;
    }

    /**
     * MainTask, Include all its sub-tasks and sub task conditions.
     */
    private MainTask mainTask;
    private SubTaskWorker curSubtaskWorker;

}
