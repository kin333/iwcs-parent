package com.wisdom.iwcs.service.task.conditions.point;

import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.task.conditions.pod.IGetPodStrategic;
import com.wisdom.iwcs.service.task.conditions.robot.IGetRobotStrategic;
import com.wisdom.iwcs.service.task.conditions.workercode.IGetWorkerCodeStrategic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取上一个任务的信息
 * @author han
 */
@Component
public class GetLastTaskInfoStrategic implements IGetPointStrategic, IGetPodStrategic, IGetRobotStrategic, IGetWorkerCodeStrategic {
    private static final Logger logger = LoggerFactory.getLogger(GetLastTaskInfoStrategic.class);
    @Autowired
    SubTaskMapper subTaskMapper;
    @Override
    public String getPoint(AutoCreateBaseInfo autoCreateBaseInfo) {
        SubTask subTask = getLastSubTask(autoCreateBaseInfo.getMainTaskNum());
        return subTask.getEndBercode();
    }

    private SubTask getLastSubTask(String mainTaskNum) {
        logger.info("主任务{}开始进行获取上一个任务信息策略", mainTaskNum);
        List<SubTask> subTaskList = subTaskMapper.selectByMainTaskNum(mainTaskNum);
        Preconditions.checkBusinessError(subTaskList.size() <= 0, mainTaskNum + "主任务没有找到对应的子任务,无法获取上一个任务的终点");
        SubTask subTask = subTaskList.get(subTaskList.size() - 1);
        logger.info("主任务{}获取上一个任务终点策略获取完成,返回子任务信息为{}", mainTaskNum, subTask.toString());
        //获取上个子任务
        return subTask;
    }

    @Override
    public String getPod(AutoCreateBaseInfo autoCreateBaseInfo) {
        SubTask subTask = getLastSubTask(autoCreateBaseInfo.getMainTaskNum());
        return subTask.getPodCode();
    }

    @Override
    public String getRobotCode(AutoCreateBaseInfo autoCreateBaseInfo) {
        SubTask subTask = getLastSubTask(autoCreateBaseInfo.getMainTaskNum());
        return subTask.getRobotCode();
    }

    @Override
    public String getWorkerCode(AutoCreateBaseInfo autoCreateBaseInfo) {
        SubTask subTask = getLastSubTask(autoCreateBaseInfo.getMainTaskNum());
        return subTask.getWorkerTaskCode();
    }
}
