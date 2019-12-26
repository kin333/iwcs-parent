package com.wisdom.iwcs.service.task.conditions.strategy;

import com.wisdom.iwcs.common.utils.exception.BusinessException;

/**
 * 策略处理器接口
 * @author han
 */
public interface IStrategyHandler {

    /**
     * 处理方法
     * @param resource 资源值
     */
    default void strategyHandler(Object resource){
        //TODO 如果使用必须重写此方法
        throw new BusinessException("配置出错:方法未重写,可能是strategy_params列缺少配置");
    }

    /**
     * 处理方法
     * @param resource 资源值
     * @param strategyParams 用于校验的策略参数(json格式字符串)
     */
    default void strategyHandler(Object resource, String strategyParams){
        //TODO 如果使用必须重写此方法
        throw new BusinessException("配置出错:方法未重写, 可能是strategy_params列不需要有配置,无配置时应设置为空或null");
    }
}
