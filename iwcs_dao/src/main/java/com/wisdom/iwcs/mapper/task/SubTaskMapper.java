package com.wisdom.iwcs.mapper.task;


import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.SubTask;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-06-20 11:44:31.
 * DeleteLogicMapper<TsSubTask>,
 */
@Repository
public interface SubTaskMapper extends MyMapperAndIds<SubTask> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<SubTask> selectPage(Map map);
}