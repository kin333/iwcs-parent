package com.wisdom.iwcs.mapper.instock;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.instock.InstockCallStra;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-19 18:40:45.
 */
@Repository
public interface InstockCallStraMapper extends DeleteLogicMapper<InstockCallStra>, MyMapperAndIds<InstockCallStra> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<InstockCallStra> selectPage(Map map);
}