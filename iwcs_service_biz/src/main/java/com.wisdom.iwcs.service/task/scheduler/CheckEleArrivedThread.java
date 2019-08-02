package com.wisdom.iwcs.service.task.scheduler;

import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.service.task.impl.CheckEleArrivedService;

/**
 * 检查电梯是否到达目标楼层
 */
public class CheckEleArrivedThread implements Runnable {

    /**
     * 电梯任务号
     */
    private String eleTaskCode;
    /**
     * 门开时所在的楼层
     */
    private String instantLocation;

    public CheckEleArrivedThread(String eleTaskCode, String instantLocation) {
        this.eleTaskCode = eleTaskCode;
        this.instantLocation = instantLocation;
    }

    @Override
    public void run() {
        CheckEleArrivedService checkEleArrivedService = AppContext.getBean("checkEleArrivedService");
        checkEleArrivedService.notifyAgv(eleTaskCode, instantLocation);
    }


}
