package com.wisdom.iwcs.mapper.log;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.log.AgvTaskLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 18:24:35.
 */
@Repository
public interface AgvTaskLogMapper extends MyMapperAndIds<AgvTaskLog> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<AgvTaskLog> selectPage(Map map);
}