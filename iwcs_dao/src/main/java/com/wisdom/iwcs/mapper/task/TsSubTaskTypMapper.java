package com.wisdom.iwcs.mapper.task;


import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.TsSubTaskTyp;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-06-20 11:46:52.
 * DeleteLogicMapper<TsSubTaskTyp>,
 */
@Repository
public interface TsSubTaskTypMapper extends  MyMapperAndIds<TsSubTaskTyp> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<TsSubTaskTyp> selectPage(Map map);
}