package com.wisdom.iwcs.mapper.base;


import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseWbGroup;
import com.wisdom.iwcs.domain.base.dto.BaseWbGroupDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:04:03.
 */
@Repository
public interface BaseWbGroupMapper extends DeleteLogicMapper<BaseWbGroup>, MyMapperAndIds<BaseWbGroup> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseWbGroup> selectPage(Map map);

    BaseWbGroupDTO selectWbGroupByGroupCode(String groupCode);
}