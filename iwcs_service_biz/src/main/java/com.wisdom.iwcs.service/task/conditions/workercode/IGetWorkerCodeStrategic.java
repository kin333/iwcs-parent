package com.wisdom.iwcs.service.task.conditions.workercode;


import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;

/**
 * 创建任务时,获取三方执行id(任务编号)策略规范
 * @author han
 */
public interface IGetWorkerCodeStrategic {
    /**
     * 获取三方执行编号
     * @return
     */
    String getWorkerCode(AutoCreateBaseInfo autoCreateBaseInfo);
}
