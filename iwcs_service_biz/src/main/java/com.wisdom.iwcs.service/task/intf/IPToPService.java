package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.PToPRequest;

public interface IPToPService {
    Result pTop(PToPRequest pToPRequest);
}
