package com.wisdom.iwcs.service.task.intf;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.PackWbCallPodRequest;

public interface IPackWbCallPodService {
    Result elevatorTask(PackWbCallPodRequest packWbCallPodRequest);
}
