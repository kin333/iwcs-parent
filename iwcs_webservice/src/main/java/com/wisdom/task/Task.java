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


import com.wisdom.iwcs.service.codec.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author ted.ma
 * @version <br>
 * <p>用印申请定时任务</p>
 */
@Component
@Transactional
@Service("taskService")
public class Task {

    @Autowired
    SequenceService sequenceService;


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


}
