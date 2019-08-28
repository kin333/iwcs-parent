package com.wisdom.iwcs.service.task.conditions.workercode;


/**
 * 创建任务时,获取三方执行id(任务编号)策略规范
 * @author han
 */
public interface IGetWorkerCodeStrategic {
    /**
     * 获取三方执行编号
     * @return
     */
    String getWorkerCode(String mainTaskNum, String value);
}
