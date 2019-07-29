package com.wisdom.iwcs.service.task.wcsSimulator;

import com.google.common.base.Strings;
import com.wisdom.base.context.AppContext;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.BusinessException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class QuaAutoCallPodWorker implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(QuaAutoCallPodWorker.class);

    private String mapCode;

    public QuaAutoCallPodWorker() {

    }

    public QuaAutoCallPodWorker(String mapCode) {
        this.mapCode = mapCode;
    }

    public void checkEmptyQua() {
        if(Strings.isNullOrEmpty(this.mapCode)) {
            logger.error("模拟老化区到检验区线程失败,原因：缺少地图代码");
            throw new BusinessException("缺少地图代码");
        }
        QuaAutoCallPodService quaAutoCallPodService = (QuaAutoCallPodService) AppContext.getBean("quaAutoCallPodService");
        Result result=quaAutoCallPodService.checkEmptyQua(this.mapCode);
        if(result.getReturnCode() != 200) {
            logger.error(result.getReturnMsg());
        }

    }

    @Override
    public void run() {
        while (true) {

            try {
                //检验八个检验点和两个检验点缓冲区是否有空点，如果有创建一个老化区去检验区的任务
                this.checkEmptyQua();

                synchronized (this) {
                    logger.info("创建老化区货架到检验区调度器线程主动随眠60*1000*1");
                    this.wait(60 * 1000 * 1);
                }

            } catch (Exception e) {
                logger.error("创建老化区货架到检验区主任务调度器线程尝试休眠失败！");
                e.printStackTrace();
            }
        }
    }
}
