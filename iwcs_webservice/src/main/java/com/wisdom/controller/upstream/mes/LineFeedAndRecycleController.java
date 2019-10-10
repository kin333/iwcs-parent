package com.wisdom.controller.upstream.mes;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.common.utils.PaginationUtil;
import com.wisdom.iwcs.common.utils.exception.MesBusinessException;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.upstream.mes.*;
import com.wisdom.iwcs.domain.upstream.mes.chaoyue.ReportEmptyContainerNumber;
import com.wisdom.iwcs.domain.upstream.mes.chaoyue.SupllyUnload;
import com.wisdom.iwcs.service.task.impl.MesRequestService;
import com.wisdom.iwcs.service.task.impl.TaskCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.*;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_IWCS;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_MES;
import static com.wisdom.iwcs.common.utils.TaskConstants.taskCodeType.SUPPLYANDRECYCLE;

/**
 * 产线供料和回收任务的向 MES 系统提供的接口
 * @author han
 */
@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/api/wisdom/autoProductionLine/supplyAndRecyle")
public class LineFeedAndRecycleController {
    @Autowired
    TaskCreateService taskCreateService;
    @Autowired
    MesRequestService mesRequestService;

    /**
     * 创建自动产线供料、回收任务
     */
    @PostMapping
    @SystemInterfaceLog(methodCode = TASK_CREATE, methodName = MOVE_TASK_CREATE, methodThansfer = SRC_MES)
    public MesResult taskCreate(@RequestBody MesBaseRequest<List<CreateTaskRequest>> mesBaseRequest) {
        List<CreateTaskRequest> data = mesBaseRequest.getData();
        for (CreateTaskRequest createTaskRequest : data) {
            taskCreateService.supplyAndRecycle(createTaskRequest, SUPPLYANDRECYCLE, mesBaseRequest.getReqcode());
        }
        return new MesResult(mesBaseRequest.getReqcode());
    }

    /**
     * 通知AGV上料数量
     */
    @PostMapping("/supplyUnloadNum")
    @SystemInterfaceLog(methodCode = SUPPLY_LOAD_NUM, methodName = SUPPLY_LOAD_NUM_DESC, methodThansfer = SRC_MES)
    public MesResult supplyLoadNum(@RequestBody MesBaseRequest<SupplyLoadNumNotify> mesBaseRequest) {
        SupplyLoadNumNotify data = mesBaseRequest.getData();
        mesRequestService.supplyLoadNum(data, mesBaseRequest.getReqcode());
        return new MesResult(mesBaseRequest.getReqcode());
    }
    /**
     * 通知AGV接料点目的地
     */
    @PostMapping("/supplyUnloadWb/notify")
    @SystemInterfaceLog(methodCode = SUPPLY_UNLOADWB_NOTIFY, methodName = SUPPLY_UNLOADWB_NOTIFY_DESC, methodThansfer = SRC_MES)
    public MesResult supplyUnloadWbNotify(@RequestBody MesBaseRequest<SupplyInfoNotify> mesBaseRequest) {
        SupplyInfoNotify data = mesBaseRequest.getData();
        mesRequestService.supplyUnloadWbNotify(data, mesBaseRequest.getReqcode());
        return new MesResult(mesBaseRequest.getReqcode());
    }

    /**
     * 接料点通知供料及回收空框信息
     */
    @PostMapping("/startSupllyAndRecyle")
    @SystemInterfaceLog(methodCode = START_SUPLLY_AND_RECYLE, methodName = START_SUPLLY_AND_RECYLE_DESC, methodThansfer = SRC_MES)
    public MesResult startSupllyAndRecyle(@RequestBody MesBaseRequest<StartSupllyAndRecyle> mesBaseRequest) {
        StartSupllyAndRecyle data = mesBaseRequest.getData();
        mesRequestService.startSupllyAndRecyle(data, mesBaseRequest.getReqcode());
        return new MesResult(mesBaseRequest.getReqcode());
    }

    /**
     * 通知可出空料框
     */
    @PostMapping("/startRecyle")
    @SystemInterfaceLog(methodCode = START_RECYLE, methodName = START_RECYLE_DESC, methodThansfer = SRC_MES)
    public MesResult startRecyle(@RequestBody MesBaseRequest<StartRecyle> mesBaseRequest) {
        StartRecyle data = mesBaseRequest.getData();
        mesRequestService.startRecyle(data, mesBaseRequest.getReqcode());
        return new MesResult(mesBaseRequest.getReqcode());
    }

    /**
     * 通知小车可离开机台
     */
    @PostMapping("/checkSuccess")
    @SystemInterfaceLog(methodCode = CHECK_SUCCESS, methodName = CHECK_SUCCESS_DESC, methodThansfer = SRC_MES)
    public MesResult checkSuccess(@RequestBody MesBaseRequest<NotifyAgvLeave> mesBaseRequest) {
        NotifyAgvLeave data = mesBaseRequest.getData();
        mesRequestService.checkSuccess(data, mesBaseRequest.getReqcode());
        return new MesResult(mesBaseRequest.getReqcode());
    }

    /**
     * 超越 上报已下料数量及已接收空框数量
     */
    @PostMapping("/supllyAndRecyleResult")
    @SystemInterfaceLog(methodCode = SUPLLY_AND_RECYLE_RESULT, methodName = SUPLLY_AND_RECYLE_RESULT_DESC, methodThansfer = SRC_MES)
    public MesResult supllyAndRecyleResult(@RequestBody MesBaseRequest<ReportEmptyContainerNumber> mesBaseRequest) {

        ReportEmptyContainerNumber data = mesBaseRequest.getData();
        mesRequestService.supllyAndRecyleResult(data, mesBaseRequest.getReqcode());
        return new MesResult(mesBaseRequest.getReqcode());
    }

    /**
     * 超越 通知AGV是否可以离开
     */
    @PostMapping("/supllyUnload/SupllyAndRecyleResult")
    @SystemInterfaceLog(methodCode = SUPLLY_UNLOAD, methodName = SUPPLY_LOAD_NUM_DESC, methodThansfer = SRC_MES)
    public MesResult supllyUnload(@RequestBody MesBaseRequest<SupllyUnload> mesBaseRequest) {

        SupllyUnload data = mesBaseRequest.getData();
        mesRequestService.supllyUnload(data, mesBaseRequest.getReqcode());
        return new MesResult(mesBaseRequest.getReqcode());
    }
}
