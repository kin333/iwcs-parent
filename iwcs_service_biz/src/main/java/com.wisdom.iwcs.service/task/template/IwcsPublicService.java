package com.wisdom.iwcs.service.task.template;

import com.wisdom.iwcs.common.utils.InterfaceLogConstants;
import com.wisdom.iwcs.common.utils.NetWorkUtil;
import com.wisdom.iwcs.common.utils.constant.SendStatus;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.task.SubTaskTyp;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskTypMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import com.wisdom.iwcs.service.task.subtask.impl.SubTaskWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

/**
 * iwcs的公共服务
 * @author han
 */
@Service
public class IwcsPublicService {
    private final Logger logger = LoggerFactory.getLogger(IwcsPublicService.class);

    @Autowired
    SubTaskMapper subTaskMapper;
    @Autowired
    SubTaskTypMapper subTaskTypMapper;
    @Autowired
    TemplateRelatedServer templateRelatedServer;
    @Autowired
    ICommonService iCommonService;

    /**
     * 根据子任务单号获取最新子任务信息,并将任务消息体取出并完善,然后发送给第三方
     * @param subTaskNum
     */
    public void sendInfoBySubTaskNum(String subTaskNum) {
        logger.info("子任务{}开始下发process ", subTaskNum);
        // 1. 从数据库获取子任务单
        SubTask subTask = subTaskMapper.selectBySubTaskNum(subTaskNum);
        String jsonStr = "";
        try {
            jsonStr = templateRelatedServer.templateIntoInfo(subTaskNum);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }

        // 2. 根据subtask的值，完善下发的信息，并下发命令
        logger.debug("子任务{}开始完善任务消息体", subTaskNum);
        SubTaskTyp subTaskTyp = subTaskTypMapper.selectByTypeCode(subTask.getSubTaskTyp());
        String resultBody;
        if (InterfaceLogConstants.SrcClientCode.SRC_HIK.equals(subTaskTyp.getWorkerType())) {
            //如果执行者类型是海康,则调用海康的接口
            resultBody = NetWorkUtil.transferContinueTask(jsonStr, subTaskTyp.getWorkerUrl());
            iCommonService.handleHikResponseAndThrowException(resultBody);
        }
        subTaskMapper.updateSendStatus(subTaskNum, SendStatus.SEND.getCode());
    }
}
