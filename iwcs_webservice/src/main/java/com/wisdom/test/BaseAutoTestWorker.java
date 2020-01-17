package com.wisdom.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础的创建自动化任务的模板类,所有自动化任务可以继承本类,run方法不能重写
 * mes自动化测试已使用
 * @author han
 */
public abstract class BaseAutoTestWorker implements Runnable{

    private final Logger logger = LoggerFactory.getLogger(BaseAutoTestWorker.class);

    private long waitTime = 60 * 1000;

    @Override
    public final void run() {
        while (true) {
            try {
                //生成任务
                this.createTask();
                //程序休眠
                synchronized (this) {
                    logger.info("创建任务调度器线程主动随眠60*1000*1");
                    this.wait(waitTime);
                }

            } catch (InterruptedException e) {
                logger.error("创建主任务调度器线程尝试休眠失败！");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    synchronized (this) {
                        this.wait(30 * 1000);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    /**
     * 创建任务逻辑
     */
    abstract void createTask();

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }
}
