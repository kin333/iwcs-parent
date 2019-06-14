package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseBincodeType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-19 14:49:36.
 */
@Repository
public interface BaseBincodeTypeMapper extends MyMapperAndIds<BaseBincodeType> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseBincodeType> selectPage(Map map);

    /**
     * 根据仓位类型编码获取仓位类型信息
     *
     * @param bincodeTypeCode
     * @return
     */
    BaseBincodeType selectByBincodeTypeCode(String bincodeTypeCode);
}