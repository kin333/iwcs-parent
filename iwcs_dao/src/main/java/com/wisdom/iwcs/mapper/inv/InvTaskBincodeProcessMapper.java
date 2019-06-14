package com.wisdom.iwcs.mapper.inv;

import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.inv.InvTaskBincodeProcess;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author Generator
 * @date 2019-03-20 10:20:27.
 */
@Repository
public interface InvTaskBincodeProcessMapper extends MyMapperAndIds<InvTaskBincodeProcess> {
    /**
     * selectPage
     *
     * @param map condition
     * @return list
     */
    List<InvTaskBincodeProcess> selectPage(Map map);

    int updateInvBincodeStatus(InvTaskBincodeProcess invTaskBincodeProcess);

    List<InvTaskBincodeProcess> queryTheRemainingBincodeOfInvTask(@Param("srcInvNo") String srcInvNo);

    List<InvTaskBincodeProcess> inquiryBincodeTask(@Param("bincode") String bincode);

    List<InvTaskBincodeProcess> selectProcessBySrcInvNo(@Param("srcInvNo") String srcInvNo);
}