package com.wisdom.iwcs.mapper.task;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.ResourceHandlerStrategy;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-12-25 11:29:44.
 */
@Repository
public interface ResourceHandlerStrategyMapper extends DeleteLogicMapper<ResourceHandlerStrategy>, MyMapperAndIds<ResourceHandlerStrategy> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<ResourceHandlerStrategy> selectPage(Map map);

    ResourceHandlerStrategy selectByStrategyCode(String strategyCode);
}