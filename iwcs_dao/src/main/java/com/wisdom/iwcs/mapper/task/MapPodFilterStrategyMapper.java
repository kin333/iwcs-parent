package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.MapPodFilterStrategy;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-09-25 15:57:17.
 */
@Repository
public interface MapPodFilterStrategyMapper extends DeleteLogicMapper<MapPodFilterStrategy>, MyMapperAndIds<MapPodFilterStrategy> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<MapPodFilterStrategy> selectPage(Map map);
}