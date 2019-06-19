package com.wisdom.iwcs.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BasePodDTO;
import com.wisdom.iwcs.domain.base.dto.ShowPodInfoCondDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:02
 */
public interface IBasePodService {
    int insert(BasePodDTO record);

    int insertBatch(List<BasePodDTO> records);

    BasePodDTO selectByPrimaryKey(Integer id);

    List<BasePodDTO> selectSelective(BasePodDTO record);

    int updateByPrimaryKey(BasePodDTO record);

    int updateByPrimaryKeySelective(BasePodDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<BasePodDTO> selectPage(GridPageRequest gridPageRequest);

    /**
     * 工作台拉取货架展示信息
     *
     * @param request
     * @return
     */
    Result showPodInfo(ShowPodInfoCondDTO request);
}
