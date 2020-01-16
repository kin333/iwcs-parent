package com.wisdom.iwcs.service.task.wcsSimulator;

import com.wisdom.iwcs.common.utils.RabbitMQUtil;
import com.wisdom.iwcs.domain.system.Dictionary;
import com.wisdom.iwcs.domain.task.SubTaskAction;
import com.wisdom.iwcs.mapper.system.DictionaryMapper;
import com.wisdom.iwcs.mapper.task.SubTaskActionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.wisdom.iwcs.common.utils.TaskConstants.actionStatus.CREATE;

/**
 * 节点通知调度发送程序
 * @author han
 */
@Component
public class NodeActionSendThread implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(NodeActionSendThread.class);
    @Autowired
    SubTaskActionMapper subTaskActionMapper;
    @Autowired
    DictionaryMapper dictionaryMapper;
    /**
     * 等待时间
     */
    private long waitTime = 10;
    /**
     * 重试次数
     */
    private int retryNum = 1;

    @Override
    public void run() {
        //获取重试时间
        Dictionary actionRetryTime = dictionaryMapper.selectByDictName("ACTION_RETRY_TIME");
        if (actionRetryTime != null) {
            long actionWaitTime = Long.parseLong(actionRetryTime.getDictValue());
            if (actionWaitTime > 0) {
                waitTime = actionWaitTime;
            }
        }
        //获取重试次数
        Dictionary actionRetryNum = dictionaryMapper.selectByDictName("ACTION_RETRY_NUM");
        if (actionRetryNum != null) {
            int actionNum = Integer.parseInt(actionRetryNum.getDictValue());
            if (actionNum > 0) {
                retryNum = actionNum;
            }
        }
        synchronized (this) {
            while (true) {
                try {
                    logger.info("开始节点通知调度程序");
                    nodeActionSend();
                    this.wait(waitTime * 1000);
                } catch (InterruptedException e) {
                    logger.info("节点通知调度程序已关闭");
                    break;
                } catch (Exception e) {
                    logger.info("MES发送报错:", e);
                    try {
                        this.wait(waitTime * 1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * 遍历查询所有的未发送的节点通知,然后将id发送给RabbitMQ的消费者
     */
    private void nodeActionSend() {
        //未发送的
        List<Long> idListNoSend = subTaskActionMapper.selectIdNoSend();
        //发送失败需要重发的
        List<Long> idListNoSendSuccess = subTaskActionMapper.selectIdNoSendSuccess(retryNum);
        //异常检查,防止RabbitMQ管理插件统计信息集合中的错误导致的消息队列不可用的问题
        List<Long> errorSend = subTaskActionMapper.selectIdErrorSend();
        logger.info("节点发送数量{}", idListNoSend.size());
        if (idListNoSend.size() > 0) {
            for (Long id : idListNoSend) {
                logger.info("节点发送ID{}", id);
                RabbitMQUtil.basicPublicNodeAction(id.toString());
            }
            subTaskActionMapper.updateStatusByIds(idListNoSend);
        }
        if (idListNoSendSuccess.size() > 0) {
            for (Long id : idListNoSendSuccess) {
                RabbitMQUtil.basicPublicNodeAction(id.toString());
            }
            subTaskActionMapper.updateStatusByIds(idListNoSendSuccess);
        }
        if (errorSend.size() > 0) {
            for (Long id : errorSend) {
                RabbitMQUtil.basicPublicNodeAction(id.toString());
            }
        }
    }
}
