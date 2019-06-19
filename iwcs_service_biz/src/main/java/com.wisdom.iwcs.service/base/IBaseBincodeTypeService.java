package com.wisdom.iwcs.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.base.dto.BaseBincodeTypeDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 15:57
 */
public interface IBaseBincodeTypeService {
    int insert(BaseBincodeTypeDTO record);

    int insertBatch(List<BaseBincodeTypeDTO> records);

    BaseBincodeTypeDTO selectByPrimaryKey(Integer id);

    List<BaseBincodeTypeDTO> selectSelective(BaseBincodeTypeDTO record);

    int updateByPrimaryKey(BaseBincodeTypeDTO record);

    int updateByPrimaryKeySelective(BaseBincodeTypeDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<BaseBincodeTypeDTO> selectPage(GridPageRequest gridPageRequest);
}
