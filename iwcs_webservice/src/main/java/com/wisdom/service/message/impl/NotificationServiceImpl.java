package com.wisdom.service.message.impl;

import com.wisdom.service.message.NotificationService;
import com.wisdom.socket.message.SocketMessage;
import com.wisdom.socket.service.SendMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.wisdom.socket.utils.SocketEvent.SEND_MESSAGE;

/**
 * @author Devin
 * @date 2018-05-15.
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Autowired
    SendMessageService sendMessageService;

    @Override
    public void sendSocketMessage(Integer userId, Integer companyId, String message) {
        SocketMessage socketMessage = new SocketMessage();
        socketMessage.setUserId(String.valueOf(userId));
        socketMessage.setCompanyCode(String.valueOf(companyId));
        socketMessage.setEvent(SEND_MESSAGE);
        socketMessage.setMessageContent(message);
        try {
            sendMessageService.sendMessage(socketMessage);
        } catch (Exception e) {
            logger.error("发送socket消息错误");
            e.printStackTrace();
        }

    }
}
