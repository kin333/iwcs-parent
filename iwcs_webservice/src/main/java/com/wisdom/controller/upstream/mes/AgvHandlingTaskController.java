package com.wisdom.controller.upstream.mes;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.upstream.mes.AgvHandlingTaskCreateRequest;
import com.wisdom.iwcs.domain.upstream.mes.ConWaitToDestWbRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesBaseRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.service.task.impl.MesRequestService;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.CONWAIT_TO_DESTWB;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.CREATE_TASK;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.CONWAIT_TO_DESTWB_DESC;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.CREATE_TASK_DESC;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_MES;

/**
 * 工产线、测试区、叉车 agv搬运
 * @Author george
 * @Date 2019/8/27 14:08
 */
@RestController
@RequestMapping("/api/wisdom/agvHandlingTask")
public class AgvHandlingTaskController {
    @Autowired
    private MesRequestService mesRequestService;
    @Autowired
    private ITaskCreateService iTaskCreateService;

    /**
     * 创建任务
     * @param
     * @return
     */
    @PostMapping
    @SystemInterfaceLog(methodCode = CREATE_TASK, methodName = CREATE_TASK_DESC, methodThansfer = SRC_MES)
    public MesResult createTask(@RequestBody MesBaseRequest<List<AgvHandlingTaskCreateRequest>> mesBaseRequest) {
        List<AgvHandlingTaskCreateRequest> data = mesBaseRequest.getData();
        for (AgvHandlingTaskCreateRequest agvHandlingTaskCreateRequest : data) {
            iTaskCreateService.agvHandlingTaskCreate(agvHandlingTaskCreateRequest, mesBaseRequest.getReqcode());
        }
        return new MesResult(mesBaseRequest.getReqcode());
    }


    /**
     * 通知Agv可从等待点前往终点
     * @param
     * @return 
     */
    @PostMapping("/conWaitToDestWb")
    @SystemInterfaceLog(methodCode = CONWAIT_TO_DESTWB, methodName = CONWAIT_TO_DESTWB_DESC, methodThansfer = SRC_MES)
    public MesResult conWaitToDestWb(@RequestBody MesBaseRequest<ConWaitToDestWbRequest> mesBaseRequest) {
        ConWaitToDestWbRequest data = mesBaseRequest.getData();
        try {
            mesRequestService.conWaitToDestWb(data, mesBaseRequest.getReqcode());
        } catch (BusinessException e) {
            e.printStackTrace();
            return new MesResult(MesResult.NG, e.getMessage(), mesBaseRequest.getReqcode());
        }
        return new MesResult(mesBaseRequest.getReqcode());
    }
}
