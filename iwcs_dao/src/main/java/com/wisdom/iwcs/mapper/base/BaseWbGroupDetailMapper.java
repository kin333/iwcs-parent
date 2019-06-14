package com.wisdom.iwcs.mapper.base;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.base.BaseWbGroupDetail;
import com.wisdom.iwcs.domain.base.dto.BaseWbGroupDetailDTO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-02-15 17:04:35.
 */
@Repository
public interface BaseWbGroupDetailMapper extends MyMapperAndIds<BaseWbGroupDetail> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<BaseWbGroupDetail> selectPage(Map map);

    List<BaseWbGroupDetailDTO> selectWbGroupDetailByGroupCode(String groupCode);

    List<String> selectMutexTypeWbByWbCode(String wbCode);

    List<String> selectInventoryTypeWbByWbCode(String wbCode);
}