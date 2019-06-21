package com.wisdom.iwcs.mapper.task;


import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TaskGroup;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-06-20 11:47:49.
 * DeleteLogicMapper<TaskGroup>,
 */
@Repository
public interface TaskGroupMapper extends MyMapperAndIds<TaskGroup> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TaskGroup> selectPage(Map map);
}