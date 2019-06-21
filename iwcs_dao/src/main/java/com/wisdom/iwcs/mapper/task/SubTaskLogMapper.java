package com.wisdom.iwcs.mapper.task;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.SubTaskLog;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-06-20 11:45:39.
 * DeleteLogicMapper<TsSubTaskLog>,
 */
@Repository
public interface SubTaskLogMapper extends MyMapperAndIds<SubTaskLog> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<SubTaskLog> selectPage(Map map);
}