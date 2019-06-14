package com.wisdom.iwcs.mapper.inv;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.inv.InvTaskCondDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-20 10:21:27.
 */
@Repository
public interface InvTaskCondDetailMapper extends MyMapperAndIds<InvTaskCondDetail> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<InvTaskCondDetail> selectPage(Map map);


    InvTaskCondDetail getCorrespondCondInfo(@Param("srcInvNo") String srcInvNo);

    List<InvTaskCondDetail> selectInvTaskCondDetail(String invNum);

}
