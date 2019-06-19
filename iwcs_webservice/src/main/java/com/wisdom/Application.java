package com.wisdom;

import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.socket.SocketApplication;
import io.prometheus.client.spring.boot.EnablePrometheusEndpoint;
import io.prometheus.client.spring.boot.EnableSpringBootMetricsCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableConfigurationProperties({LiquibaseProperties.class, ApplicationProperties.class})
@EnableScheduling
@EnableAsync
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
@ServletComponentScan
public class Application {

    public static void main(String[] args) {
        Object[] objects = new Object[2];
        objects[0] = Application.class;
        objects[1] = SocketApplication.class;
        SpringApplication.run(objects, args);
    }

    @Bean
    public TaskExecutor taskExecutor() {
        // Or use another one of your liking
        return new SimpleAsyncTaskExecutor();
    }
}
