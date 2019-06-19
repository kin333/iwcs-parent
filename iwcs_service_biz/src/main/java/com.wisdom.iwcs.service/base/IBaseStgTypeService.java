package com.wisdom.iwcs.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.base.dto.BaseStgTypeDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:03
 */
public interface IBaseStgTypeService {
    int insert(BaseStgTypeDTO record);

    int insertBatch(List<BaseStgTypeDTO> records);

    BaseStgTypeDTO selectByPrimaryKey(Integer id);

    List<BaseStgTypeDTO> selectSelective(BaseStgTypeDTO record);

    int updateByPrimaryKey(BaseStgTypeDTO record);

    int updateByPrimaryKeySelective(BaseStgTypeDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<BaseStgTypeDTO> selectPage(GridPageRequest gridPageRequest);
}
