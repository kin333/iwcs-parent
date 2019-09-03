package com.wisdom.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 配置线程池
 * @author han
 */
@Configuration
public class TaskExecutePool {

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        /**
         * 核心线程数
         * 核心线程会一直存活，即使没有任务需要执行
         */
        executor.setCorePoolSize(4);
        //最大线程数
        executor.setMaxPoolSize(8);
        //队列容量
        executor.setQueueCapacity(100);
        //线程空闲时间
        executor.setKeepAliveSeconds(60);
        //线程名称前缀
        executor.setThreadNamePrefix("Pool-A");
        //任务拒绝处理器
        //CallerRunsPolicy: 不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();

        return executor;
    }
}
