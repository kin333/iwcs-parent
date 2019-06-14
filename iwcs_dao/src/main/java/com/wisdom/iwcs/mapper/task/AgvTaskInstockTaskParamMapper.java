package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.AgvTaskInstockTaskParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-04-04 14:00:11.
 */
@Repository
public interface AgvTaskInstockTaskParamMapper extends MyMapperAndIds<AgvTaskInstockTaskParam> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<AgvTaskInstockTaskParam> selectPage(Map map);

    AgvTaskInstockTaskParam selectInstockTaskParam(String taskNo);
}