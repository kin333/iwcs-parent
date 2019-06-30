package com.wisdom.iwcs.service.task;

import com.wisdom.iwcs.service.task.subtask.intf.CondIntf;
import com.wisdom.iwcs.service.task.subtask.intf.WcsObservable;
import com.wisdom.iwcs.service.task.subtask.intf.WcsObserver;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public abstract class AbstractTaskWorker implements Runnable, WcsObserver {
    protected AtomicBoolean waitLock = new AtomicBoolean(false);
    protected WcsObservable observable;
    private String topic;
    private int taskStatus;
    private List<CondIntf> conditions = new LinkedList<CondIntf>();


    public int getTaskStatus() { return taskStatus; }
    public void setTaskStatus(int taskStatus) { this.taskStatus = taskStatus; }

    @Override
    public String getTopic(){ return topic;}

    public AbstractTaskWorker(WcsObservable observable){
        this.observable = observable;
    }

    /**
     * Register to receive messages from a topic.
     */
    public void register(){
        observable.addListener(this);
    }

    /**
     * Register to receive messages from a topic.
     */
    public void unRegister(){
        observable.removeListener(this);
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
