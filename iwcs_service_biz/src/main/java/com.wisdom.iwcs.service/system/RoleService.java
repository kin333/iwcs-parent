package com.wisdom.iwcs.service.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.Role;
import com.wisdom.iwcs.domain.system.UserRole;
import com.wisdom.iwcs.domain.system.dto.CompanyRoleDTO;
import com.wisdom.iwcs.domain.system.dto.RoleDTO;
import com.wisdom.iwcs.mapper.system.RoleMapper;
import com.wisdom.iwcs.mapper.system.UserRoleMapper;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class RoleService {
    private final Logger log = LoggerFactory.getLogger(RoleService.class);
    @Autowired
    private RoleMapper mRoleMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;

    public int add(Role role) {
        Integer companyId = SecurityUtils.getCurrentCompanyId();
        if (log.isInfoEnabled()) {
            log.info("添加角色:[角色信息:{}]", role);
        }

        Preconditions.checkNotNull(role.getRoleName(), ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);


        Integer userId = SecurityUtils.getCurrentUserId();
        Long nowTime = System.currentTimeMillis();
        if (role.getCompanyId() == null) {
            role.setCompanyId(companyId);
        }
        this.checkRoleExistByRoleNameInCompany(role.getRoleName(), role.getCompanyId());
        this.checkRoleExistByRoleCodeInCompany(role.getRolecode(), role.getCompanyId());

        role.setLastModifiedTime(nowTime);
        role.setCreatedTime(nowTime);
        role.setCreatedBy(userId);
        role.setLastModifiedBy(userId);
        int num = mRoleMapper.insertSelective(role);
        return num;
    }

    public int update(Role role) {
        if (log.isInfoEnabled()) {
            log.info("更新角色:[角色信息:{}]", role);
        }

        if (role.getCompanyId() == null) {
            throw new BusinessException("参数错误");
        }
        this.checkRoleExistByRoleIdAndRoleNameInCompany(role.getId(), role.getRoleName(), role.getCompanyId());
        this.checkRoleExistByRoleIdAndRoleCodeInCompany(role.getId(), role.getRolecode(), role.getCompanyId());

        Integer userId = SecurityUtils.getCurrentUserId();
        Long nowTime = System.currentTimeMillis();
        role.setLastModifiedTime(nowTime);
        role.setLastModifiedBy(userId);
        int num = mRoleMapper.updateByIdSelective(role);
        return num;

    }

    public int removeLogicalById(int id) {
        if (log.isInfoEnabled()) {
            log.info("删除角色(逻辑):[删除条件:{}]", id);
        }

        Role role = new Role();
        role.setId(id);
        Integer userId = SecurityUtils.getCurrentUserId();
        Long nowTime = System.currentTimeMillis();
        role.setLastModifiedTime(nowTime);
        role.setLastModifiedBy(userId);
        role.setDeleteFlag(false);
        int num = mRoleMapper.updateByIdSelective(role);
        return num;
    }

    public int removePhysicalById(int id) {
        if (log.isInfoEnabled()) {
            log.info("删除角色(物理):[删除条件:{}]", id);
        }

        int num = mRoleMapper.deleteById(id);
        return num;
    }

    public int removeLogicalById(List<Integer> id) {
        if (log.isInfoEnabled()) {
            log.info("删除角色(逻辑):[删除条件:{}]", id);
        }

        int num = mRoleMapper.updateToDelete(id);
        return num;
    }

    public GridReturnData<RoleDTO> list(RoleDTO role, Integer pageNum, Integer pageSize) {
        Integer companyId = SecurityUtils.getCurrentCompanyId();

        if (role.getCompanyId() != null && SecurityUtils.isSuperAdmin()) {
            role.setCompanyId(role.getCompanyId());
        } else {
            role.setCompanyId(companyId);
        }

        if (log.isInfoEnabled()) {
            log.info("角色列表(分页):[查询条件:{},pageNum:{},pageSize:{}]", role, pageNum, pageSize);
        }
        GridReturnData<RoleDTO> mGridReturnData = new GridReturnData<>();
        Preconditions.checkNotNull(pageNum, ApplicationErrorEnum.COMMON_PAGE_PARAMETER_NOT_FOUND_ERROR);
        Preconditions.checkNotNull(pageSize, ApplicationErrorEnum.COMMON_PAGE_PARAMETER_NOT_FOUND_ERROR);
        PageHelper.startPage(pageNum, pageSize);
        role.setDeleteFlag(false);
        List<RoleDTO> roleList = mRoleMapper.selectRoleDTO(role);
        PageInfo<RoleDTO> pageInfo = new PageInfo<RoleDTO>(roleList);
        mGridReturnData.setPageInfo(pageInfo);
        return mGridReturnData;
    }

    public Result getAllRoles() {
        List<Role> roleList = mRoleMapper.getAllRoles();
        return new Result(roleList);
    }

    public List<Role> list(Role role) {
        if (log.isInfoEnabled()) {
            log.info("角色列表:[查询条件:{}]", role);
        }

        List<Role> roles = mRoleMapper.selectByObject(role);
        return roles;
    }

    public Role getOne(Role roleCondition) {
        Role role = mRoleMapper.selectOneByObject(roleCondition);
        return role;
    }

    /**
     * 校验角色名称在分公司下是否重复
     *
     * @param roleName  角色名称
     * @param companyId 分公司
     */
    private void checkRoleExistByRoleNameInCompany(String roleName, Integer companyId) {
        Role roleCondition = new Role();
        roleCondition.setRoleName(roleName);
        roleCondition.setCompanyId(companyId);
        roleCondition.setDeleteFlag(false);
        List<Role> roles = mRoleMapper.selectByRole(roleCondition);
        Preconditions.checkArgument(roles == null || roles.size() < 1, ApplicationErrorEnum.ROLE_NAME_EXIST);
    }

    /**
     * 校验角色名称在分公司下是否重复
     *
     * @param roleName  角色名称
     * @param companyId 分公司
     */
    private void checkRoleExistByRoleIdAndRoleNameInCompany(Integer id, String roleName, Integer companyId) {
        Role roleCondition = new Role();
        roleCondition.setRoleName(roleName);
        roleCondition.setCompanyId(companyId);
        roleCondition.setDeleteFlag(false);
        List<Role> roles = mRoleMapper.selectByRole(roleCondition);
        if (roles != null && roles.size() > 0) {
            roles.forEach(r -> {
                if (!r.getId().equals(id)) {
                    throw new BusinessException(ApplicationErrorEnum.ROLE_NAME_EXIST.getResMsg());
                }
            });
        }
    }

    /**
     * 校验角色名称在分公司下是否重复
     *
     * @param roleCode  角色编码
     * @param companyId 分公司
     */
    private void checkRoleExistByRoleCodeInCompany(String roleCode, Integer companyId) {
        Role roleCondition = new Role();
        roleCondition.setRolecode(roleCode);
        roleCondition.setCompanyId(companyId);
        roleCondition.setDeleteFlag(false);
        List<Role> roles = mRoleMapper.selectByRole(roleCondition);
        Preconditions.checkArgument(roles == null || roles.size() < 1, ApplicationErrorEnum.ROLE_CODE_EXIST);
    }

    /**
     * 校验角色名称在分公司下是否重复
     *
     * @param roleCode  角色编码
     * @param companyId 分公司
     */
    private void checkRoleExistByRoleIdAndRoleCodeInCompany(Integer id, String roleCode, Integer companyId) {
        Role roleCondition = new Role();
        roleCondition.setRolecode(roleCode);
        roleCondition.setCompanyId(companyId);
        roleCondition.setDeleteFlag(false);
        List<Role> roles = mRoleMapper.selectByRole(roleCondition);
        if (roles != null && roles.size() > 0) {
            roles.forEach(r -> {
                if (!r.getId().equals(id)) {
                    throw new BusinessException(ApplicationErrorEnum.ROLE_CODE_EXIST.getResMsg());
                }
            });
        }
    }


//    private void checkRoleExistByRoleName(String roleName){
//        Role roleCondition = new Role();
//        roleCondition.setRoleName(roleName);
//        Role role = this.getOne(roleCondition);
//        Preconditions.checkArgument(role == null,ApplicationErrorEnum.ROLE_EXIST);
//    }

//    private void checkRoleExistByRoleIdAndName(Integer id,String roleName){
//        Role roleCondition = new Role();
//        roleCondition.setRoleName(roleName);
//        roleCondition.setId(id);
//        Role role = this.getOne(roleCondition);
//        if(role != null){
//            Preconditions.checkArgument(role.getId().equals(id),ApplicationErrorEnum.ROLE_EXIST);
//        }
//    }

    public Result getCompanyRole(CompanyRoleDTO companyRoleDTO) {
        List<Role> companyRoles = mRoleMapper.getCompanyRole(companyRoleDTO.getCompanyId());
        HashMap<String, Object> map = new HashMap<>();
        //该公司下所有可选角色
        map.put("companyRoles", companyRoles);
        if (companyRoleDTO.getUserId() != null && companyRoleDTO.getUserId() > 0) {
            List<UserRole> userRoles = userRoleMapper.selectByUserIdAndCompanyId(companyRoleDTO.getUserId(), companyRoleDTO.getCompanyId());
            //用户在该公司下选择的角色
            map.put("userRoles", userRoles);
        }
        return new Result(map);
    }
}
