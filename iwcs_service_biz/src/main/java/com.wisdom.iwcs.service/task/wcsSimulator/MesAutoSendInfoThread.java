package com.wisdom.iwcs.service.task.wcsSimulator;

import com.wisdom.iwcs.common.utils.NetWorkUtil;
import com.wisdom.iwcs.domain.task.BaseMsgSend;
import com.wisdom.iwcs.mapper.task.BaseMsgSendMapper;
import com.wisdom.iwcs.service.base.ICommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.wisdom.iwcs.common.utils.TaskConstants.subTaskSendStatus.HAS_SEND;

/**
 * MES自动发送信息线程
 * @author han
 */
@Component
public class MesAutoSendInfoThread implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(MesAutoSendInfoThread.class);
    @Autowired
    ICommonService iCommonService;
    @Autowired
    BaseMsgSendMapper baseMsgSendMapper;
    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                try {
                    sendInfo();
                    this.wait(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    logger.info("MES发送报错:", e);
                    try {
                        this.wait(15 * 1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    private void sendInfo() {
        List<BaseMsgSend> baseMsgSendList = baseMsgSendMapper.selectAllNoSend();
        for (BaseMsgSend baseMsgSend : baseMsgSendList) {
            String resultBody = NetWorkUtil.transferContinueTask(baseMsgSend.getSendMsg(), baseMsgSend.getUrl());
            iCommonService.handleMesResponse(resultBody);
            BaseMsgSend tmpBaseMsgSend = new BaseMsgSend();
            tmpBaseMsgSend.setId(baseMsgSend.getId());
            tmpBaseMsgSend.setSendStatus(HAS_SEND);
            tmpBaseMsgSend.setReqMsg(resultBody);
            baseMsgSendMapper.updateByPrimaryKeySelective(tmpBaseMsgSend);
        }
    }
}
