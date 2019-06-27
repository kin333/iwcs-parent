package com.wisdom.iwcs.service.task.maintask;

import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.dto.MainTaskWithSubTaskInfos;
import com.wisdom.iwcs.domain.task.dto.SubTaskInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 主任务执行器,负责主任务任务执行
 */
public class MainTaskExecutor implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(MainTaskExecutor.class);
    private MainTaskWithSubTaskInfos mainTaskWithSubTaskInfos;
    private String curSubTaskNum;
    private int indexOfCurSubTask = -1;
    private SubTaskExecutor curSubTaskExecutor;
    private Thread curSubTaskExecutorThread;

    @Override
    public void run() {
        logger.info("主任务{}执行器线程被启动", mainTaskWithSubTaskInfos.getMainTaskNum());
        List<SubTaskInfo> subTaskList = mainTaskWithSubTaskInfos.getSubTaskInfos();
        //按照执行顺序正向排序
        subTaskList = subTaskList.stream().sorted(Comparator.comparing(SubTask::getSubTaskSeq)).collect(Collectors.toList());
        while (true) {
            logger.info("开始检查主任务{}开始执行下一步子任务{}", mainTaskWithSubTaskInfos.getMainTaskNum(), curSubTaskNum);
            SubTask nextPendingSubtask = getNextPendingSubtask();
            if (curSubTaskExecutor != null) {
                String subTaskNum = curSubTaskExecutor.getSubtask().getSubTaskNum();
            } else {
                logger.debug("启动子任务{}线程", curSubTaskNum);
                curSubTaskExecutorThread = new Thread(new SubTaskExecutor(mainTaskWithSubTaskInfos, subTaskList.get(indexOfCurSubTask)));
                curSubTaskExecutorThread.start();
            }
            logger.info("开始检查主任务{}开始执行下一步子任务{}", mainTaskWithSubTaskInfos.getMainTaskNum(), curSubTaskNum);
        }

    }

    private Boolean nextSubTaskExecutable() {
        return true;
    }


    /**
     * 获取下一个子任务
     *
     * @return
     */
    private SubTask getNextPendingSubtask() {
        List<SubTaskInfo> subTaskInfos = mainTaskWithSubTaskInfos.getSubTaskInfos();
        if (indexOfCurSubTask == -1) {
            return subTaskInfos.get(0);
        } else if (indexOfCurSubTask == subTaskInfos.size() - 1) {
            return null;
        } else {
            indexOfCurSubTask = indexOfCurSubTask + 1;
            return subTaskInfos.get(indexOfCurSubTask);
        }
    }

}
