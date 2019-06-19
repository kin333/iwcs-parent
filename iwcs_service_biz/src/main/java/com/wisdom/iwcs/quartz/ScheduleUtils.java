package com.wisdom.iwcs.quartz;


import com.wisdom.base.quartz.SpringContextUtils;
import com.wisdom.iwcs.common.utils.Constants;
import com.wisdom.iwcs.common.utils.JSONUtils;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.quartz.QuartzJob;
import com.wisdom.iwcs.service.quartz.ScheduleJob;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;

/**
 * 定时任务工具类
 *
 * @author zcl<yczclcn                                                                                                                                                                                                                                                               @                                                                                                                                                                                                                                                               1                                                                                                                                                                                                                                                               6                                                                                                                                                                                                                                                               3                                                                                                                                                                                                                                                               .                                                                                                                                                                                                                                                               com>
 */
@DependsOn("springContextUtils")
public class ScheduleUtils {
    private static Logger logger = LoggerFactory.getLogger(ScheduleUtils.class);
    private static Scheduler scheduler = (Scheduler) SpringContextUtils.getBean("scheduler");


    private final static String JOB_NAME = "TASK_";

    /**
     * 获取触发器key
     *
     * @param jobId
     * @return
     */
    public static TriggerKey getTriggerKey(Integer jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }

    /**
     * 获取jobKey
     *
     * @param jobId
     * @return
     */
    public static JobKey getJobKey(Integer jobId) {
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     *
     * @param jobId
     * @return
     */
    public static CronTrigger getCronTrigger(Integer jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
//            logger.error("获取定时任务CronTrigger出现异常",e);
            throw new BusinessException("获取定时任务CronTrigger出现异常");
        }
    }

    /**
     * 创建定时任务
     *
     * @param scheduleJob
     */
    public static void createScheduleJob(QuartzJob scheduleJob) {
        try {
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(scheduleJob.getJobId())).build();

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJob.getJobId())).withSchedule(scheduleBuilder).build();

            //放入参数，运行时的方法可以获取
            jobDetail.getJobDataMap().put(QuartzJob.JOB_PARAM_KEY, JSONUtils.beanToJson(scheduleJob));

            scheduler.scheduleJob(jobDetail, trigger);

            //暂停任务
            if (scheduleJob.getStatus().equals(Constants.ScheduleStatus.PAUSE.getValue())) {
                pauseJob(scheduleJob.getJobId());
            }
        } catch (SchedulerException e) {
            logger.error("创建定时任务失败", e);
            throw new BusinessException("创建定时任务失败");
        }
    }

    /**
     * 更新定时任务
     *
     * @param scheduleJob
     */
    public static void updateScheduleJob(QuartzJob scheduleJob) {
        try {
            TriggerKey triggerKey = getTriggerKey(scheduleJob.getJobId());

            //表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                    .withMisfireHandlingInstructionDoNothing();

            CronTrigger trigger = getCronTrigger(scheduleJob.getJobId());

            //按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            //参数
            trigger.getJobDataMap().put(QuartzJob.JOB_PARAM_KEY, JSONUtils.beanToJson(scheduleJob));

            scheduler.rescheduleJob(triggerKey, trigger);

            //暂停任务
            if (scheduleJob.getStatus() == Constants.ScheduleStatus.PAUSE.getValue()) {
                pauseJob(scheduleJob.getJobId());
            }

        } catch (SchedulerException e) {
            logger.error("更新定时任务失败", e);
            throw new BusinessException("更新定时任务失败");
        }
    }

    /**
     * 立即执行任务
     *
     * @param scheduleJob
     */
    public static void run(QuartzJob scheduleJob) {
        try {
            //参数
            JobDataMap dataMap = new JobDataMap();
            dataMap.put(QuartzJob.JOB_PARAM_KEY, JSONUtils.beanToJson(scheduleJob));

            scheduler.triggerJob(getJobKey(scheduleJob.getJobId()), dataMap);
        } catch (SchedulerException e) {
//            logger.error("立即执行定时任务失败",e);
            throw new BusinessException("立即执行定时任务失败");
        }
    }

    /**
     * 暂停任务
     *
     * @param jobId
     */
    public static void pauseJob(Integer jobId) {
        try {
            scheduler.pauseJob(getJobKey(jobId));
        } catch (SchedulerException e) {
//            logger.error("暂停定时任务失败",e);
            throw new BusinessException("暂停定时任务失败");
        }
    }

    /**
     * 恢复任务
     *
     * @param jobId
     */
    public static void resumeJob(Integer jobId) {
        try {
            scheduler.resumeJob(getJobKey(jobId));
        } catch (SchedulerException e) {
//            logger.error("恢复定时任务失败",e);
            throw new BusinessException("恢复定时任务失败");
        }
    }

    /**
     * 删除定时任务
     *
     * @param jobId
     */
    public static void deleteScheduleJob(Integer jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
//            logger.error("删除定时任务失败",e);
            throw new BusinessException("删除定时任务失败");
        }
    }
}
