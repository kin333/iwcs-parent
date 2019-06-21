package com.wisdom.iwcs.mapper.task;


import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TaskGroupItem;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-06-20 11:48:56.
 * DeleteLogicMapper<TaskGroupItem>,
 */
@Repository
public interface TaskGroupItemMapper extends MyMapperAndIds<TaskGroupItem> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TaskGroupItem> selectPage(Map map);
}