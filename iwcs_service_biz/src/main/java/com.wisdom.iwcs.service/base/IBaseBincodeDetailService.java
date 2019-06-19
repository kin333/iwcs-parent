package com.wisdom.iwcs.service.base;


import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.base.dto.BaseBincodeDetailDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 15:55
 */
public interface IBaseBincodeDetailService {
    int insert(BaseBincodeDetailDTO record);

    int insertBatch(List<BaseBincodeDetailDTO> records);

    BaseBincodeDetailDTO selectByPrimaryKey(Integer id);

    List<BaseBincodeDetailDTO> selectSelective(BaseBincodeDetailDTO record);

    int updateByPrimaryKey(BaseBincodeDetailDTO record);

    int updateByPrimaryKeySelective(BaseBincodeDetailDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<BaseBincodeDetailDTO> selectPage(GridPageRequest gridPageRequest);
}
