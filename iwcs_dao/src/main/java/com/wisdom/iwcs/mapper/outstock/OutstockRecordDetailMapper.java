package com.wisdom.iwcs.mapper.outstock;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.outstock.OutstockRecordDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 18:07:00.
 */
@Repository
public interface OutstockRecordDetailMapper extends MyMapperAndIds<OutstockRecordDetail> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<OutstockRecordDetail> selectPage(Map map);

    List<OutstockRecordDetail> selectOutstockRecordDetailById(Integer outstockRecordId);

}
