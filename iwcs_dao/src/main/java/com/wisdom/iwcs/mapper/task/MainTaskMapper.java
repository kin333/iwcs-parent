package com.wisdom.iwcs.mapper.task;


import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.system.ViewData;
import com.wisdom.iwcs.domain.task.MainTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-06-25 14:18:25.
 */
@Repository
public interface MainTaskMapper extends MyMapperAndIds<MainTask> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<MainTask> selectPage(Map map);

    /**
     * 根据主任务编号查询
     */
    MainTask selectByMainTaskNum(String mainTaskNum);

    /**
     * 根据任务状态查询主任务列表
     */
    List<MainTask> selectByTaskStatus(String taskStatus);
    /**
     * 根据任务状态查询主任务列表
     */
    List<MainTask> selectByTaskStatusList(List<String> taskStatusList);

    /**
     * 根据主任务单号设置优先级
     * @param mainTaskList
     * @param priority
     * @return
     */

    int updatePriority(@Param("mainTaskList") List<String> mainTaskList,@Param("priority") Integer priority);

    int updateMainTaskEleByMainTaskNum(MainTask mainTask);

    /**
     * 查询未开始的主任务数量
     */
    Integer selectUnStartMainTaskCount();
    /**
     * 查询执行中的主任务数量
     */
    Integer selectStartMainTaskCount();
    /**
     * 查询已结束的主任务数量
     */
    Integer selectEndMainTaskCount();
    /**
     * 查询时间段主任务结束数量
     */
    List<ViewData> getMainViewData();
    /**
     * 时间段执行中的
     */
    List<ViewData> getMainStartViewData();
    Integer selectStartUSpTopTaskCount();
    Integer selectStartSupplyAndRecycleTaskCount();
    Integer selectStartEmptyRecycleTask();

}