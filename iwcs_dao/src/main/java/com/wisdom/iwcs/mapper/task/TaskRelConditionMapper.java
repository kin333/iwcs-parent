package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TaskRelCondition;
import com.wisdom.iwcs.domain.task.dto.TaskRelConditionDTO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * @author Generator
 * @date 2019-06-26 11:49:44.
 */
public interface TaskRelConditionMapper extends MyMapperAndIds<TaskRelCondition> {

    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TaskRelCondition> selectPage(Map map);

    /**
     * 根据主任务和子任务编号查询
     */
    List<TaskRelCondition> selectByMainTaskTypeCodeAndSubCode(@Param("mainTaskTypeCode") String mainTaskTypeCode,@Param("subTaskTypeCode") String subTaskTypeCode);

    /**
     * 根据模板编号搜索条件
     *
     * @param templCode
     * @return
     */
    List<TaskRelCondition> selectByTemplCodeAndConType(@Param("templCode") String templCode, @Param("conditonTriger") String conditonTriger);

    /**
     * 根据子任务编号查询
     */
    TaskRelCondition selectBySubCode(String subTaskTypeCode);

    /**
     * 根据主任务类型和执行顺序查找
     * @param mainTaskType
     * @param subTaskSeq
     * @return
     */
    TaskRelCondition selectByMainTaskTypeAndPriority(@Param("mainTaskType") String mainTaskType,@Param("subTaskSeq") Integer subTaskSeq);

    List<TaskRelCondition> selectByTemplCode(String templCode);

    Integer updateByTemplCode(TaskRelCondition taskRelCondition);

    Integer deleteByTemplCode(String templCode);

    TaskRelCondition selectTaskConditionByTemplCode(TaskRelCondition taskRelConditionDTO);
}
