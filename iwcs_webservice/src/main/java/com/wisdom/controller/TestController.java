package com.wisdom.controller;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.service.common.IPodCal;
import com.wisdom.service.message.NotificationService;
import com.wisdom.service.notice.NoticeUserMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Devin
 * @date 2018-05-15.
 */
@RestController
@RequestMapping("/api/test/devin")
public class TestController {

    @Autowired
    NotificationService notificationService;
    @Autowired
    NoticeUserMsgService noticeUserMsgService;
    @Autowired
    IPodCal podCal;

    @GetMapping
    public Result testSocketSendMessage() {
        noticeUserMsgService.sendNoticeToUser(1, 1, "测试发送消息", "测试发送消息");
        return new Result();
    }

    @GetMapping(value = "/testPodCa")
    public Result testPodCalImpl() {
        List<String> listPodcode = podCal.calPodByPodFliterCondition("sss", "sss", "ssss", 10);
        return new Result(listPodcode);
    }
}
