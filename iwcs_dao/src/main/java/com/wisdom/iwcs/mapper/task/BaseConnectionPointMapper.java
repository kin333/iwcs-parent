package com.wisdom.iwcs.mapper.task;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.BaseConnectionPoint;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-07-30 16:51:38.
 */
@Repository
public interface BaseConnectionPointMapper extends DeleteLogicMapper<BaseConnectionPoint>, MyMapperAndIds<BaseConnectionPoint> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<BaseConnectionPoint> selectPage(Map map);
}