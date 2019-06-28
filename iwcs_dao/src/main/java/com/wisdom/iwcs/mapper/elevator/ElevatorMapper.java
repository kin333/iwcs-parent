package com.wisdom.iwcs.mapper.elevator;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.elevator.Elevator;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-06-28 17:05:41.
 */
@Repository
public interface ElevatorMapper extends MyMapperAndIds<Elevator>{
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<Elevator> selectPage(Map map);
}
