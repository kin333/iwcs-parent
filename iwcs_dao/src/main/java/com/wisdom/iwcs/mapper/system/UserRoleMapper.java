package com.wisdom.iwcs.mapper.system;

import com.wisdom.iwcs.domain.system.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    int deleteLogicByPrimaryKey(Integer id);

    int deleteAll();

    int deleteAllLogic();

    int insertBatch(List<UserRole> records);

    List<UserRole> selectSelective(UserRole record);

    int updateBatchByPrimaryKeySelective(List<UserRole> records);

    int deleteMore(List<Integer> ids);

    int deleteMoreLogic(List<Integer> ids);

    List<UserRole> selectPage(Map map);

    List<UserRole> selectByUserId(Integer userId);

    /**
     * 获取用户在指定公司下所有有效角色
     *
     * @param userId    userId
     * @param companyId companyId
     * @return 用户角色
     */
    List<UserRole> selectByUserIdAndCompanyId(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    int deleteByUserId(Integer userId);

    List<UserRole> getCompanyUserRole(@Param("userId") Integer userId, @Param("companyId") Integer companyId);

    int deleteUserCompanyRole(@Param("userId") Integer userId, @Param("companyId") Integer companyId);
}