package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.AgvCallback;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-07-06 13:30:06.
 * DeleteLogicMapper<AgvCallback>,
 */
@Repository
public interface AgvCallbackMapper extends MyMapperAndIds<AgvCallback> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<AgvCallback> selectPage(Map map);
}