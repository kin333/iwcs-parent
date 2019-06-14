package com.wisdom.iwcs.mapper.stock;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.stock.StockAbnormalAdjustRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-21 15:26:54.
 */
@Repository
public interface StockAbnormalAdjustRecordMapper extends MyMapperAndIds<StockAbnormalAdjustRecord> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<StockAbnormalAdjustRecord> selectPage(Map map);
}