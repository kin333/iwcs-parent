package com.wisdom.iwcs.mapper.task;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.domain.task.Imitatetest;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-10-11 15:47:08.
 */
@Repository
public interface ImitateTestMapper extends  MyMapperAndIds<Imitatetest> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<Imitatetest> selectPage(Map map);
}