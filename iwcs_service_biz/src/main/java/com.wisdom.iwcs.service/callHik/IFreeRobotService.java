package com.wisdom.iwcs.service.callHik;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.control.GenAgvSchedulingRequestDTO;

public interface IFreeRobotService {

    Result freeRobot(GenAgvSchedulingRequestDTO genAgvSchedulingRequestDTO);
}
