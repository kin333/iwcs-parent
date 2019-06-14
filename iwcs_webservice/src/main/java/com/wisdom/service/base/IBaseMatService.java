package com.wisdom.service.base;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseMatDTO;

import java.util.List;

/**
 * @Description: 基础物料
 * @Author: george
 * @CreateDate: 2019/3/15 16:02
 */
public interface IBaseMatService {
    /**
     * 保存物料
     *
     * @param baseMatDTOList
     * @return
     */
    Result saveMat(List<BaseMatDTO> baseMatDTOList);

    /**
     * 物料同步 test
     *
     * @param
     * @return
     */
    void testSyncMat() throws Exception;
}
