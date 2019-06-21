package com.wisdom.iwcs.mapper.task;


import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TsTaskGroup;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-06-20 11:47:49.
 * DeleteLogicMapper<TsTaskGroup>,
 */
@Repository
public interface TsTaskGroupMapper extends MyMapperAndIds<TsTaskGroup> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TsTaskGroup> selectPage(Map map);
}