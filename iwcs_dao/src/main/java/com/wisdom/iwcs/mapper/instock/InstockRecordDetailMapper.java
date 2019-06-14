package com.wisdom.iwcs.mapper.instock;


import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.instock.InstockRecordDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:36:41.
 */
@Repository
public interface InstockRecordDetailMapper extends MyMapperAndIds<InstockRecordDetail> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<InstockRecordDetail> selectPage(Map map);

    List<InstockRecordDetail> selectRecordDetail(Integer instockRecordId);

}
