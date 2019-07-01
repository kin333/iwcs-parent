package com.wisdom.iwcs.service.base;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseWhAreaDTO;
import com.wisdom.iwcs.domain.system.dto.LoginDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:05
 */
public interface IBaseWhAreaService {
    int insert(BaseWhAreaDTO record);

    int insertBatch(List<BaseWhAreaDTO> records);

    BaseWhAreaDTO selectByPrimaryKey(Integer id);

    List<BaseWhAreaDTO> selectSelective(BaseWhAreaDTO record);

    int updateByPrimaryKey(BaseWhAreaDTO record);

    int updateByPrimaryKeySelective(BaseWhAreaDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);

    GridReturnData<BaseWhAreaDTO> selectPage(GridPageRequest gridPageRequest);

    Result checkWhAreaAndUser(LoginDTO loginDTO);
}
