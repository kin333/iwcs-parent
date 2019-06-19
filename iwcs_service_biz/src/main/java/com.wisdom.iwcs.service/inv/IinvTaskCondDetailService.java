package com.wisdom.iwcs.service.inv;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.inv.InvTaskCondDetail;
import com.wisdom.iwcs.domain.inv.dto.InvTaskCondDetailDTO;

import java.util.List;

/**
 * @Auther: panzun
 * @Date: 2019-3-20 14:40
 * @Description:
 */
public
interface IinvTaskCondDetailService {
    int storageInvCondition(InvTaskCondDetailDTO record);

    int storageInvConditionBatch(List<InvTaskCondDetailDTO> records);

    GridReturnData<InvTaskCondDetailDTO> selectPage(GridPageRequest gridPageRequest);

    InvTaskCondDetail getCorrespondCondInfo(String srcInvNo);

    List<InvTaskCondDetail> selectInvTaskCondDetail(String invNum);
}
