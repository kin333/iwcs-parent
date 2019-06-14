package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BasePodLayerStk;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-07 13:52:24.
 */
@Repository
public interface BasePodLayerStkMapper extends MyMapperAndIds<BasePodLayerStk> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BasePodLayerStk> selectPage(Map map);

    /**
     * 根据货架号查询
     *
     * @param podCode
     * @return
     */
    List<BasePodLayerStk> selectByPodCode(String podCode);

    /**
     * 根据货架号更新库区编号、地图编号
     *
     * @param areaCode
     * @param mapCode
     * @param podCode
     * @return
     */
    Integer updateAreaCodeAndMapCodeByPodCode(@Param("areaCode") String areaCode, @Param("mapCode") String mapCode, @Param("podCode") String podCode);
}