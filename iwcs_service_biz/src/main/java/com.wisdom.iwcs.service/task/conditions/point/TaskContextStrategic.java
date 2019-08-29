package com.wisdom.iwcs.service.task.conditions.point;

import com.wisdom.iwcs.domain.task.TaskContext;
import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;
import com.wisdom.iwcs.mapper.task.TaskContextMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 从context上下文中获取
 */
public class TaskContextStrategic implements IGetPointStrategic{
    @Autowired
    TaskContextMapper taskContextMapper;

    @Override
    public String getPoint(AutoCreateBaseInfo autoCreateBaseInfo) {
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(autoCreateBaseInfo.getMainTaskNum());
        return null;
    }
}
