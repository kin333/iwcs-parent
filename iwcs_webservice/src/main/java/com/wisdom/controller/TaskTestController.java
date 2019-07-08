package com.wisdom.controller;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.service.task.maintask.MainTaskWorker;
import com.wisdom.iwcs.service.task.scheduler.WcsTaskScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Devin
 * @date 2018-05-15.
 */
@RestController
@RequestMapping("/api/test/wcsTask")
public class TaskTestController {
    @Autowired
    private WcsTaskScheduler wcsTaskScheduler;
    @Autowired
    private MainTaskMapper mainTaskMapper;


    @GetMapping("/startWcsTaskScheduler")
    public Result startWcsTaskScheduler() {
        wcsTaskScheduler.dispatchMaintask();
        return new Result();
    }


    @GetMapping("/startMainTask")
    public Result startSubtask(Long mainTaskId) {
        MainTask mainTask = mainTaskMapper.selectByPrimaryKey(mainTaskId);
        if (mainTask != null) {
            MainTaskWorker mainTaskWorker = new MainTaskWorker(null, mainTask);
            Thread thread = new Thread(mainTaskWorker);
            thread.start();
            return new Result("任务已启动");
        } else {
            return new Result(99, "无效的主任务id");
        }
    }


}
