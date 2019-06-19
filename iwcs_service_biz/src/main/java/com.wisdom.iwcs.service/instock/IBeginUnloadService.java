package com.wisdom.iwcs.service.instock;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.instock.dto.instockrequest.BeginUnloadRequestDTO;

public interface IBeginUnloadService {
    Result instockCall(BeginUnloadRequestDTO requestDTO);
}
