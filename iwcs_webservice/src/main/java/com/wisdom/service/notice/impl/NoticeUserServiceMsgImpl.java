package com.wisdom.service.notice.impl;

import com.github.pagehelper.util.StringUtil;
import com.wisdom.iwcs.common.utils.NoticeTypeEnum;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.notice.Notice;
import com.wisdom.iwcs.domain.notice.NoticeUser;
import com.wisdom.iwcs.mapper.notice.NoticeMapper;
import com.wisdom.iwcs.mapper.notice.NoticeUserMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.message.NotificationService;
import com.wisdom.service.notice.NoticeUserMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by centling on 2018/6/15.
 */
@Service
public class NoticeUserServiceMsgImpl implements NoticeUserMsgService {
    @Autowired
    NoticeUserMapper noticeCustomerMapper;

    @Autowired
    NoticeMapper noticeMapper;

    @Autowired
    NotificationService notificationService;

    @Override
    public void sendNoticeToUser(Integer receiveUserId, Integer receiveCompanyId, String title, String content) {
        Integer userId = SecurityUtils.getCurrentUserId();
        Notice notice = new Notice();
        notice.setDeleteFlag(0);
        notice.setContent(content);
        notice.setCreatedTime(new Date());
        if (StringUtil.isNotEmpty(title)) {
            notice.setTitle(title);
        } else {
            notice.setTitle("业务操作消息通知");
        }
        notice.setType(NoticeTypeEnum.PERSON_NOTICE.getCode());
        notice.setCreatedBy(userId);
        noticeMapper.insertNotice(notice);
        // 新增一条通知记录数据
        NoticeUser noticeCustomer = new NoticeUser();
        noticeCustomer.setDeleteFlag(0);
        noticeCustomer.setCreatedTime(new Date());
        noticeCustomer.setStatus(0);
        noticeCustomer.setSendId(userId);
        noticeCustomer.setReceiveId(receiveUserId);
        noticeCustomer.setNoticeId(notice.getId());
        noticeCustomer.setReceiveCompanyId(receiveCompanyId);
        int num = noticeCustomerMapper.insert(noticeCustomer);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        //调用socket发送消息。
        notificationService.sendSocketMessage(receiveUserId, receiveCompanyId, content);
    }

    @Override
    public void sendSystemNotice(String title, String content) {
        Notice notice = new Notice();
        notice.setDeleteFlag(0);
        notice.setContent(content);
        notice.setCreatedTime(new Date());
        notice.setTitle("系统通知");
        notice.setType(NoticeTypeEnum.SYSTEM_NOTICE.getCode());
        //设置为用户表中系统机器人
        notice.setCreatedBy(1);
        noticeMapper.insertNotice(notice);
    }


}
