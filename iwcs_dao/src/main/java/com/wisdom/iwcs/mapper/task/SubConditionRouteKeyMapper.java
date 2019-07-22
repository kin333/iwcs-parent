package com.wisdom.iwcs.mapper.task;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.SubConditionRouteKey;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-07-19 19:12:25.
 */
@Repository
public interface SubConditionRouteKeyMapper extends MyMapperAndIds<SubConditionRouteKey> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<SubConditionRouteKey> selectPage(Map map);

    /**
     * 根据code查询数据
     * @param code
     * @return
     */
    SubConditionRouteKey selectByCode(String code);
}