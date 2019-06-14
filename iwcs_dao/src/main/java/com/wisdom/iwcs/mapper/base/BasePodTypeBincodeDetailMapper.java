package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BasePodTypeBincodeDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:02:23.
 */
@Repository
public interface BasePodTypeBincodeDetailMapper extends DeleteLogicMapper<BasePodTypeBincodeDetail>, MyMapperAndIds<BasePodTypeBincodeDetail> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BasePodTypeBincodeDetail> selectPage(Map map);

    /**
     * 根据货架类型编号删除描述信息
     *
     * @param podTypeCode
     * @return
     */
    int deleteByPodTypeCode(String podTypeCode);

    /**
     * 根据货架类型获取货架的层数和bincode数量
     *
     * @param podTypeCode
     * @return
     */
    List<BasePodTypeBincodeDetail> selectLayerAndBincodeNumByPodTypeCode(String podTypeCode);
}