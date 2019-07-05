package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.PlToAgingRequest;

public interface IPlToAgingService {
    Result agingToQuaInsp(PlToAgingRequest plToAgingRequest);
}
