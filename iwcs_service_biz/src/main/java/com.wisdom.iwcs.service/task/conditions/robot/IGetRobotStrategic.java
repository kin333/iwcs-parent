package com.wisdom.iwcs.service.task.conditions.robot;

/**
 * 创建任务时,获取三方执行者(机器号)策略规范
 * @author han
 */
public interface IGetRobotStrategic {

    /**
     * 获取执行者(机器号)
     * @return
     */
    String getRobotCode(String mainTaskNum, String value);
}
