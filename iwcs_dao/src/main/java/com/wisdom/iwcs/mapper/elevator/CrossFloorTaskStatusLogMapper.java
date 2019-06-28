package com.wisdom.iwcs.mapper.elevator;


import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.elevator.CrossFloorTaskStatusLog;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
/**
 * @author Generator
 * @date 2019-06-28 16:34:10.
 */
@Repository
public interface CrossFloorTaskStatusLogMapper extends MyMapperAndIds<CrossFloorTaskStatusLog>{
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<CrossFloorTaskStatusLog> selectPage(Map map);
}
