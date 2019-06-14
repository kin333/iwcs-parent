package com.wisdom.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.base.dto.BasePodTypeDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:03
 */
public interface IBasePodTypeService {
    int insert(BasePodTypeDTO record);

    int insertBatch(List<BasePodTypeDTO> records);

    BasePodTypeDTO selectByPrimaryKey(Integer id);

    List<BasePodTypeDTO> selectSelective(BasePodTypeDTO record);

    int updateByPrimaryKey(BasePodTypeDTO record);

    int updateByPrimaryKeySelective(BasePodTypeDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<BasePodTypeDTO> selectPage(GridPageRequest gridPageRequest);
}
