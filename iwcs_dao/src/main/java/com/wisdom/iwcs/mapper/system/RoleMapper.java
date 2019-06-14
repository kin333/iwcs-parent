package com.wisdom.iwcs.mapper.system;


import com.wisdom.iwcs.domain.system.Role;
import com.wisdom.iwcs.domain.system.dto.RoleDTO;

import java.util.List;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);

    int insertInBatch(List<Role> records);

    int deleteById(Integer id);

    int deleteByObject(Role record);

    int deleteByIds(List<Integer> ids);

    int deleteAll();

    int updateById(Role record);

    int updateByIdSelective(Role record);

    Role selectOneById(Integer id);

    Role selectOneByObject(Role record);

    List<Role> selectByObject(Role record);

    List<Role> selectByRole(Role record);

    List<RoleDTO> selectRoleDTO(RoleDTO roleDTO);

    List<Role> selectAll();

    long count(Role record);

    long countAll();

    List<Role> selectByString(String searchKey);

    int updateToDelete(List<Integer> id);

    List<Role> getAllRoles();

    List<Role> getCompanyRole(Integer companyId);
}