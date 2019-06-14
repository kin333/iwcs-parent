package com.wisdom.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.base.dto.BasePodBincodeDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:01
 */
public interface IBasePodBincodeService {
    int insert(BasePodBincodeDTO record);

    int insertBatch(List<BasePodBincodeDTO> records);

    BasePodBincodeDTO selectByPrimaryKey(Integer id);

    List<BasePodBincodeDTO> selectSelective(BasePodBincodeDTO record);

    int updateByPrimaryKey(BasePodBincodeDTO record);

    int updateByPrimaryKeySelective(BasePodBincodeDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<BasePodBincodeDTO> selectPage(GridPageRequest gridPageRequest);
}
