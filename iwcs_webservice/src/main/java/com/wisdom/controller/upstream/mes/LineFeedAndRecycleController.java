package com.wisdom.controller.upstream.mes;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.domain.upstream.mes.MesCancelTaskRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.service.task.impl.MesRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_MES;

/**
 * 产线供料和回收任务的向 MES 系统提供的接口
 * @author han
 */
@RestController
@RequestMapping("/api/wisdom/autoProductionLine/supplyAndRecyle")
public class LineFeedAndRecycleController {
    @Autowired
    MesRequestService mesRequestService;

    /**
     * mes取消任务
     */
    @PostMapping("/cancleTask")
    @SystemInterfaceLog(methodCode = CANCEL_TASK, methodName = CANCEL_TASK_DESC, methodThansfer = SRC_MES)
    public MesResult cancelMesTask(@RequestBody MesCancelTaskRequest mesCancelTaskRequest) {
        return mesRequestService.cancelMesTask(mesCancelTaskRequest);
    }
    /**
     * 顶升式通用的取消任务
     */
    @PostMapping("/publicCancelTask")
    @SystemInterfaceLog(methodCode = PUBLIC_CANCEL_TASK, methodName = PUBLIC_CANCEL_TASK_DESC, methodThansfer = SRC_MES)
    public MesResult publicCancelTask(@RequestBody MesCancelTaskRequest mesCancelTaskRequest) {
        return mesRequestService.publicCancelTask(mesCancelTaskRequest);
    }
    /**
     * mes滚筒换车
     */
    @PostMapping("/changeAgv")
    @SystemInterfaceLog(methodCode = CHANGE_AGV, methodName = CHANGE_AGV_DESC, methodThansfer = SRC_MES)
    public MesResult changeAgv(@RequestBody MesCancelTaskRequest mesCancelTaskRequest) {
        return mesRequestService.changeAgv(mesCancelTaskRequest);
    }



}
