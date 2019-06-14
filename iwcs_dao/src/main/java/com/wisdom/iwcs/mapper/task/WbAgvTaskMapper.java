package com.wisdom.iwcs.mapper.task;


import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.WbAgvTask;
import com.wisdom.iwcs.domain.task.dto.WbAgvTaskDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 18:22:00.
 */
@Repository
public interface WbAgvTaskMapper extends MyMapperAndIds<WbAgvTask> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<WbAgvTask> selectPage(Map map);

    /**
     * 根据工作台点位count是否还有未完成的任务
     *
     * @param wbCode
     * @param taskStatus
     * @return
     */
    int selectCountNotCompletedTaskByWbCodeAndTaskEndStatus(@Param("wbCode") String wbCode, @Param("taskStatus") String taskStatus);

    /**
     * 获取任务流水号
     *
     * @param wbCode
     * @return
     */
    @Select("select task_no from wb_agv_task where wb_code=#{wbCode} and task_status=0")
    String selectTaskrefByWeb(String wbCode);

    /**
     * 根据BINCODE获取点位
     *
     * @param podcode
     * @return
     */
    @Select("select a.wb_code from wb_agv_task a LEFT JOIN wb_task_detail b on a.task_no = b.wb_task_no where pod_code=#{podcode} and b.task_status='0'")
    String selectWbcodeByPodcode(String podcode);

    /**
     * 根据podCode、任务类型获取当前正在执行的任务详情
     *
     * @param podCode
     * @param taskType
     * @return
     */
    List<WbAgvTask> selectUnCompletedTaskInfoByPodCodeAndTaskType(@Param("podCode") String podCode, @Param("taskType") String taskType);

    /**
     * 根据PODcode、任务类型获取当前已到达工作台的任务详情
     *
     * @param podCode
     * @param taskType
     * @return
     */
    List<WbAgvTask> selectArrivedTaskByPodCodeAndTaskType(@Param("podCode") String podCode, @Param("taskType") String taskType);

    /**
     * 根据点位获取未完成的点位任务
     *
     * @param wbCode
     * @return
     */
    WbAgvTask selectUnCompletedTaskByWbCode(String wbCode);

    /**
     * 根据工作台点位count是否还有未完成的任务
     *
     * @param wbCodes
     * @return
     */
    int selectCountNotCompletedTaskNumByMutexWbCode(List<String> wbCodes);

    /**
     * 根据任务号更新状态
     *
     * @param taskNo
     * @param taskStatus
     * @return
     */
    int updateTaskStatusByTaskNoAndTaskStatus(@Param("taskNo") String taskNo, @Param("taskStatus") String taskStatus);

    /**
     * 根据任务编号获取任务信息
     *
     * @param taskNo
     * @return
     */
    WbAgvTask selectedByTaskNo(String taskNo);

    /**
     * 根据点位编号count未完成的任务
     *
     * @param wbCodes
     * @return
     */
    int selectCountNotCompletedTaskByWbCodes(List<String> wbCodes);

    /**
     * 根据点位编码，任务类型count非该任务类型的未结束的任务
     *
     * @param wbCode
     * @param taskType
     * @return
     */
    int selectCountNotCompletedTaskByWbCodeAndNotPointTaskType(@Param("wbCode") String wbCode, @Param("taskType") String taskType);

    /**
     * 根据bizOrderNo 获取工作台
     */
    @Select("SELECT wb_agv_task.wb_code FROM wb_agv_task where biz_order_code = #{bizOrderNo} and task_status < '9'  and task_type = 'inventory'")
    List<String> underBizOrderNoByWbCode(String bizOrderNo);

    List<WbAgvTask> selectWbAgvTaskInfo(WbAgvTaskDTO record);

}

