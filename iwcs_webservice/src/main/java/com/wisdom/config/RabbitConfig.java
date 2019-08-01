package com.wisdom.config;

import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.constant.RabbitMQConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置
 */
@Configuration
public class RabbitConfig {

    @Autowired
    ApplicationProperties applicationProperties;

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
        return new TopicExchange(RabbitMQConstants.EXCHANGE_A, durable, autoDelete);
    }

    /**
     * 声明一个队列
     * @return
     */
    @Bean
    public Queue queue() {
        // 是否持久化
        boolean durable = false;
        // 仅创建者可以使用的私有队列，断开后自动删除
        boolean exclusive = false;
        // 当所有消费客户端连接断开后，是否自动删除队列
        boolean autoDelete = true;
        return new Queue(RabbitMQConstants.QUEUE_A, durable, exclusive, autoDelete);
    }


    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RabbitMQConstants.QUEUE_A);
        return container;
    }

    /** 以下代码用于初始化时的地址信息配置 2 */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    /** 以下代码用于初始化时的地址信息配置 1 */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory =
                new CachingConnectionFactory(applicationProperties.getRabbitmq().getHost(),
                                            applicationProperties.getRabbitmq().getPort());
        connectionFactory.setUsername(applicationProperties.getRabbitmq().getUsername());
        connectionFactory.setPassword(applicationProperties.getRabbitmq().getPassword());
        connectionFactory.setVirtualHost(applicationProperties.getRabbitmq().getVirtualHost());
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
    }


}
