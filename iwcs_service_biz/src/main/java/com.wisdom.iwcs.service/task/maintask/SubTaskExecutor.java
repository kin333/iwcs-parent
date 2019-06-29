package com.wisdom.iwcs.service.task.maintask;

import com.wisdom.iwcs.domain.task.SubTaskConditions;
import com.wisdom.iwcs.domain.task.dto.MainTaskWithSubTaskInfos;
import com.wisdom.iwcs.domain.task.dto.SubTaskInfo;
import com.wisdom.iwcs.domain.task.dto.SubTaskStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * 主任务执行器,负责主任务任务执行
 */
public class SubTaskExecutor implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(SubTaskExecutor.class);
    private MainTaskWithSubTaskInfos mainTask;
    private SubTaskInfo subtask;

    @Override
    public void run() {
        logger.info("子任务{}执行器线程被启动", subtask.getSubTaskNum());
        List<SubTaskConditions> preTaskRelConditionsList = subtask.getPreTaskRelConditionsList();
        List<SubTaskConditions> afterTaskRelConditionsList = subtask.getAfterTaskRelConditionsList();
        while (true) {
            logger.info("开始检查主任务{}开始执行下一步子任务{}，检查条件", subtask.getMainTaskNum(), subtask.getSubTaskNum());
            //已结束的任务自动结束该任务并上报主任务线程
            if (SubTaskStatusEnum.Finished.getStatusCode().equals(subtask.getTaskStatus())) {
                //TODO 上报主线程
                break;
            } else {
                // 检查并执行未完成的前置条件
                preTaskRelConditionsList.stream().forEach(c -> c.getConditonTriger());


            }


            subtask.getConditionHandler();
            logger.info("开始执行主任务{}子任务{}", subtask.getMainTaskNum(), subtask.getSubTaskNum());
        }
    }


    private Boolean nextSubTaskExecutable() {
        return true;
    }

    public SubTaskExecutor(MainTaskWithSubTaskInfos mainTask, SubTaskInfo subtask) {
        this.mainTask = mainTask;
        this.subtask = subtask;
    }

    public MainTaskWithSubTaskInfos getMainTask() {
        return mainTask;
    }

    public void setMainTask(MainTaskWithSubTaskInfos mainTask) {
        this.mainTask = mainTask;
    }

    public SubTaskInfo getSubtask() {
        return subtask;
    }

    public void setSubtask(SubTaskInfo subtask) {
        this.subtask = subtask;
    }
}
