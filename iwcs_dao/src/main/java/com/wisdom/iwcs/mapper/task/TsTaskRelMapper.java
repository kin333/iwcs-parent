package com.wisdom.iwcs.mapper.task;


import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TsTaskRel;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-06-20 11:50:19.
 * DeleteLogicMapper<TsTaskRel>,
 */
@Repository
public interface TsTaskRelMapper extends MyMapperAndIds<TsTaskRel> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TsTaskRel> selectPage(Map map);
}