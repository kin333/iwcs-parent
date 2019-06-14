package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseBizConCurrentRules;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-25 09:52:40.
 */
@Repository
public interface BaseBizConCurrentRulesMapper extends MyMapperAndIds<BaseBizConCurrentRules> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseBizConCurrentRules> selectPage(Map map);

    /**
     * 根据
     *
     * @param srcBizType
     * @return
     */
    List<String> selectBySrcBizType(String srcBizType);
}