package com.wisdom.iwcs.service.inv;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.inv.InvTaskResultDetail;
import com.wisdom.iwcs.domain.inv.dto.InvTaskResultDetailDTO;

import java.util.List;
import java.util.Map;

/**
 * @Auther: panzun
 * @Date: 2019-3-15 15:59
 * @Description:
 */
public
interface IinvTaskDetailService {
    int addInvTaskDetail(InvTaskResultDetail record);

    List<InvTaskResultDetail> selectBySrcInvNo(String srcInvNo);

    int deleteByPrimaryKey(Integer id);

    GridReturnData<InvTaskResultDetailDTO> selectPage(GridPageRequest gridPageRequest);

    String updateActualInvData(InvTaskResultDetailDTO invTaskResultDetailDTO);

    Map<String, Object> actuallyStartInv(String srcInvNo, String wbCode);
}
