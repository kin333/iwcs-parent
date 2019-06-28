package com.wisdom.iwcs.service.elevator;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.elevator.dto.EleConfigDTO;

import java.util.List;

public interface IEleConfigService {
    int insert(EleConfigDTO record);

    int insertBatch(List<EleConfigDTO> records);

    EleConfigDTO selectByPrimaryKey(Integer id);

    List<EleConfigDTO> selectSelective(EleConfigDTO record);

    int updateByPrimaryKey(EleConfigDTO record);

    int updateByPrimaryKeySelective(EleConfigDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    GridReturnData<EleConfigDTO> selectPage(GridPageRequest gridPageRequest);
}
