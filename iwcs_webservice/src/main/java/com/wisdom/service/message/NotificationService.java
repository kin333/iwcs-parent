package com.wisdom.service.message;

/**
 * @author Devin
 * @date 2018-05-15.
 */
public interface NotificationService {

    void sendSocketMessage(Integer userId, Integer companyId, String message);
}
