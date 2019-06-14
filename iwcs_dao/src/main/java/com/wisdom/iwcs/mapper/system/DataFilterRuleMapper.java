package com.wisdom.iwcs.mapper.system;


import com.wisdom.iwcs.domain.system.DataFilterRule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DataFilterRuleMapper {
    int insert(DataFilterRule record);

    int insertSelective(DataFilterRule record);

    int insertInBatch(List<DataFilterRule> records);

    int deleteById(Integer id);

    int deleteByAuthorityId(@Param("authorityId") int authorityId);

    int deleteByObject(DataFilterRule record);

    int deleteByIds(List<Integer> ids);

    int deleteAll();

    int updateById(DataFilterRule record);

    int updateByIdSelective(DataFilterRule record);

    int updateByAuthorityIdSelective(DataFilterRule record);

    DataFilterRule selectOneById(Integer id);

    DataFilterRule selectOneByObject(DataFilterRule record);

    List<DataFilterRule> selectByObject(DataFilterRule record);

    List<DataFilterRule> selectAll();

    long count(DataFilterRule record);

    long countAll();

    List<DataFilterRule> selectByIds(List<Integer> ids);

    List<DataFilterRule> selectDataFilterRuleByUserIdAndMenuRoleName(@Param("userId") Integer userId, @Param("menuRoleName") String menuRoleName);
}