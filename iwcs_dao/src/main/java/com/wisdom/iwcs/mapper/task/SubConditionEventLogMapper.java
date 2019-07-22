package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.SubConditionEventLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-07-19 19:13:07.
 */
@Repository
public interface SubConditionEventLogMapper extends MyMapperAndIds<SubConditionEventLog> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<SubConditionEventLog> selectPage(Map map);
}