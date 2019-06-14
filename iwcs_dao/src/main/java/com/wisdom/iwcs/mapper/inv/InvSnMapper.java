package com.wisdom.iwcs.mapper.inv;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.inv.InvSn;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-01 09:45:15.
 */
@Repository
public interface InvSnMapper extends DeleteLogicMapper<InvSn>, MyMapperAndIds<InvSn> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<InvSn> selectPage(Map map);

    /**
     * 更新SN盘点结果表
     *
     * @param invSns
     * @return int
     */

    int updateInvSNResults(InvSn invSns);
}