package com.wisdom.iwcs.service.instock;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.instock.dto.instockrequest.InboundRequestDTO;

public interface IInboundUnloadService {
    Result inbound(InboundRequestDTO inboundRequestDTO);
}
