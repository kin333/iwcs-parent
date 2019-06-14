package com.wisdom.iwcs.mapper.quartz;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.quartz.QuartzJobLog;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-25 18:21:43.
 */
@Repository
public interface QuartzJobLogMapper extends MyMapperAndIds<QuartzJobLog> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<QuartzJobLog> selectPage(Map map);
}