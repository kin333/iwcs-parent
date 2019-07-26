package com.wisdom.iwcs.mapper.linebody;

import java.util.List;
import java.util.Map;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.linebody.LineBody;
import org.apache.ibatis.annotations.Select;
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

    @Select("select msg_code from line_body where map_code in (select map_code from base_map_berth where point_alias = #{pointAlias,jdbcType=VARCHAR})")
    String selectMsgCode(String pointAlias);
}