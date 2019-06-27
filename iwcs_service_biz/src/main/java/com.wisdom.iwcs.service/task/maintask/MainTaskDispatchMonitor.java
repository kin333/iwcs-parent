package com.wisdom.iwcs.service.task.maintask;

import com.wisdom.base.context.AppContext;

/**
 * 主任务下发器，负责待下发的主任务的下发执行
 */
public class MainTaskDispatchMonitor implements Runnable {

    @Override
    public void run() {
        //获取所有待执行任务

        //根据待执行主任务顺序（优先级及创建时间）轮训判断是否主任务是否可执行，并为每个可执行的主任务创建独立执行线程并启动
        AppContext.getBean("");
        //遍历一遍后等待执行

    }
}
