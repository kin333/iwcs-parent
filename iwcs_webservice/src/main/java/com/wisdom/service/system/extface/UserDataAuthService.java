package com.wisdom.service.system.extface;

import com.wisdom.iwcs.common.utils.GroupTypeEnum;
import com.wisdom.iwcs.domain.system.Depart;
import com.wisdom.iwcs.domain.system.dto.UserDataAuthDTO;

import java.util.List;

/**
 * 用户数据权限相关接口
 *
 * @author Devin
 * @date 2018-04-03.
 */
public interface UserDataAuthService {
    /**
     * 获取当前登录用户的数据查看权限
     *
     * @return 用户的数据权限
     */
    UserDataAuthDTO currentUserDataSelectAuth();

    /**
     * 获取当前登录用户的数据修改权限
     *
     * @return 用户的数据权限
     */
    UserDataAuthDTO currentUserDataUpdateAuth();

    /**
     * 获取用户所在公司能查看所有的部门
     */
    List<Integer> getViewAllDepartmentId(Integer userId, Integer companyId);

    /**
     * 索取用户所在组成员，当前仅当用户为组长时能够获取到列表
     *
     * @param userId        用户ID
     * @param companyId     子公司ID
     * @param groupTypeEnum 小组类型
     * @return member 列表
     */
    List<Integer> getGroupMemberIds(Integer userId, Integer companyId, GroupTypeEnum groupTypeEnum);

    /**
     * 获取用户所在公司只能查看自己数据的部门(作废)
     * use getCompanyUserAllDepartmentId
     */
    @Deprecated
    List<Integer> getViewSelfDepartmentId(Integer userId, Integer companyId);

    /**
     * 获取用户在公司下的所属部门
     *
     * @param userId    userId
     * @param companyId companyId
     * @return 所属部门
     */
    Depart getUserDepartment(Integer userId, Integer companyId);

    /**
     * 获取用户在公司下的所属部门
     *
     * @param userId    userId
     * @param companyId companyId
     * @return 所属部门Id
     */
    Integer getUserDepartmentId(Integer userId, Integer companyId);


    /**
     * （通过小组）当前登录用户能看那些人的数据，
     *
     * @param groupTypeEnum 小组类型
     * @return 这些人的用户id
     */
    List<Integer> getUserIdsInCurrentUserManagedGroup(GroupTypeEnum groupTypeEnum);


    /**
     * 获取用户的所有部门（所属＋管辖）
     */
    List<Integer> getCompanyUserAllDepartmentId(Integer userId, Integer companyId);
}
