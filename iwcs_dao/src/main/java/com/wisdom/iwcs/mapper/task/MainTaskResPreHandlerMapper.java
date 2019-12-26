package com.wisdom.iwcs.mapper.task;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.MainTaskResPreHandler;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-12-25 11:29:07.
 */
@Repository
public interface MainTaskResPreHandlerMapper extends DeleteLogicMapper<MainTaskResPreHandler>, MyMapperAndIds<MainTaskResPreHandler> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<MainTaskResPreHandler> selectPage(Map map);

    List<MainTaskResPreHandler> selectByMainTaskType(String mainTaskType);
}