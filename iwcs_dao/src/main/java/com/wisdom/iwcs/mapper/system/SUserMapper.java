package com.wisdom.iwcs.mapper.system;

import com.wisdom.iwcs.domain.system.SUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SUser record);

    int insertSelective(SUser record);

    SUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SUser record);

    int updateByPrimaryKey(SUser record);

    //    @DataFilter(value = true, authority = "ROLE_USER")
    List<SUser> selectByPage(Map map);

    int deleteByIds(List<Integer> list);

    SUser selectByUserName(String loginName);

    SUser findOneByUserNameAndStatus(@Param("userName") String userName, @Param("status") String status);

    SUser findOneByUserNameAndCompanyIdAndStatus(@Param("userName") String userName, @Param("companyId") String companyId, @Param("status") String status);


    int enabledUsers(List<Integer> list);

    int disabledUsers(List<Integer> list);


    List<SUser> getAllUsers();

    List<SUser> getCompanyUsers(Integer companyId);


    List<SUser> getUsersByIds(List<Integer> list);

    int updateUserPassword(@Param("userId") Integer userId, @Param("password") String password);

    List<SUser> userNoInThisList(List<Integer> list);


    List<SUser> getUserByMap(Map map);


    Integer checkUserNameExist(@Param("userName") String userName, @Param("userId") Integer userId);
}