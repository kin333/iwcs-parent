package com.wisdom.service.callHik;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.control.EndTaskRequestDTO;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:07
 */
public interface IEndTaskService {
    Result endTaskByWbCode(EndTaskRequestDTO requestDTO);
}
