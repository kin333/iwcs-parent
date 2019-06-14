package com.wisdom.service.quartz;


import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.quartz.QuartzJob;

import java.util.List;

/**
 * 定时任务
 *
 * @author zcl<yczclcn                                                                                                                                                                                                                                                               @                                                                                                                                                                                                                                                               1                                                                                                                                                                                                                                                               6                                                                                                                                                                                                                                                               3                                                                                                                                                                                                                                                               .                                                                                                                                                                                                                                                               com>
 */
public interface IQuartzJobManagementService {
    /**
     * 获取任务列表
     *
     * @return
     */
    List<QuartzJob> getList();


    /**
     * 新增任务
     *
     * @param job
     * @return
     */
    Result saveQuartzJob(QuartzJob job);

    /**
     * 根据id查询任务
     *
     * @param jobId
     * @return
     */
    Result getQuartzJobById(Integer jobId);

    /**
     * 修改任务
     *
     * @param job
     * @return
     */
    Result updateQuartzJob(QuartzJob job);

    /**
     * 批量删除任务
     *
     * @param id
     * @return
     */
    Result batchRemoveQuartzJob(Integer[] id);

    /**
     * 立即运行任务
     *
     * @param id
     * @return
     */
    Result run(Integer[] id);

    /**
     * 暂停任务
     *
     * @param id
     * @return
     */
    Result pause(Integer[] id);

    /**
     * 恢复任务
     *
     * @param id
     * @return
     */
    Result resume(Integer[] id);


}
