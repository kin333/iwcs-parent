package com.wisdom.iwcs.mapper.inv;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.inv.InvTaskResultDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-01 09:46:13.
 */
@Repository
public interface InvTaskResultDetailMapper extends MyMapperAndIds<InvTaskResultDetail> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<InvTaskResultDetail> selectPage(Map map);

    /**
     * updateActualInvData
     *
     * @param invTaskDetail
     * @return int
     */
    int updateActualInvData(InvTaskResultDetail invTaskDetail);

    /**
     * queryInvResultOfSrcInvNo
     *
     * @param srcInvNo
     * @return list
     */
    List<InvTaskResultDetail> queryInvResultOfSrcInvNo(@Param("srcInvNo") String srcInvNo);

    List<InvTaskResultDetail> selectAllBySrcInvNo(@Param("srcInvNo") String srcInvNo);

}