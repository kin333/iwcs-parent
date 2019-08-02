/*
 * SealApplyTask.java
 *
 * Created Date: 2016年7月6日
 *
 * Copyright (c)  Centling Technologies Co., Ltd.
 *
 * This software is the confidential and proprietary information of
 *  Centling Technologies Co., Ltd. ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with
 * Centling Technologies Co., Ltd.
 */

package com.wisdom.task;


import com.wisdom.iwcs.quartz.ErrorRepairThread;
import com.wisdom.iwcs.service.codec.SequenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author ted.ma
 * @version <br>
 * <p>用印申请定时任务</p>
 */
@Component
public class Task {
    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    @Autowired
    SequenceService sequenceService;
    @Autowired
    ErrorRepairThread errorRepairThread;


    //	@Scheduled(cron="0 0/1 * * * ?")
    public void XXXTask() {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

    }

    /**
     * 每月1号0点执行
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void resetInterBlNo() {
        sequenceService.resetInterBlNo();
    }

    /**
     * 每年1月1号0点执行
     */
    @Scheduled(cron = "0 0 0 1 1 ? ")
    public void resetInvoiceNo() {
        sequenceService.resetInvoiceNo();
    }

    /**
     * 处理任务信息异常
     *
     */
    @Scheduled(fixedRateString="60000",initialDelayString="100")
    public void disposeError() {
//        logger.info("错误处理定时器准备执行");
//        Thread thread = new Thread(errorRepairThread);
//        thread.start();
//        logger.info("错误处理定时器开始执行");
    }


}
