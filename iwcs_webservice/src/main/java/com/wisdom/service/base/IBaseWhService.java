package com.wisdom.service.base;

import com.wisdom.iwcs.domain.base.dto.BaseWhDTO;

import java.util.List;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/18 16:05
 */
public interface IBaseWhService {
    int insert(BaseWhDTO record);

    int insertBatch(List<BaseWhDTO> records);

    BaseWhDTO selectByPrimaryKey(Integer id);

    List<BaseWhDTO> selectSelective(BaseWhDTO record);

    int updateByPrimaryKey(BaseWhDTO record);

    int updateByPrimaryKeySelective(BaseWhDTO record);

    int deleteByPrimaryKey(Integer id);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteMore(List<String> ids);

    int deleteMoreLogic(List<String> ids);
}
