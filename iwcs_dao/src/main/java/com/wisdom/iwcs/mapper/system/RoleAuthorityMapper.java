package com.wisdom.iwcs.mapper.system;

import com.wisdom.iwcs.domain.system.RoleAuthority;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleAuthorityMapper {
    int insert(RoleAuthority record);

    int insertSelective(RoleAuthority record);

    int insertInBatch(List<RoleAuthority> records);

    int deleteById(Integer id);

    int deleteByObject(RoleAuthority record);

    int deleteByIds(List<Integer> ids);

    int deleteAll();

    int updateById(RoleAuthority record);

    int updateByIdSelective(RoleAuthority record);

    RoleAuthority selectOneById(Integer id);

    RoleAuthority selectOneByObject(RoleAuthority record);

    List<RoleAuthority> selectByObject(RoleAuthority record);

    List<RoleAuthority> selectAll();

    long count(RoleAuthority record);

    long countAll();

    List<RoleAuthority> selectByRoleIds(List<Integer> ids);

    List<RoleAuthority> selectByRoleId(Integer id);

    int updateByObject(RoleAuthority roleAuthority);

    List<RoleAuthority> selectByRoleIdsAndMenuId(@Param("roleIds") List<Integer> roleIds, @Param("menuId") Integer menuId);

    List<RoleAuthority> selectByAuthorityId(@Param("authorityId") Integer authorityId);

    int deleteByRoleIdAndAuthType(@Param("roleId") Integer roleId, @Param("authType") Integer authType);

    int deleteByRoleIdAndAuthTypeAndAuthParentId(@Param("roleId") Integer roleId, @Param("authType") Integer authType, @Param("authParentId") Integer authParentId);

}