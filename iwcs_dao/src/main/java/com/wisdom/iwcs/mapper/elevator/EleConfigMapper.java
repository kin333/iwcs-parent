package com.wisdom.iwcs.mapper.elevator;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.elevator.EleConfig;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-06-28 14:31:25.
 */
@Repository
public interface EleConfigMapper extends MyMapperAndIds<EleConfig> {

    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<EleConfig> selectPage(Map map);
}
