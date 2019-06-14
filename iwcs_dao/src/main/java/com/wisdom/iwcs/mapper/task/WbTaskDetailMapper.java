package com.wisdom.iwcs.mapper.task;


import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.WbTaskDetail;
import com.wisdom.iwcs.domain.task.dto.WbTaskDetailDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 18:22:51.
 */
@Repository
public interface WbTaskDetailMapper extends MyMapperAndIds<WbTaskDetail> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<WbTaskDetail> selectPage(Map map);

    /**
     * 根据货架编号count未完成的任务的个数
     *
     * @param podCode
     * @param taskStatus
     * @return
     */
    int selectCountNotCompletedTaskByPodCodeAndTaskEndStatus(@Param("podCode") String podCode, @Param("taskStatus") String taskStatus);


    List<WbTaskDetail> selectTaskNoByPod(Map map);

    /**
     * 根据podCode获取未完成的任务
     *
     * @param podCode
     * @return
     */
    List<WbTaskDetail> selectCountUnCompletedTaskByPodCode(String podCode);

    /**
     * 根据任务编号count未完成的任务的个数
     *
     * @param taskNo
     * @return
     */
    int selectNotCompletedTaskByTaskNo(String taskNo);

    /**
     * 根据货架编号和任务号未完成的任务
     *
     * @param podCode
     * @param taskNo
     * @return
     */
    WbTaskDetail selectNotCompletedTaskByTaskNoAndPodCode(@Param("podCode") String podCode, @Param("taskNo") String taskNo);

    /**
     * 根据任务号更新任务状态
     *
     * @param wbTaskNo
     * @param taskStatus
     * @return
     */
    int updateTaskStatusByWbTaskNoAndTaskStatus(@Param("wbTaskNo") String wbTaskNo, @Param("taskStatus") String taskStatus);

    /**
     * 根据任务号获取未完成的任务信息
     *
     * @param wbTaskNo
     * @return
     */
    List<WbTaskDetail> selectUnCompletedTaskByWbTaskNo(String wbTaskNo);

    /**
     * 根据任务类型获取未完成的任务详情
     *
     * @param taskTypes
     * @return
     */
    List<WbTaskDetail> selectUnCompletedTaskDetailByTaskTypes(List<String> taskTypes);

    /**
     * 根据工作台编号 任务类型 获取在该工作台未完成的任务信息
     *
     * @param wbCode
     * @param taskType
     * @return List
     */
    List<WbTaskDetail> selectArrivedTaskByWbCodeAndTaskType(@Param("wbCode") String wbCode, @Param("taskType") String taskType);

    int numberOfJobs(@Param("bizOrderCode") String bizOrderCode, @Param("taskType") String taskType);

    List<WbTaskDetailDTO> selectAgvTaskDetail(WbTaskDetailDTO record);

}
