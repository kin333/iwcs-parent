package com.wisdom.iwcs.mapper.task;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.LineTask;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-07-13 18:11:16.
 */
@Repository
public interface LineTaskMapper extends MyMapperAndIds<LineTask> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<LineTask> selectPage(Map map);
}