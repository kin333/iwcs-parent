package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TaskRelCondition;
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
     * 根据子任务编号查询
     */
    TaskRelCondition selectBySubCode(String subTaskTypeCode);
}
