package com.wisdom.iwcs.service.callHik;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.control.ContinueTaskRequestDTO;


public interface IContinueTaskService {

    Result continueTask(ContinueTaskRequestDTO continueTaskRequestDTO);
}
