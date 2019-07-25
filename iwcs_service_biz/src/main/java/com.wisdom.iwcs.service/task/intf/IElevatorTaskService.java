package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.elevator.ElevatorTaskRequest;

public interface IElevatorTaskService {
    Result elevatorTask(ElevatorTaskRequest elevatorTaskRequest);
}
