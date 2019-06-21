package com.wisdom.iwcs.mapper.task;


import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TsTaskGroupItem;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-06-20 11:48:56.
 * DeleteLogicMapper<TsTaskGroupItem>,
 */
@Repository
public interface TsTaskGroupItemMapper extends MyMapperAndIds<TsTaskGroupItem> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TsTaskGroupItem> selectPage(Map map);
}