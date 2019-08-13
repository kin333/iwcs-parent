package com.wisdom.iwcs.service.task.wcsSimulator;

import com.google.common.base.Strings;
import com.wisdom.base.context.AppContext;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class QuaAutoToAgingWorker implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(QuaAutoToAgingWorker.class);

    private String mapCode;


    public QuaAutoToAgingWorker() {

    }
    public QuaAutoToAgingWorker(String mapCode) {
        this.mapCode = mapCode;
    }

    /**
     * 检查检验区的工作点是否有货架，有货架的或创建任务
     */
    public void checkQuaHavePodThenToAging() {

        if(Strings.isNullOrEmpty(this.mapCode)) {
            logger.error("模拟检验区到老化区线程失败,原因：缺少地图代码");
            throw new BusinessException("缺少地图代码");
        }

        QuaAutoToAgingService quaAutoToAgingService = (QuaAutoToAgingService) AppContext.getBean("quaAutoToAgingService");
        Result result = quaAutoToAgingService.checkQuaHavePodThenToAging(this.mapCode);
        if(result.getReturnCode() != 200) {
            logger.error(result.getReturnMsg());
        }


    }

    @Override
    public void run() {
        while (true) {

            try {
                this.checkQuaHavePodThenToAging();
                synchronized (this) {
                    logger.error("创建检验区到老化区调度器线程主动随眠60*1000*2");
                    this.wait(60 * 1000 * 2);
                }

            } catch (InterruptedException e) {
                logger.error("创建检验区到老化区主任务调度器线程尝试休眠失败！");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    this.wait(30 * 1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
