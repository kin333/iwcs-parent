package com.wisdom.iwcs.service.callHik;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.control.CancelTaskRequestDTO;

public interface ICancelTaskService {
    Result cancelTask(CancelTaskRequestDTO cancelTaskRequestDTO);
}
