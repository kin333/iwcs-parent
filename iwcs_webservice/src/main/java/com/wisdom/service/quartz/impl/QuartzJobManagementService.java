package com.wisdom.service.quartz.impl;

import com.wisdom.iwcs.common.utils.Constants;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.quartz.QuartzJob;
import com.wisdom.iwcs.mapper.quartz.QuartzJobMapper;
import com.wisdom.quartz.ScheduleUtils;
import com.wisdom.service.quartz.IQuartzJobManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 定时任务
 *
 * @author zcl<yczclcn                                                                                                                                                                                                                                                               @                                                                                                                                                                                                                                                               1                                                                                                                                                                                                                                                               6                                                                                                                                                                                                                                                               3                                                                                                                                                                                                                                                               .                                                                                                                                                                                                                                                               com>
 */
@DependsOn("springContextUtils")
@Service("quartzJobServiceImpl")
public class QuartzJobManagementService implements IQuartzJobManagementService {

    @Autowired
    private QuartzJobMapper quartzJobMapper;

    /**
     * 项目启动，初始化任务
     */
    @PostConstruct
    public void init() {
        List<QuartzJob> jobList = quartzJobMapper.selectAll();
        for (QuartzJob job : jobList) {
            org.quartz.CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(job.getJobId());
            //如果不存在，则创建
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(job);
            } else {
                ScheduleUtils.updateScheduleJob(job);
            }
        }
    }

    /**
     * 获取任务列表
     *
     * @return
     */
    @Override
    public List<QuartzJob> getList() {
        List<QuartzJob> list = quartzJobMapper.selectAll();

        return list;
    }

    /**
     * 新增任务
     *
     * @param job
     * @return
     */
    @Override
    public Result saveQuartzJob(QuartzJob job) {
        job.setStatus(Constants.ScheduleStatus.NORMAL.getValue());
        int count = quartzJobMapper.insertSelective(job);
        ScheduleUtils.createScheduleJob(job);
        return new Result();
    }

    /**
     * 根据id查询任务
     *
     * @param jobId
     * @return
     */
    @Override
    public Result getQuartzJobById(Integer jobId) {
        QuartzJob job = quartzJobMapper.selectByPrimaryKey(jobId);
        return new Result();
    }

    /**
     * 更新任务
     *
     * @param job
     * @return
     */
    @Override
    public Result updateQuartzJob(QuartzJob job) {
        int count = quartzJobMapper.updateByPrimaryKeySelective(job);
        job = quartzJobMapper.selectByPrimaryKey(job.getJobId());
        ScheduleUtils.updateScheduleJob(job);
        return new Result();
    }

    /**
     * 批量删除任务
     *
     * @param id
     * @return
     */
    @Override
    public Result batchRemoveQuartzJob(Integer[] id) {
        for (Integer jobId : id) {
            ScheduleUtils.deleteScheduleJob(jobId);
            int count = quartzJobMapper.deleteByPrimaryKey(jobId);
        }

        return new Result();
    }

    /**
     * 立即运行任务
     *
     * @param id
     * @return
     */
    @Override
    public Result run(Integer[] id) {
        for (Integer jobId : id) {
            ScheduleUtils.run(quartzJobMapper.selectByPrimaryKey(jobId));
        }
        return new Result();
    }

    /**
     * 暂停任务
     *
     * @param id
     * @return
     */
    @Override
    public Result pause(Integer[] id) {
        for (Integer jobId : id) {
            ScheduleUtils.pauseJob(jobId);
            QuartzJob job = quartzJobMapper.selectByPrimaryKey(jobId);
            job.setStatus(Constants.ScheduleStatus.PAUSE.getValue());
            int count = quartzJobMapper.updateByPrimaryKeySelective(job);

        }

        return new Result();
    }

    /**
     * 恢复任务
     *
     * @param id
     * @return
     */
    @Override
    public Result resume(Integer[] id) {
        for (Integer jobId : id) {
            ScheduleUtils.resumeJob(jobId);
            QuartzJob job = quartzJobMapper.selectByPrimaryKey(jobId);
            job.setStatus(Constants.ScheduleStatus.NORMAL.getValue());
            int count = quartzJobMapper.updateByPrimaryKeySelective(job);
        }

        return new Result();
    }

}
