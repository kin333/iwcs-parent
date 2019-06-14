package com.wisdom.iwcs.mapper.instock;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.instock.InstockRecord;
import com.wisdom.iwcs.domain.instock.dto.InstockRecordConditionDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:36:09.
 */
@Repository
public interface InstockRecordMapper extends MyMapperAndIds<InstockRecord> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<InstockRecord> selectPage(Map map);

    List<InstockRecord> selectInstockRecord(InstockRecordConditionDto instockRecordConditionDto);

}
