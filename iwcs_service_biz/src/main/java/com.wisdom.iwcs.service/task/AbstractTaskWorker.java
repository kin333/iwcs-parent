package com.wisdom.iwcs.service.task;

import com.rabbitmq.client.Channel;
import com.wisdom.iwcs.service.task.event.WcsConsumer;
import lombok.Data;

import java.util.concurrent.atomic.AtomicBoolean;

@Data
public abstract class AbstractTaskWorker extends WcsConsumer implements Runnable {
    protected AtomicBoolean waitLock = new AtomicBoolean(false);
    protected AtomicBoolean reExecFlag = new AtomicBoolean(false);
    protected  AtomicBoolean stopMeFlag = new AtomicBoolean(false);
    private String topicTag;
    private int taskStatus;

    public AbstractTaskWorker(Channel channel){
        super(channel);
    }

    public abstract void preConditions();
    public abstract void postConditions();
    public abstract void process();

    public abstract void loginListenner();
    public abstract void deleteListenner();

    @Override
    public void run() {
        reExecFlag.set(false);
        /**
         * 订阅消费者
         */
        loginListenner();
        /**
         * Handle pre-conditions
         */
        preConditions();

        /**
         *  Run task
         */
        process();
            while (reExecFlag.get()&&!stopMeFlag.get()) {
            /**
             * Handle pre-conditions
             */
            preConditions();

            /**
             *  Run task
             */
            process();
        }



        /**
         * Some post works after the task finished.
         */
        postConditions();
        /**
         * 取消订阅
         */
        deleteListenner();


    }

    public AtomicBoolean getWaitLock() {
        return waitLock;
    }
    public void stoppedMe() {
        this.stopMeFlag = new AtomicBoolean(true);
    }
}
