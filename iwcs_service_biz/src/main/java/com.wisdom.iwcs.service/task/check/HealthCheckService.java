package com.wisdom.iwcs.service.task.check;

import com.wisdom.base.context.AppContext;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.domain.task.dto.HealthCheckResult;
import com.wisdom.iwcs.service.task.wcsSimulator.TaskLogThreadService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 健康检查服务
 */
@Service
public class HealthCheckService {
    private static final Logger logger = LoggerFactory.getLogger(HealthCheckService.class);
    @Autowired
    ApplicationProperties applicationProperties;

    /**
     * 遍历所有的健康检查
     */
    public void healthCheck() {
        String checkName = applicationProperties.getWarn().getCheckName();
        if (StringUtils.isBlank(checkName)) {
            return;
        }
        String[] names = checkName.split(";");
        for (String name : names) {
            HealthCheck healthCheck = AppContext.getBean(name);
            if (healthCheck != null) {
                logger.error("健康检查类{}不存在,已跳过", name);
                continue;
            }
            HealthCheckResult healthCheckResult = healthCheck.check();
            logger.info("健康检查{}完成,检查结果:{}",name, healthCheckResult.toString());
        }
    }

}
