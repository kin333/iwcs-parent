package com.wisdom.iwcs.service.task.conditions.workercode;

import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取指定步骤的任务号策略
 * @author han
 */
@Component
public class AssignTaskStrategic implements IGetWorkerCodeStrategic {
    @Autowired
    SubTaskMapper subTaskMapper;
    @Override
    public String getWorkerCode(AutoCreateBaseInfo autoCreateBaseInfo) {
        //获取主任务下所有子任务
        List<SubTask> subTaskList = subTaskMapper.selectByMainTaskNum(autoCreateBaseInfo.getMainTaskNum());
        //获取指定步骤的子任务
        SubTask subTask = subTaskList.get(Integer.valueOf(autoCreateBaseInfo.getValue()));
        return subTask.getWorkerTaskCode();
    }
}
