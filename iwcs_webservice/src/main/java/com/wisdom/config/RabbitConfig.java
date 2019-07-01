package com.wisdom.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置
 */
@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_A = "iwcs_exchange_A";


    /**
     * 默认的交换机使用的是DirectExchange,这里使用Topic类型的交换机
     * @return
     */
    @Bean
    public TopicExchange exchange() {
        // 是否持久化
        boolean durable = true;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = false;
        return new TopicExchange(EXCHANGE_A, durable, autoDelete);
    }

}

