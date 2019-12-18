package com.wisdom.iwcs.mapper.task;


import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.system.ViewData;
import com.wisdom.iwcs.domain.task.SubTask;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-06-20 11:44:31.
 * DeleteLogicMapper<TsSubTask>,
 */
@Repository
public interface SubTaskMapper extends MyMapperAndIds<SubTask> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<SubTask> selectPage(Map map);

    List<SubTask> selectByMainTaskNum(String mainTaskNum);

    /**
     * 根据子任务编号查询数据
     * @param subTaskNum
     * @return
     */
    SubTask selectBySubTaskNum(String subTaskNum);

    /**
     * 根据子任务编号更改下发状态
     * @param subTaskNum
     */
    void updateSendStatus(@Param("subTaskNum") String subTaskNum,@Param("status") String status);

    /**
     * 根据子任务编号更新货架号
     * @param subTaskNum
     * @return
     */
    int updatePodCodeBySubTaskCode(@Param("subTaskNum") String subTaskNum);

    /**
     * 根据子任务编号更新终点地码和位置
     * @param subTaskNum
     * @param baseMapBerth
     * @return
     */
    int updateEndCodeBySubTaskCode(@Param("subTaskNum") String subTaskNum,@Param("baseMapBerth") BaseMapBerth baseMapBerth);

    /**
     * 根据主任务单号设置优先级
     * @param subTaskList
     * @param priority
     * @return
     */

    int updatePriority(@Param("subTaskList") List<String> subTaskList,@Param("priority") Integer priority);

    /**
     * 根据任务编号更新机器人编号,和状态
     * @return
     */
    int updateRobotCodeByBerCode(SubTask subTask);
    /**
     * 根据子任务编号更新机器人编号,和状态
     * @return
     */
    int updateTimeBySubTaskNum(SubTask subTask);
    /**
     * 根据子任务编号以及当前任务状态更新机器人编号,和状态
     * @return
     */
    int updateTimeByTaskNumAndStatus(@Param("subTask")SubTask subTask,@Param("currentStatus") String currentStatus);

    /**
     * 通过执行任务号查询子任务
     * @param taskCode
     * @return
     */
    SubTask selectByTaskCode(String taskCode);
    /**
     * 通过执行任务号查询所有子任务
     * @param taskCode
     * @return
     */
    List<SubTask> selectAllByTaskCode(String taskCode);

    /**
     * 根据子任务编号更新货架号和地码
     * @param subTaskNum
     * @return
     */
    int updatePodAndBerBySubTaskCode(@Param("subTaskNum")String subTaskNum,@Param("podCode") String podCode,@Param("berCode") String berCode);

    /**
     * 根据子任务号修改任务状态
     * @param subTaskNum
     * @param statusCode
     * @return
     */
    int updateTaskStatusByNum(@Param("subTaskNum") String subTaskNum,@Param("statusCode") String statusCode);
    /**
     * 根据子任务号修改第三方任务状态
     * @param subTaskNum
     * @param workStatusCode
     * @return
     */
    int updateWorkTaskStatusByNum(@Param("subTaskNum") String subTaskNum,@Param("workStatusCode") String workStatusCode);

    /**
     * 根据主任务号和子任务类型更新
     * @return
     */
    int updateByMainTaskNumAndSubTaskType(SubTask subTask);

    List<SubTask> selectUnusualTask(String endStatus);

    List<SubTask> selectByTaskStatus(String taskStatus);

    int updateJsonData(@Param("subTaskNum") String subTaskNum,@Param("jsonData") String jsonData);

    List<SubTask> selectSubTaskByMainCode(SubTask subTask);

    /**
     * 查询未开始的子任务数量
     */
    Integer selectUnStartSubTaskCount();
    /**
     * 查询执行中的子任务数量
     */
    Integer selectStartSubTaskCount();
    /**
     * 查询已结束的子任务数量
     */
    Integer selectEndSubTaskCount();
    /**
     * 获取每个时间段子任务数量
     */
    List<ViewData> getViewData();
    /**
     * 查询时间段执行中的
     */
    List<ViewData> getSubStartViewData();

    int updateInitById(@Param("list") List<Long> changeIds,
                       @Param("newWorkTaskNum") String newWorkTaskNum,
                       @Param("robotCode") String robotCode);
}