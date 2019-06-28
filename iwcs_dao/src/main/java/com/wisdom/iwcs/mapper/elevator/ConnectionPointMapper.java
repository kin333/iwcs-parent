package com.wisdom.iwcs.mapper.elevator;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.elevator.ConnectionPoint;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-06-28 15:32:59.
 */
@Repository
public interface ConnectionPointMapper extends MyMapperAndIds<ConnectionPoint> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<ConnectionPoint> selectPage(Map map);
}
