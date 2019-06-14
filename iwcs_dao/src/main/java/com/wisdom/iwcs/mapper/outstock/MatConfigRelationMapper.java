package com.wisdom.iwcs.mapper.outstock;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.outstock.MatConfigRelation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:46:09.
 */
@Repository
public interface MatConfigRelationMapper extends MyMapperAndIds<MatConfigRelation> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<MatConfigRelation> selectPage(Map map);

    /**
     * 根据配置号、出库材料编号获取关系
     *
     * @param configCode
     * @param orderMatGenCode
     * @return
     */
    MatConfigRelation selectByConfigCodeAndOrderMatGenCode(@Param("configCode") String configCode, @Param("orderMatCode") String orderMatGenCode);

    /**
     * 根据出库材料编号删除关系
     *
     * @param orderMatGenCodes
     * @return
     */
    int deleteByOrderMatGenCodes(List<String> orderMatGenCodes);

    /**
     * 根据订单号获取配置关系
     *
     * @param orderMatGenCode
     * @return
     */
    MatConfigRelation selectByOrderMatGenCode(String orderMatGenCode);
}