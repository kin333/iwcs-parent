package com.wisdom.iwcs.mapper.system;

import com.wisdom.iwcs.domain.system.UserInfoExt;

import java.util.List;
import java.util.Map;

public interface UserInfoExtMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoExt record);

    int insertSelective(UserInfoExt record);

    UserInfoExt selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfoExt record);

    int updateByPrimaryKey(UserInfoExt record);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteAll();

    int deleteAllLogic();

    int insertBatch(List<UserInfoExt> records);

    List<UserInfoExt> selectSelective(UserInfoExt record);

    int updateBatchByPrimaryKeySelective(List<UserInfoExt> records);

    int deleteMore(List<Integer> ids);

    int deleteMoreLogic(List<Integer> ids);

    List<UserInfoExt> selectPage(Map map);
}