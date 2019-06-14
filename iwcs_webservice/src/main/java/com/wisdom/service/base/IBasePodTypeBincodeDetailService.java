package com.wisdom.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.base.dto.BasePodTypeBincodeDetailDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:02
 */
public interface IBasePodTypeBincodeDetailService {
    int insert(BasePodTypeBincodeDetailDTO record);

    int insertBatch(List<BasePodTypeBincodeDetailDTO> records);

    BasePodTypeBincodeDetailDTO selectByPrimaryKey(Integer id);

    List<BasePodTypeBincodeDetailDTO> selectSelective(BasePodTypeBincodeDetailDTO record);

    int updateByPrimaryKey(BasePodTypeBincodeDetailDTO record);

    int updateByPrimaryKeySelective(BasePodTypeBincodeDetailDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<BasePodTypeBincodeDetailDTO> selectPage(GridPageRequest gridPageRequest);
}
