package com.wisdom.config;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

/**
 * 结束的时候执行
 * @author dmw
 *
 * 2019年4月15日
 */
@Component
public class BaseDisposableBean implements DisposableBean {
    @Autowired
    ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void destroy() {
        threadPoolTaskExecutor.shutdown();
    }
}