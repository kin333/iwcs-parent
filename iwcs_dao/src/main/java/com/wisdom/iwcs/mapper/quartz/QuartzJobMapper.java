package com.wisdom.iwcs.mapper.quartz;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.quartz.QuartzJob;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-25 18:20:59.
 */
@Repository
public interface QuartzJobMapper extends MyMapperAndIds<QuartzJob> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<QuartzJob> selectPage(Map map);
}