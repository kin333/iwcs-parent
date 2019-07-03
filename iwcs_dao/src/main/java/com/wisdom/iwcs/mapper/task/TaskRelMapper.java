package com.wisdom.iwcs.mapper.task;


import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TaskRel;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-06-20 11:50:19.
 * DeleteLogicMapper<TaskRel>,
 */
@Repository
public interface TaskRelMapper extends MyMapperAndIds<TaskRel> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TaskRel> selectPage(Map map);

    List<TaskRel> selectByMainTaskType(String mainTaskTypeCode);
}