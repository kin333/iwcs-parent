package com.wisdom.iwcs.mapper.instock;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.instock.InstockCallStraParam;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-19 18:40:07.
 */
@Repository
public interface InstockCallStraParamMapper extends MyMapperAndIds<InstockCallStraParam> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<InstockCallStraParam> selectPage(Map map);
}