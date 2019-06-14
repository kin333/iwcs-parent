package com.wisdom.service.inv;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.inv.InvSn;
import com.wisdom.iwcs.domain.inv.dto.InvSnDTO;

/**
 * @Auther: panzun
 * @Date: 2019-3-15 16:18
 * @Description:
 */
public
interface IinvSnService {
    int addInvSnDetail(InvSnDTO record);

    GridReturnData<InvSnDTO> selectPage(GridPageRequest gridPageRequest);

    int updateInvSNResults(InvSn invSn);
}
