package com.wisdom.iwcs.service.task.event;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.wisdom.iwcs.service.task.subtask.intf.WcsObservable;

import java.io.IOException;

public abstract class WcsConsumer extends DefaultConsumer {
    public WcsConsumer(Channel channel) {
        super(channel);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
        getChannel().basicConsume(queueName,true, this);
    }

    /**
     * When the event happens, this method will be notified.
     *
     * @param o
     * @param arg
     */
    void onMessage(WcsObservable o, Object arg) {
        ;
    }

    private String queueName;
}
