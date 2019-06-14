package com.wisdom.iwcs.mapper.system;

import com.wisdom.iwcs.domain.system.SOperation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SOperationMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteByAuthorityId(@Param("authorityId") Integer authorityId);

    int insert(SOperation record);

    int insertSelective(SOperation record);

    SOperation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SOperation record);

    int updateByAuthorityId(SOperation record);

    int updateByPrimaryKey(SOperation record);

    List<SOperation> getByMenuId(@Param("menuId") Integer menuId);
}