package com.wisdom.iwcs.mapper.elevator;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.elevator.CrossFloorTask;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * @author Generator
 * @date 2019-06-28 15:55:51.
 */
@Repository
public interface CrossFloorTaskMapper extends DeleteLogicMapper<CrossFloorTask>, MyMapperAndIds<CrossFloorTask> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List <CrossFloorTask> selectPage(Map map);
}
