package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TaskRelConditions;

import java.util.List;
import java.util.Map;


/**
 * @author Generator
 * @date 2019-06-26 11:49:44.
 */
public interface TaskRelConditionsMapper extends MyMapperAndIds<TaskRelConditions> {

    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TaskRelConditions> selectPage(Map map);
}
