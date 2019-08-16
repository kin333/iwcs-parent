package com.wisdom.iwcs.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.wisdom.base.context.ApplicationProperties;
import com.wisdom.iwcs.common.utils.NetWorkUtil;
import com.wisdom.iwcs.domain.hikSync.HikFiniTask;
import com.wisdom.iwcs.domain.task.EleControlTask;
import com.wisdom.iwcs.domain.task.dto.TempdateRelatedContext;
import com.wisdom.iwcs.mapper.elevator.EleControlTaskMapper;
import com.wisdom.iwcs.service.base.baseImpl.CommonService;
import com.wisdom.iwcs.service.task.template.TemplateRelatedServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wisdom.iwcs.common.utils.TaskConstants.yesOrNo.YES;

@Service
public class CheckEleArrivedService {
    private static final Logger logger = LoggerFactory.getLogger(CheckEleArrivedService.class);
    @Autowired
    EleControlTaskMapper eleControlTaskMapper;
    @Autowired
    ApplicationProperties applicationProperties;
    @Autowired
    CommonService commonService;
    @Autowired
    TemplateRelatedServer templateRelatedServer;


    /**
     * 检查电梯是否到达目标楼层
     * @return
     */
    private boolean checkEleArrived(String eleTaskCode) {
        EleControlTask eleControlTask = eleControlTaskMapper.selectTaskInfo(eleTaskCode);
        return YES.equals(eleControlTask.getPlcNotifyEntryDest());
    }

    /**
     * 根据json字符串和url调用海康接口,并提取其中的data属性数据
     * 通知电梯到达的接口
     * @param jsonStr
     * @return
     */
    private void notifyHikEleArrived(String jsonStr) {

        String url = applicationProperties.getHikParam().getFiniTask();
        //调用海康接口
        String resultBody = NetWorkUtil.transferContinueTask(jsonStr, url);
        commonService.handleHikResponseAndThrowException(resultBody);
    }

    /**
     * 向Hik发送信息
     * @param eleTaskCode 电梯任务号
     * @param instantLocation 门开时所在的楼层
     */
    public void notifyAgv(String eleTaskCode, String instantLocation, String subTaskNum) {
        while (true) {
            if (checkEleArrived(eleTaskCode)) {
                TempdateRelatedContext template = templateRelatedServer.getRequestInfo();
                HikFiniTask hikFiniTask = new HikFiniTask();
                hikFiniTask.setClientCode(template.getClientCode());
                hikFiniTask.setReqCode(template.getReqCode());
                hikFiniTask.setReqTime(template.getReqTime());
                hikFiniTask.setTokenCode(template.getTokenCode());
                hikFiniTask.setLiftId("01");
                hikFiniTask.setTaskDetailKey(subTaskNum);
                hikFiniTask.setInstantLocation(instantLocation);
                String jsonStr = JSON.toJSONString(hikFiniTask);
                notifyHikEleArrived(jsonStr);
                break;
            } else {
                try {
                    Thread.sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
