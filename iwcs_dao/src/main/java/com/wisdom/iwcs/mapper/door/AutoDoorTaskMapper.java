package com.wisdom.iwcs.mapper.door;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.door.AutoDoorTask;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-11-26 14:30:47.
 */
@Repository
public interface AutoDoorTaskMapper extends MyMapperAndIds<AutoDoorTask> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<AutoDoorTask> selectPage(Map map);

    /**
     * 根据门的编号查询当前是否有未完结的过门请求
     */
    List<AutoDoorTask> selectUnTaskByDoorCode(String doorCode);
}