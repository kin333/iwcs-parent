package com.wisdom.iwcs.mapper.log;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.log.InterfaceLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 18:25:01.
 */
@Repository
public interface InterfaceLogMapper extends MyMapperAndIds<InterfaceLog> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<InterfaceLog> selectPage(Map map);
}