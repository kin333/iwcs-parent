package com.wisdom.iwcs.mapper.outstock;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.outstock.OutstockOrderSn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 18:05:58.
 */
@Repository
public interface OutstockOrderSnMapper extends DeleteLogicMapper<OutstockOrderSn>, MyMapperAndIds<OutstockOrderSn> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<OutstockOrderSn> selectPage(Map map);

    /**
     * 根据出库材料唯一编码删除条码信息
     *
     * @param orderMatGenCodes
     * @return
     */
    int deleteByOrderMatGenCodes(List<String> orderMatGenCodes);

    List<OutstockOrderSn> selectOutStockOrderDetailByGenCode(String orderMatGenCode);


}
