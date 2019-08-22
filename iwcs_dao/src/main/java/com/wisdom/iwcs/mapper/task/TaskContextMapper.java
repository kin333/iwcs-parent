package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TaskContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-08-22 19:27:46.
 */
@Repository
public interface TaskContextMapper extends MyMapperAndIds<TaskContext> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TaskContext> selectPage(Map map);
}