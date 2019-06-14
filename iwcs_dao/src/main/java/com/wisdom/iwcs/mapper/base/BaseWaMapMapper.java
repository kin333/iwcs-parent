package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseWaMap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:03:12.
 */
@Repository
public interface BaseWaMapMapper extends DeleteLogicMapper<BaseWaMap>, MyMapperAndIds<BaseWaMap> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseWaMap> selectPage(Map map);

    /**
     * 根据地图编号获取未删除的有效的地图、库区对应关系
     *
     * @param mapCode
     * @param validFlag
     * @param deleteFlag
     * @return
     */
    BaseWaMap selectByMapCodeAndValidFlagAndDeleteFlag(@Param("mapCode") String mapCode, @Param("validFlag") Integer validFlag, @Param("deleteFlag") Integer deleteFlag);
}