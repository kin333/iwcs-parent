package com.wisdom.controller.upstream.mes;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.domain.upstream.mes.CreateTaskRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesBaseRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.service.task.impl.TaskCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.TASK_CREATE;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.PToP_TASK_CREATE;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_MES;

/**
 * 超越点到点搬运 主任务接口
 */

@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/api/wisdom/pToPHandlingTask")
public class PToPHandlingTaskController {

    @Autowired
    TaskCreateService taskCreateService;

    /**
     * 超越点到点搬运 创建主任务接口 根据任务类型确定不同的任务
     */
    @PostMapping
    @SystemInterfaceLog(methodCode = TASK_CREATE, methodName =PToP_TASK_CREATE, methodThansfer = SRC_MES)
    public MesResult taskCreate(@RequestBody MesBaseRequest<List<CreateTaskRequest>> mesBaseRequest) {
        List<CreateTaskRequest> data = mesBaseRequest.getData();
        for (CreateTaskRequest createTaskRequest : data) {
            taskCreateService.pToPHandlingTask(createTaskRequest,mesBaseRequest.getReqcode());
        }
        return new MesResult(mesBaseRequest.getReqcode());
    }



}
