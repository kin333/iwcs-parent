package com.wisdom.iwcs.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.base.dto.BaseMapSectionDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 15:59
 */
public interface IBaseMapSectionService {
    int insert(BaseMapSectionDTO record);

    int insertBatch(List<BaseMapSectionDTO> records);

    BaseMapSectionDTO selectByPrimaryKey(Integer id);

    List<BaseMapSectionDTO> selectSelective(BaseMapSectionDTO record);

    int updateByPrimaryKey(BaseMapSectionDTO record);

    int updateByPrimaryKeySelective(BaseMapSectionDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<BaseMapSectionDTO> selectPage(GridPageRequest gridPageRequest);
}
