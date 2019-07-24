package com.wisdom.iwcs.service.line;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 线控 service
 * @Author george
 * @Date 2019/7/16 16:17 
 */
public class LineNotifyService {
    private final Logger logger = LoggerFactory.getLogger(LineNotifyService.class);

    /**
     * 线体通知WCS 呼叫空货架
     * @param
     * @return
     */
    public void lineCallEmptyPod(){
        //接收到信号，拆分，获取点位

        //查询这点有货架或没有完结的任务没
        //更新sub_task_hander

    }

    /**
     * 线体上报WCS 搬走货架
     * @param
     * @return
     */
    public void lineCallAgvPickPod(){
        //接收到信号，拆分，获取点位

        //查询这点有货架没
        //创建线体到老化区任务
        //目标点，2楼全自动，随机选，3楼自动区域

    }

    /**
     * Agv搬运货架到达线体工作点
     * @param
     * @return
     */
    public void agvArriveLine(){
        //通知线体

        //更新任务状态
        //更新点位信息
    }

    /**
     * Agv搬运货架离开线体工作点
     * @param
     * @return
     */
    public void agvPickLine(){
        //通知线体
        //更新任务状态
        //更新点位信息
    }
}
