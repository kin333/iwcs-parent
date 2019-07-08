package com.wisdom.iwcs.mapper.task;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TaskPointBlackRule;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-07-08 14:55:24.
 */
@Repository
public interface TaskPointBlackRuleMapper extends MyMapperAndIds<TaskPointBlackRule> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TaskPointBlackRule> selectPage(Map map);
}