package com.wisdom.iwcs.mapper.outstock;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.outstock.OutstockRecord;
import com.wisdom.iwcs.domain.outstock.dto.OutstockRecordConditionDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 18:06:25.
 */
@Repository
public interface OutstockRecordMapper extends MyMapperAndIds<OutstockRecord> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<OutstockRecord> selectPage(Map map);

    List<OutstockRecord> selectOutStockRecord(OutstockRecordConditionDTO record);

}
