package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.task.BaseMsgRcv;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-09-02 14:23:58.
 */
@Repository
public interface BaseMsgRcvMapper extends MyMapperAndIds<BaseMsgRcv> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<BaseMsgRcv> selectPage(Map map);
}