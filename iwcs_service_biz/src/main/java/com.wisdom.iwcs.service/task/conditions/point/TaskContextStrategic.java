package com.wisdom.iwcs.service.task.conditions.point;

import com.wisdom.iwcs.domain.task.TaskContext;
import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;
import com.wisdom.iwcs.mapper.task.TaskContextMapper;
import com.wisdom.iwcs.service.task.conditions.pod.IGetPodStrategic;
import com.wisdom.iwcs.service.task.conditions.robot.IGetRobotStrategic;
import com.wisdom.iwcs.service.task.conditions.workercode.IGetWorkerCodeStrategic;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 从context上下文中获取
 * @author han
 */
@Component
public class TaskContextStrategic implements IGetPointStrategic, IGetPodStrategic, IGetRobotStrategic, IGetWorkerCodeStrategic {
    private static final Logger logger = LoggerFactory.getLogger(TaskContextStrategic.class);
    @Autowired
    TaskContextMapper taskContextMapper;

    @Override
    public String getPoint(AutoCreateBaseInfo autoCreateBaseInfo) {
        logger.info("主任务{}开始进行从context上下文中获取策略", autoCreateBaseInfo.getMainTaskNum());
        TaskContext taskContext = taskContextMapper.selectByMainTaskNum(autoCreateBaseInfo.getMainTaskNum());
        String context = taskContext.getContext();
        try {
            JSONObject jsonObject = new JSONObject(context);
            String point = jsonObject.getString(autoCreateBaseInfo.getValue());
            logger.info("主任务{}从context上下文中获取策略获取完成,返回值为{}", autoCreateBaseInfo.getMainTaskNum(), point);
            return point;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getPod(AutoCreateBaseInfo autoCreateBaseInfo) {
        return getPoint(autoCreateBaseInfo);
    }

    @Override
    public String getRobotCode(AutoCreateBaseInfo autoCreateBaseInfo) {
        return getPoint(autoCreateBaseInfo);
    }

    @Override
    public String getWorkerCode(AutoCreateBaseInfo autoCreateBaseInfo) {
        return getPoint(autoCreateBaseInfo);
    }
}
