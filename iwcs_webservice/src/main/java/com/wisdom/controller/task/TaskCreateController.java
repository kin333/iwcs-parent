package com.wisdom.controller.task;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.TaskCreateRequest;
import com.wisdom.iwcs.service.task.intf.ITaskCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务创建
 * @Author george
 * @Date 2019/7/3 8:41
 */
@RestController
@RequestMapping("/api/task")
public class TaskCreateController {
    @Autowired
    private ITaskCreateService taskCreateService;

    /**
     *  搬运任务创建
     * @param
     * @return
     */
    @PostMapping(value = "/create")
    public Result createTask(@RequestBody TaskCreateRequest taskCreateRequest) {
        Result result = taskCreateService.creatTask(taskCreateRequest);
        return result;
    }
}
