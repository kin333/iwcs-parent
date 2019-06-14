package com.wisdom.service.system;

import com.wisdom.iwcs.common.utils.GroupTypeEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.system.Depart;
import com.wisdom.iwcs.domain.system.DepartUser;
import com.wisdom.iwcs.domain.system.dto.UserDataAuthDTO;
import com.wisdom.iwcs.mapper.system.DepartMapper;
import com.wisdom.iwcs.mapper.system.UserGroupMapper;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.system.extface.UserDataAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Devin
 * @date 2018-04-09.
 */
@Service
public class UserDataAuthServiceImpl implements UserDataAuthService {

    private final DepartMapper departMapper;

    private final UserGroupMapper userGroupMapper;

    @Autowired
    public UserDataAuthServiceImpl(DepartMapper departMapper, UserGroupMapper userGroupMapper) {
        this.departMapper = departMapper;
        this.userGroupMapper = userGroupMapper;
    }

    @Override
    public UserDataAuthDTO currentUserDataSelectAuth() {
        return null;
    }

    @Override
    public UserDataAuthDTO currentUserDataUpdateAuth() {
        return null;
    }


    @Override
    public List<Integer> getViewAllDepartmentId(Integer userId, Integer companyId) {
        List<DepartUser> departUsers = departMapper.companyDepartmentUserSelectAll(userId, companyId);
        departUsers.stream().map(DepartUser::getDepartId).collect(Collectors.toList());
        return departUsers.stream().map(DepartUser::getDepartId).collect(Collectors.toList());
    }

    @Override
    public List<Integer> getGroupMemberIds(Integer userId, Integer companyId, GroupTypeEnum groupTypeEnum) {
        List<Integer> groups = userGroupMapper.userManagedGroup(userId, companyId, groupTypeEnum.getCode());
        if (groups == null || groups.size() < 1) {
            return Collections.singletonList(userId);
        }
        List<Integer> userIds = userGroupMapper.getUserIdListInManagedGroup(groups);
        return userIds;
    }

    @Override
    public List<Integer> getViewSelfDepartmentId(Integer userId, Integer companyId) {
        List<DepartUser> departUsers = departMapper.companyDepartmentUserSelectSelf(userId, companyId);
        departUsers.stream().map(DepartUser::getDepartId).collect(Collectors.toList());
        return departUsers.stream().map(DepartUser::getDepartId).collect(Collectors.toList());
    }

    @Override
    public Depart getUserDepartment(Integer userId, Integer companyId) {
        Integer departmentId = getUserDepartmentId(userId, companyId);
        return departMapper.getDepartmentById(departmentId);
    }

    @Override
    public Integer getUserDepartmentId(Integer userId, Integer companyId) {
        DepartUser departUser = departMapper.selectByUserBelongDepartment(userId, companyId);
        if (departUser == null) {
            throw new BusinessException("用户没有所属部门，请检查");
        }
        return departUser.getDepartId();
    }

    @Override
    public List<Integer> getUserIdsInCurrentUserManagedGroup(GroupTypeEnum groupTypeEnum) {
        Integer userId = SecurityUtils.getCurrentUserId();
        Integer companyId = SecurityUtils.getCurrentCompanyId();
        //对应类型下 ，当前用户当组长的小组
        List<Integer> groups = userGroupMapper.userManagedGroup(userId, companyId, groupTypeEnum.getCode());
        if (groups == null || groups.size() < 1) {
            return new ArrayList<Integer>();
        }
        List<Integer> userIds = userGroupMapper.getUserIdListInManagedGroup(groups);
        return userIds;
    }

    @Override
    public List<Integer> getCompanyUserAllDepartmentId(Integer userId, Integer companyId) {
        List<Depart> userCompanyDepartments = departMapper.getUserCompanyDepartments(userId, companyId);
        return userCompanyDepartments.stream().map(Depart::getId).collect(Collectors.toList());
    }
}
