package com.wisdom.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.base.dto.BaseMapDTO;

import java.util.List;

public interface IBaseMapService {
    int insert(BaseMapDTO record);

    int insertBatch(List<BaseMapDTO> records);

    BaseMapDTO selectByPrimaryKey(Integer id);

    List<BaseMapDTO> selectSelective(BaseMapDTO record);

    int updateByPrimaryKey(BaseMapDTO record);

    int updateByPrimaryKeySelective(BaseMapDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<BaseMapDTO> selectPage(GridPageRequest gridPageRequest);
}
