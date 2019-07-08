package com.wisdom.iwcs.mapper.log;


import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.log.TaskOperationLog;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-07-08 15:41:11.
 */
@Repository
public interface TaskLogMapper extends DeleteLogicMapper<TaskOperationLog>, MyMapperAndIds<TaskOperationLog> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TaskOperationLog> selectPage(Map map);
}