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
        if(stillRunable()){
            /**
             * 订阅消费者
             */
            loginListenner();
        }

        if(stillRunable()){
            /**
             * Handle pre-conditions
             */
            preConditions();
        }
        if(stillRunable()){
            /**
             *  Run task
             */
            process();
        }
        if(stillRunable()){
            while (reExecFlag.get()&&stillRunable()) {
                /**
                 * Handle pre-conditions
                 */
                preConditions();

                /**
                 *  Run task
                 */
                process();
            }
        }

        if(stillRunable()){
            /**
             * Some post works after the task finished.
             */
            postConditions();
        }
        if(stillRunable()){

            /**
             * 取消订阅
             */
            deleteListenner();
        }

    }

    public AtomicBoolean getWaitLock() {
        return waitLock;
    }
    public void stoppedMe() {
        this.stopMeFlag = new AtomicBoolean(true);
    }

    /**
     * 是否可继续执行
     * @return
     */
    public boolean stillRunable(){
        return !stopMeFlag.get()&&!Thread.interrupted();
    }



}
