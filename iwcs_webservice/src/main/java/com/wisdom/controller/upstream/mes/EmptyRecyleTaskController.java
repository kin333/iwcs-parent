package com.wisdom.controller.upstream.mes;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.domain.upstream.mes.CreateTaskRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesBaseRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.domain.upstream.mes.StartRecyle;
import com.wisdom.iwcs.service.task.impl.MesRequestService;
import com.wisdom.iwcs.service.task.impl.TaskCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.START_RECYLE;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.TASK_CREATE;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.MOVE_TASK_CREATE;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.START_RECYLE_DESC;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_MES;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.EMPTYRECYCLETASK;

/**
 * 自动化产线空箱回收任务的向 MES 系统提供的接口
 * @author han
 */
@RestController
@RequestMapping("/api/wisdom/autoProductionLine/emptyRecyleTask")
public class EmptyRecyleTaskController {
    @Autowired
    TaskCreateService taskCreateService;
    @Autowired
    MesRequestService mesRequestService;

    /**
     * 创建自动产线回收任务
     * @return
     */
    @PostMapping
    @SystemInterfaceLog(methodCode = TASK_CREATE, methodName = MOVE_TASK_CREATE, methodThansfer = SRC_MES)
    public MesResult taskCreate(@RequestBody MesBaseRequest<List<CreateTaskRequest>> mesBaseRequest) {
        List<CreateTaskRequest> data = mesBaseRequest.getData();
        for (CreateTaskRequest createTaskRequest : data) {
            taskCreateService.emptyRecyleTask(createTaskRequest, EMPTYRECYCLETASK);
        }
        return new MesResult(mesBaseRequest.getReqcode());
    }

    /**
     * 通知可出空料框
     */
    @PostMapping("/startRecyle")
    @SystemInterfaceLog(methodCode = START_RECYLE, methodName = START_RECYLE_DESC, methodThansfer = SRC_MES)
    public MesResult startRecyle(@RequestBody MesBaseRequest<StartRecyle> mesBaseRequest) {
        StartRecyle data = mesBaseRequest.getData();
        mesRequestService.startRecyle(data);
        return new MesResult(mesBaseRequest.getReqcode());
    }
}
