package com.wisdom.iwcs.mapper.task;


import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.MainTask;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-06-25 14:18:25.
 */
@Repository
public interface MainTaskMapper extends MyMapperAndIds<MainTask> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<MainTask> selectPage(Map map);
}