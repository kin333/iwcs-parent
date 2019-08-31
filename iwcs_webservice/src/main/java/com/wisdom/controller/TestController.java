package com.wisdom.controller;

import com.wisdom.iwcs.common.utils.JSONUtils;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.taskUtils.TaskContextUtils;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.task.dto.ContextDTO;
import com.wisdom.iwcs.domain.upstream.mes.MesBaseRequest;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.service.common.IPodCal;
import com.wisdom.iwcs.service.message.NotificationService;
import com.wisdom.iwcs.service.notice.NoticeUserMsgService;
import com.wisdom.iwcs.service.task.impl.SubTaskService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    SubTaskService subTaskService;
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    IPodCal podCal;

    @GetMapping
    public Result testSocketSendMessage() {
        noticeUserMsgService.sendNoticeToUser(1, 1, "测试发送消息", "测试发送消息");
        return new Result();
    }

    @GetMapping(value = "/testPodCa")
    public Result testPodCalImpl(MesBaseRequest<Object> mesBaseRequest) {
        List<String> listPodcode = podCal.calPodByPodFliterCondition("sss", "sss", "ssss", 10);
        return new Result(listPodcode);
    }

    @PostMapping(value = "/testContextUtils")
    public Result testContextUtils(@RequestBody ContextDTO dto) {
        String json = JSONUtils.beanToJson(dto);
        json = "{\n" +
                "\t\"startBerCode\": \"startBerCode\"\n" +
                "}";
        ContextDTO contextDTO = TaskContextUtils.jsonToObject(json, ContextDTO.class);
        System.out.println(contextDTO);
        String s = TaskContextUtils.objectToJson(contextDTO);
        System.out.println(s);
        return new Result(s);
    }

    @GetMapping("/testAutoCreate/{mainTaskNum}")
    public Result testAutoCreate(@PathVariable String mainTaskNum) {
        subTaskService.autoCreateSubTask("testTemp", mainTaskNum);
        return new Result();
    }
    @GetMapping("/addPointAlias")
    public Result addPointAlias() {
        Integer id = baseMapBerthMapper.selectFirst();
        Integer i = 110;
        while (id != null) {
            BaseMapBerth baseMapBerth = new BaseMapBerth();
            baseMapBerth.setId(id);
            baseMapBerth.setPointAlias(i.toString());
            baseMapBerthMapper.updateByPrimaryKeySelective(baseMapBerth);
            id = baseMapBerthMapper.selectFirst();
            i++;
        }

        return new Result();
    }



}
