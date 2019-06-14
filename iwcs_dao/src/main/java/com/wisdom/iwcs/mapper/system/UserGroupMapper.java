package com.wisdom.iwcs.mapper.system;

import com.wisdom.iwcs.common.utils.mapper.LogicDelete.DeleteLogicMapper;
import com.wisdom.iwcs.common.utils.mapper.MyMapperAndIds;
import com.wisdom.iwcs.domain.system.UserGroup;
import com.wisdom.iwcs.domain.system.dto.UserGroupDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserGroupMapper extends MyMapperAndIds<UserGroup>, DeleteLogicMapper<UserGroup> {
    List<UserGroup> selectPage(Map map);

    List<UserGroupDto> getGroupUsers(Map map);

    List<UserGroup> selectUserIdAndGroupId(@Param("userId") Integer userId, @Param("groupId") Integer groupId);

    List<UserGroup> getManagedGroupByUserId(@Param("userId") Integer userId, @Param("groupType") Integer groupType);

    List<Integer> getManagedGroupIdByUserId(@Param("userId") Integer userId, @Param("groupType") Integer groupType, @Param("companyId") Integer companyId);

    List<Integer> getUserIdListInManagedGroup(@Param("userGroupIds") List<Integer> userGroupIds);


    List<Integer> userManagedGroup(@Param("userId") Integer userId, @Param("companyId") Integer companyId, @Param("groupType") String groupType);

    List<Integer> userManagedAllGroup(@Param("userId") Integer userId, @Param("companyId") Integer companyId);
}