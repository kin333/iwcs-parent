package com.wisdom.controller.quartz;


import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.quartz.QuartzJob;
import com.wisdom.iwcs.service.quartz.IQuartzJobManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 定时任务
 *
 * @author zcl<yczclcn                                                                                                                                                                                                                                                               @                                                                                                                                                                                                                                                               1                                                                                                                                                                                                                                                               6                                                                                                                                                                                                                                                               3                                                                                                                                                                                                                                                               .                                                                                                                                                                                                                                                               com>
 */
@RestController
@RequestMapping("/quartz/job")
public class QuartzJobManagementController {

    @Autowired
    private IQuartzJobManagementService quartzJobService;

    /**
     * 获取任务列表
     *
     * @return
     */
    @PostMapping("/list")
    public List<QuartzJob> getList() {
        return quartzJobService.getList();
    }

    /**
     * 新增任务
     *
     * @param job
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody QuartzJob job) {
        return quartzJobService.saveQuartzJob(job);
    }

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @PostMapping("/info")
    public Result info(@RequestBody Integer id) {
        return quartzJobService.getQuartzJobById(id);
    }

    /**
     * 修改任务
     *
     * @param job
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody QuartzJob job) {
        return quartzJobService.updateQuartzJob(job);
    }

    /**
     * 删除定时任务
     *
     * @param id
     * @return
     */
    @PostMapping("/remove")
    public Result remove(@RequestBody Integer[] id) {
        return quartzJobService.batchRemoveQuartzJob(id);
    }

    /**
     * 立即运行
     *
     * @param id
     * @return
     */
    @PostMapping("/run")
    public Result run(@RequestBody Integer[] id) {
        return quartzJobService.run(id);
    }

    /**
     * 暂停任务
     *
     * @param id
     * @return
     */
    @PostMapping("/disable")
    public Result pause(@RequestBody Integer[] id) {
        return quartzJobService.pause(id);
    }

    /**
     * 启用任务
     *
     * @param id
     * @return
     */
    @PostMapping("/enable")
    public Result resume(@RequestBody Integer[] id) {
        return quartzJobService.resume(id);
    }


}
