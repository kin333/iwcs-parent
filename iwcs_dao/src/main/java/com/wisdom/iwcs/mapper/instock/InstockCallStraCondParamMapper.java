package com.wisdom.iwcs.mapper.instock;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.instock.InstockCallStraCondParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-19 18:41:52.
 */
@Repository
public interface InstockCallStraCondParamMapper extends DeleteLogicMapper<InstockCallStraCondParam>, MyMapperAndIds<InstockCallStraCondParam> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<InstockCallStraCondParam> selectPage(Map map);
}