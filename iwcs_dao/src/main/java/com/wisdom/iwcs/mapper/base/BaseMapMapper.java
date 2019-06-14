package com.wisdom.iwcs.mapper.base;


import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-22 11:08:51.
 */
@Repository
public interface BaseMapMapper extends DeleteLogicMapper<BaseMap>, MyMapperAndIds<BaseMap> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseMap> selectPage(Map map);

    /**
     * 根据mapCode和DeleteFlag获取地图信息
     *
     * @param mapCode
     * @param deleteFlag
     * @return
     */
    BaseMap selectByMapCodeAndDeleteFlag(@Param("mapCode") String mapCode, @Param("deleteFlag") Integer deleteFlag);

    /**
     * 根据地图编号删除地图数据
     *
     * @param mapCode
     * @return
     */
    int deleteByMapCode(String mapCode);


}