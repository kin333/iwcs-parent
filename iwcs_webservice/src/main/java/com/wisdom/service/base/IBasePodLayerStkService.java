package com.wisdom.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.base.dto.BasePodLayerStkDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:02
 */
public interface IBasePodLayerStkService {
    int insert(BasePodLayerStkDTO record);

    int insertBatch(List<BasePodLayerStkDTO> records);

    BasePodLayerStkDTO selectByPrimaryKey(Integer id);

    List<BasePodLayerStkDTO> selectSelective(BasePodLayerStkDTO record);

    int updateByPrimaryKey(BasePodLayerStkDTO record);

    int updateByPrimaryKeySelective(BasePodLayerStkDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<BasePodLayerStkDTO> selectPage(GridPageRequest gridPageRequest);
}
