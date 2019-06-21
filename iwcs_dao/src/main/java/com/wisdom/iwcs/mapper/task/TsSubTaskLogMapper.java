package com.wisdom.iwcs.mapper.task;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TsSubTaskLog;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-06-20 11:45:39.
 * DeleteLogicMapper<TsSubTaskLog>,
 */
@Repository
public interface TsSubTaskLogMapper extends MyMapperAndIds<TsSubTaskLog> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TsSubTaskLog> selectPage(Map map);
}