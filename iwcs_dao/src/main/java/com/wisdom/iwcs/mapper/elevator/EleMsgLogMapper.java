package com.wisdom.iwcs.mapper.elevator;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.elevator.EleMsgLog;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-07-25 10:36:40.
 */
@Repository
public interface EleMsgLogMapper extends MyMapperAndIds<EleMsgLog> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<EleMsgLog> selectPage(Map map);
}