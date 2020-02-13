package com.wisdom.controller.upstream.mes;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.upstream.mes.CreateTaskRequest;
import com.wisdom.iwcs.service.task.impl.TaskCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.TASK_CREATE;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.CHANGCHUN_PToP_TASK_CREATE;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_MES;

/**
 * 长春手持
 * @Author snn
 */
@RestController
@RequestMapping("/api/wisdom/agvHandlingTask")
public class AgvHandlingTaskController {
    @Autowired
    private TaskCreateService taskCreateService;

    @PostMapping("/create")
    @SystemInterfaceLog(methodCode = TASK_CREATE, methodName =CHANGCHUN_PToP_TASK_CREATE, methodThansfer = SRC_MES)
    public Result createTask(@RequestBody CreateTaskRequest createTaskRequest) {
        taskCreateService.pToPTaskCreate(createTaskRequest);
        return new Result();
    }

}
