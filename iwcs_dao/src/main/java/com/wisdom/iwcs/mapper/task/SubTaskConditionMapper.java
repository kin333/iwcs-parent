package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.SubTaskCondition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-06-26 11:44:27.
 */
@Repository
public interface SubTaskConditionMapper extends MyMapperAndIds<SubTaskCondition> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<SubTaskCondition> selectPage(Map map);
}
