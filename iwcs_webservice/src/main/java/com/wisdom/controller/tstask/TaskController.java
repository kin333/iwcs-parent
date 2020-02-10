package com.wisdom.controller.tstask;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.domain.task.CreateMainTaskRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.service.task.impl.TaskCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.PUBLIC_TASK_CREATE;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.PUBLIC_TASK_CREATE_DECS;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_IWCS;

/**
 * 任务管理接口
 */
@RestController
@RequestMapping("/api/task/main_task")
public class TaskController {

    @Autowired
    TaskCreateService taskCreateService;
    /**
     * 通用的创建主任务接口
     */
    @PostMapping
    @SystemInterfaceLog(methodCode = PUBLIC_TASK_CREATE, methodName = PUBLIC_TASK_CREATE_DECS, methodThansfer = SRC_IWCS)
    public MesResult publicTaskCreate(@RequestBody CreateMainTaskRequest createMainTaskRequest) {
        taskCreateService.publicTaskCreate(createMainTaskRequest);
        return new MesResult(createMainTaskRequest.getReqCode());
    }
}