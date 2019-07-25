package com.wisdom.iwcs.mapper.linebody;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.linebody.LineBody;
import org.springframework.stereotype.Repository;

/**
 * @author Generator
 * @date 2019-07-25 10:38:26.
 */
@Repository
public interface LineBodyMapper extends MyMapperAndIds<LineBody> {
    /**
     * selectPage
     * @param map condition
     * @return list
     */
    List<LineBody> selectPage(Map map);
}