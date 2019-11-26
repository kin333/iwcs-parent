package com.wisdom.iwcs.mapper.door;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.door.DoorMsgLog;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-11-26 14:32:07.
 */
@Repository
public interface DoorMsgLogMapper extends MyMapperAndIds<DoorMsgLog> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<DoorMsgLog> selectPage(Map map);
}