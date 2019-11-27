package com.wisdom.iwcs.mapper.door;


import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.door.AutoDoor;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-11-26 14:28:58.
 */
@Repository
public interface AutoDoorMapper extends MyMapperAndIds<AutoDoor> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<AutoDoor> selectPage(Map map);

    AutoDoor selectDoorStatus(String doorCode);

    int updateDoorInfo(AutoDoor autoDoor);
}