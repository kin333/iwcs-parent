package com.wisdom.iwcs.service.task.check;

import com.wisdom.iwcs.domain.task.dto.HealthCheckResult;

/**
 * 健康检查接口
 * @author han
 */
public interface HealthCheck {

    /**
     * 需要执行的健康检查
     * @return 检查结果
     */
    HealthCheckResult check();
}
