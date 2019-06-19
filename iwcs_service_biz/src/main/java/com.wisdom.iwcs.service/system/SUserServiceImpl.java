package com.wisdom.iwcs.service.system;

import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.system.Depart;
import com.wisdom.iwcs.mapper.system.DepartMapper;
import com.wisdom.iwcs.mapper.system.SUserMapper;
import com.wisdom.iwcs.mapper.system.UserGroupMapper;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.system.extface.SUserInterface;
import com.wisdom.iwcs.service.system.extface.UserDataAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Service
public class SUserServiceImpl implements SUserInterface {
    private final Logger log = LoggerFactory.getLogger(SUserServiceImpl.class);

    @Autowired
    private SUserMapper sUserMapper;
    @Autowired
    private DepartMapper departMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private UserDataAuthService userDataAuthService;

    private Integer DIGITS = 4;

    @Override
    public List<Integer> getUserIdsFromCanvassUserGroupAndOrganizeById(Integer currentUserId, Integer companyId) {
        List<Integer> userIdList = userGroupService.getCanvassUserIdsInManagedGroup(currentUserId, companyId);

        if (userIdList == null || userIdList.size() < 1) {
            return Arrays.asList(currentUserId);
        }
        return userIdList;
    }

    @Override
    public List<Integer> getUserIdsFromOperUserGroupAndOrganizeById(Integer currentUserId, Integer companyId) {

        List<Integer> userIdList = userGroupService.getOperUserIdsInManagedGroup(currentUserId, companyId);
        if (userIdList == null || userIdList.size() < 1) {
            return Arrays.asList(currentUserId);
        }


        return userIdList;
    }

    @Override
    public List<Integer> getUserIdsFromBusinessUserGroupAndOrganizeById(Integer currentUserId, Integer companyId) {
        List<Integer> userIdList = userGroupService.getUserIdsFromBusinessUserGroupAndOrganizeById(currentUserId, companyId);
        if (userIdList == null || userIdList.size() < 1) {
            return Arrays.asList(currentUserId);
        }
        return userIdList;
    }

    @Override
    public String getDepartmentCode(Integer departmentId) {
        Depart department = departMapper.getDepartmentById(departmentId);
        if (department == null || StringUtils.isEmpty(department.getOrgCode()) || department.getOrgCode().length() < DIGITS) {
            throw new BusinessException("部门不存在或部门编码为空或编码长度小于" + DIGITS);
        }
        String departmentCode = department.getOrgCode();
        return departmentCode.substring(0, 4);
    }

    @Override
    public String getCurrentUserDepartmentCode() {
        Integer userId = SecurityUtils.getCurrentUserId();
        Integer companyId = SecurityUtils.getCurrentCompanyId();
        if (userId == null || companyId == null) {
            throw new BusinessException("用户未登录");
        }
        Depart department = userDataAuthService.getUserDepartment(userId, companyId);

        return getDepartmentCode(department.getId());
    }
}
