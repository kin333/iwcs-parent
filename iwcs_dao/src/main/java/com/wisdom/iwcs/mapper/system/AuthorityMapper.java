package com.wisdom.iwcs.mapper.system;

import com.wisdom.iwcs.domain.system.Authority;

import java.util.List;

public interface AuthorityMapper {
    int insert(Authority record);

    int insertSelective(Authority record);

    int insertInBatch(List<Authority> records);

    int deleteById(Integer id);

    int deleteByObject(Authority record);

    int deleteByIds(List<Integer> ids);

    int deleteAll();

    int updateById(Authority record);

    int updateByIdSelective(Authority record);

    Authority selectOneById(Integer id);

    Authority selectOneByObject(Authority record);

    List<Authority> selectByObject(Authority record);

    List<Authority> selectAll();

    long count(Authority record);

    long countAll();

    List<Authority> selectByIds(List<Integer> authIds);

    List<Authority> getButtonAuthByParentId(Integer parentId);
}