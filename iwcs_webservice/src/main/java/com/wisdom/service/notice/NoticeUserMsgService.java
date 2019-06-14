package com.wisdom.service.notice;

/**
 * Created by centling on 2018/6/15.
 */
public interface NoticeUserMsgService {

    void sendNoticeToUser(Integer receiveUserId, Integer receiveCompanyId, String title, String content);

    void sendSystemNotice(String title, String content);
}
