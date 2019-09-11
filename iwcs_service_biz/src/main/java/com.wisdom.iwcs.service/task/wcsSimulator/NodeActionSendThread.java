package com.wisdom.iwcs.service.task.wcsSimulator;

import com.wisdom.iwcs.common.utils.RabbitMQUtil;
import com.wisdom.iwcs.mapper.task.SubTaskActionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 节点通知调度发送程序
 * @author han
 */
@Component
public class NodeActionSendThread implements Runnable {
    private final Logger logger = LoggerFactory.getLogger(NodeActionSendThread.class);
    @Autowired
    SubTaskActionMapper subTaskActionMapper;

    @Override
    public void run() {
        synchronized (this) {
            while (true) {
                try {
                    logger.info("开始滚筒通知调度程序");
                    nodeActionSend();
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

    /**
     * 遍历查询所有的未发送的节点通知,然后将id发送给RabbitMQ的消费者
     */
    private void nodeActionSend() {
        List<Long> idList = subTaskActionMapper.selectIdNoSend();
        if (idList.size() > 0) {
            for (Long id : idList) {
                RabbitMQUtil.basicPublicNodeAction(id.toString());
            }
        }
    }
}
