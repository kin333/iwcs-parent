package com.wisdom.iwcs.service.inv;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.inv.dto.InvTaskBincodeProcessDTO;

/**
 * @Auther: panzun
 * @Date: 2019-3-20 14:18
 * @Description:
 */
public
interface IinvTaskBincodeProcessService {
    int addInvTaskBincode(InvTaskBincodeProcessDTO record);


    int updateInvBincodeStatus(InvTaskBincodeProcessDTO record);

    GridReturnData<InvTaskBincodeProcessDTO> selectPage(GridPageRequest gridPageRequest);
}
