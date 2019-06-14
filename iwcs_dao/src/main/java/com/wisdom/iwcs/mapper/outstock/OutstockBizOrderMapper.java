package com.wisdom.iwcs.mapper.outstock;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.outstock.OutstockBizOrder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:45:29.
 */
@Repository
public interface OutstockBizOrderMapper extends MyMapperAndIds<OutstockBizOrder> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<OutstockBizOrder> selectPage(Map map);

    /**
     * 根据订单号获取已存在的订单信息
     *
     * @param bizOrderCode
     * @return
     */
    OutstockBizOrder selectByBizOrderCode(String bizOrderCode);

    /**
     * 根据出库单号删除出库单信息
     *
     * @param bizOrderCodes
     * @return
     */
    int deleteByBizOrderCodes(List<String> bizOrderCodes);
}