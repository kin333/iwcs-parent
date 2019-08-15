package com.wisdom.iwcs.mapper.elevator;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.EleControlTask;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-07-25 10:35:59.
 */
@Repository
public interface EleControlTaskMapper extends MyMapperAndIds<EleControlTask> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<EleControlTask> selectPage(Map map);

    EleControlTask selectTaskInfo(String eleTaskCode);

    int updateTaskInfo(EleControlTask eleControlTask);

    @Select("select count(1) from ele_control_task where task_status != '9'")
    long countUnEndTask();

    /**
     * 根据主任务号查询
     * @param mainTaskNum
     * @return
     */
    EleControlTask selectByMainTaskNum(String mainTaskNum);

    int updateByMainTaskNum(EleControlTask eleControlTask);
}