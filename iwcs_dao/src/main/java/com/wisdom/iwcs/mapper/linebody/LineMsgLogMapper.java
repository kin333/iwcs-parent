package com.wisdom.iwcs.mapper.linebody;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.linebody.LineMsgLog;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-07-25 10:38:52.
 */
@Repository
public interface LineMsgLogMapper extends MyMapperAndIds<LineMsgLog> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<LineMsgLog> selectPage(Map map);
}