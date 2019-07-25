package com.wisdom.iwcs.mapper.elevator;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.elevator.Elevator;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-07-25 10:37:42.
 */
@Repository
public interface ElevatorMapper extends MyMapperAndIds<Elevator> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<Elevator> selectPage(Map map);
}