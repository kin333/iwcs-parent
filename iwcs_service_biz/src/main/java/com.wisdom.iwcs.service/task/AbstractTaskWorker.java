package com.wisdom.iwcs.service.task;

import com.rabbitmq.client.Channel;
import com.wisdom.iwcs.service.task.subtask.intf.WcsObservable;
import com.wisdom.iwcs.service.task.event.WcsConsumer;
import lombok.Data;

import java.util.concurrent.atomic.AtomicBoolean;

@Data
public abstract class AbstractTaskWorker extends WcsConsumer implements Runnable {
    protected AtomicBoolean waitLock = new AtomicBoolean(false);
    protected WcsObservable observable;
    private String topicTag;
    private int taskStatus;

    public AbstractTaskWorker(Channel channel){
        super(channel);
        this.observable = observable;
    }


    public abstract void preConditions();
    public abstract void postConditions();
    public abstract void process();

    @Override
    public void run() {
        /**
         * Handle pre-conditions
         */
        preConditions();

        /**
         *  Run task
         */
        process();

        /**
         * Some post works after the task finished.
         */

    }


 }
