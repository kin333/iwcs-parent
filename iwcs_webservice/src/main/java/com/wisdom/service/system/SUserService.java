package com.wisdom.service.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wisdom.iwcs.common.utils.GridFilterInfo;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.UserCompanyDutyEnum;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.*;
import com.wisdom.iwcs.domain.system.dto.*;
import com.wisdom.iwcs.mapper.system.*;
import com.wisdom.security.SecurityUtils;
import com.wisdom.service.system.extface.UserDataAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

@Service
@Transactional
public class SUserService {

    private final Logger log = LoggerFactory.getLogger(SUserService.class);

    /**
     * 全局数组
     **/
    private final static String[] strDigits = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};

    @Autowired
    private SUserMapper sUserMapper;

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Autowired
    private AuthorityMapper authorityMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private DepartMapper departMapper;

    @Autowired
    private UserCompanySettingsMapper userCompanySettingsMapper;
    @Autowired
    private UserDataAuthService userDataAuthService;


    /**
     * 当前登录用户信息
     */
    public Result getCurrentUserInfo() {

        Integer currentUserId = SecurityUtils.getCurrentUserId();
        Integer currentCompanyId = SecurityUtils.getCurrentCompanyId();
        SUser user = sUserMapper.selectByPrimaryKey(currentUserId);

        if (user == null) {
            return new Result(4001, "用户不存在");
        }
        CurrentUserInfo currentUserInfo = new CurrentUserInfo();
        currentUserInfo.setId(user.getId());
        currentUserInfo.setUserName(user.getUserName());
        currentUserInfo.setRealName(user.getRealName());
        currentUserInfo.setCurrentCompanyId(currentCompanyId);

        currentUserInfo.setEmail(user.getEmail());
        currentUserInfo.setMobile(user.getMobile());

        Depart company = departMapper.getDepartmentById(currentCompanyId);
        currentUserInfo.setCurrentCompanyName(company.getDepartname());

        currentUserInfo.setSuperAdmin(SecurityUtils.isSuperAdmin());
        //获取权限
        Set<String> authorities = getAuthoritiesByUserIdAndCompanyId(user.getId(), String.valueOf(SecurityUtils.getCurrentCompanyId()));
        currentUserInfo.setAuthorities(authorities);
        //部门信息
        // 有可能未设置所属部门,该用户在现在登录的分公司没有任何归属部门
        try {
            Depart department = userDataAuthService.getUserDepartment(currentUserId, currentCompanyId);
            if (department != null) {
                currentUserInfo.setDepartmentId(department.getId());
                currentUserInfo.setDepartmentName(department.getDepartname());
            }

        } catch (Exception e) {

        }

        return new Result(currentUserInfo);
    }


    public Result deleteByPrimaryKey(Integer id) {
        int returnNum = sUserMapper.deleteByPrimaryKey(id);
        return new Result(returnNum);
    }

    /**
     * 设置用户角色
     **/
    private void setUserRoles(List<Integer> roleIds, Integer userId) {
        if (roleIds != null && roleIds.size() > 0) {
            List<UserRole> userRoles = new ArrayList<>();
            roleIds.forEach(roleId -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(userId);
                userRole.setRoleId(roleId);
                userRoles.add(userRole);
            });
            userRoleMapper.insertBatch(userRoles);
        }
    }

    /**
     * 设置所属部门
     */
    public void setUserCompanyDepartment(UserDTO userDTO, Integer currentCompanyId) {
        // 删除原先的所属部门
        DepartUser departUser = new DepartUser();
        departUser.setUserId(userDTO.getId());
        departUser.setCompanyId(currentCompanyId);
        departUser.setDepartId(userDTO.getDepartmentId());
        departMapper.deleteUserBelongDepartment(departUser);

        DepartUser newDepartmentUser = new DepartUser();
        newDepartmentUser.setUserId(userDTO.getId());
        newDepartmentUser.setDepartId(userDTO.getDepartmentId());
        newDepartmentUser.setCompanyId(currentCompanyId);
        newDepartmentUser.setSelectAll(userDTO.getSelectAll());
        newDepartmentUser.setRelationType(2);
        departMapper.addDepartUser(newDepartmentUser);
    }

    /**
     * 设置角色
     */
    public void setCompanyRole(List<Integer> roleIds, Integer userId, Integer companyId) {
        //删除账号公司下的原角色
        userRoleMapper.deleteUserCompanyRole(userId, companyId);
        // 添加角色
        setUserRoles(roleIds, userId);
    }

    /**
     * 添加一个账号
     */
    public Result insertSelective(UserDTO record) {
        Integer userId = SecurityUtils.getCurrentUserId();
        String addUserName = record.getUserName();
        SUser sUser = sUserMapper.selectByUserName(addUserName);
        if (sUser != null) {
            return new Result(4001, "用户已存在");
        }
        record.setCreatedBy(userId);
        record.setCreatedTime(new Date());
        record.setLastModifiedBy(userId);
        record.setLastModifiedTime(new Date());
        record.setDeleteFlag(0);
        record.setPassword(GetMD5Code(record.getPassword()));

        int returnNum = sUserMapper.insertSelective(record);

        Integer currentCompanyId = SecurityUtils.getCurrentCompanyId();
        //超级管理员可以指定公司
        if (record.getCompanyId() != null && SecurityUtils.isSuperAdmin()) {
            currentCompanyId = record.getCompanyId();
        }
        // 部门
        setUserCompanyDepartment(record, currentCompanyId);

        // 角色
        this.setUserRoles(record.getRoleList(), record.getId());

        // 用户职责
        if (record.getDutyList() != null && record.getDutyList().size() > 0) {
            List<String> userDutyList = record.getDutyList();
            setUserCompanyDuty(userDutyList, currentCompanyId, record.getId());
        }

        return new Result(returnNum);
    }

    /**
     * 设置用户公司职责
     */
    private void setUserCompanyDuty(List<String> userDutyList, Integer companyId, Integer userId) {
        UserCompanySettings userCompanySettings = new UserCompanySettings();
        userCompanySettings.setCompanyId(companyId);
        userCompanySettings.setUserId(userId);

        if (userDutyList.contains(UserCompanyDutyEnum.BUSINESS.getCode())) {
            userCompanySettings.setIsBusiness(1);
        } else {
            userCompanySettings.setIsBusiness(0);
        }
        if (userDutyList.contains(UserCompanyDutyEnum.SALES.getCode())) {
            userCompanySettings.setIsSales(1);
        } else {
            userCompanySettings.setIsSales(0);
        }
        if (userDutyList.contains(UserCompanyDutyEnum.OPERATION.getCode())) {
            userCompanySettings.setIsOperation(1);
        } else {
            userCompanySettings.setIsOperation(0);
        }
        if (userDutyList.contains(UserCompanyDutyEnum.SERVICE.getCode())) {
            userCompanySettings.setIsService(1);
        } else {
            userCompanySettings.setIsService(0);
        }
        if (userDutyList.contains(UserCompanyDutyEnum.DOCUMENT.getCode())) {
            userCompanySettings.setIsDocument(1);
        } else {
            userCompanySettings.setIsDocument(0);
        }
        userCompanySettingsMapper.insertSelective(userCompanySettings);
    }

    public Result selectByPrimaryKey(Integer id) {
        SUser sUser = sUserMapper.selectByPrimaryKey(id);
        if (sUser != null) {
            return new Result(sUser);
        } else {
            return new Result(4001, "用户不存在");
        }
    }

    /**
     * 更新用户信息
     *
     * @param record
     * @return
     */
    public Result updateByPrimaryKeySelective(UserDTO record) {
        SUser sUser = sUserMapper.selectByPrimaryKey(record.getId());
        if (sUser == null) {
            return new Result(4001, "用户不存在");
        }
        Integer userNameCount = sUserMapper.checkUserNameExist(record.getUserName(), record.getId());
        if (userNameCount != null && userNameCount > 0) {
            throw new BusinessException("用户名已存在");
        }

        Integer userId = SecurityUtils.getCurrentUserId();
        record.setLastModifiedBy(userId);
        record.setLastModifiedTime(new Date());
        int returnNum = sUserMapper.updateByPrimaryKeySelective(record);

        Integer currentCompanyId = SecurityUtils.getCurrentCompanyId();
        // 超级管理员可以指定公司
        if (record.getCompanyId() != null && SecurityUtils.isSuperAdmin()) {
            currentCompanyId = record.getCompanyId();
        }
        setUserCompanyDepartment(record, currentCompanyId);

        setCompanyRole(record.getRoleList(), record.getId(), currentCompanyId);

        userCompanySettingsMapper.deleteByUserIdAndCompanyId(record.getId(), currentCompanyId);

        //用户职责
        if (record.getDutyList() != null && record.getDutyList().size() > 0) {
            List<String> userDutyList = record.getDutyList();
            setUserCompanyDuty(userDutyList, currentCompanyId, record.getId());
        }
        return new Result(returnNum);
    }


    public Result selectByPage(UserGridPageRequest gridPageRequest) {
        Integer userId = SecurityUtils.getCurrentUserId();
        Integer companyId = SecurityUtils.getCurrentCompanyId();
        GridReturnData<SUser> mGridReturnData = new GridReturnData<>();
        List<GridFilterInfo> filterList = gridPageRequest.getFilterList();
        Map map = new HashMap();
        if (gridPageRequest.getSearchKey() != null && !"".equals(gridPageRequest.getSearchKey())) {
            map.put("searchKey", gridPageRequest.getSearchKey());
        }
        if (gridPageRequest.getId() != null && gridPageRequest.getId() != 0) {
            map.put("id", gridPageRequest.getId());
        }

        // 超级管理员可以指定公司
        if (gridPageRequest.getCompanyId() != null && SecurityUtils.isSuperAdmin()) {
            companyId = gridPageRequest.getCompanyId();
        }
        map.put("companyId", companyId);

        String sortMyBatisByString = gridPageRequest.getSortMybatisString();
        PageHelper.startPage(gridPageRequest.getPageNum(), gridPageRequest.getPageSize(), sortMyBatisByString);
        List<SUser> list = sUserMapper.selectByPage(map);
        PageInfo<SUser> pageInfo = new PageInfo<SUser>(list);
        mGridReturnData.setPageInfo(pageInfo);

        return new Result(mGridReturnData);
    }

    public Result deleteByIds(List<Integer> ids) {
        return new Result(sUserMapper.deleteByIds(ids));
    }

    public SUser getOneByUserId(Integer userId) {
        if (log.isInfoEnabled()) {
            log.info("按用户id查询用户:[条件：{}]", userId);
        }
        SUser user = sUserMapper.selectByPrimaryKey(userId);
        return user;
    }

    /**
     * 获取用户所有角色（包括用户本身和所在部门带着的角色）
     */
    @Deprecated
    public List<Integer> getUserAllRoleIdsByUserId(Integer userId) {
        //查询用户角色
        List<UserRole> userRoleList = userRoleMapper.selectByUserId(userId);
        List<Integer> userRoleIds = userRoleList.stream().map(UserRole::getRoleId).collect(toList());
        //查询用户部门，查出部门所带的角色
        List<Integer> departmentRoleIds = new ArrayList<>();
        List<DepartUser> departUsers = departMapper.selectDepartmentsByUserId(userId);
        if (departUsers != null && departUsers.size() > 0) {
            List<Integer> departments = departUsers.stream().map(DepartUser::getDepartId).collect(toList());
            List<DepartRole> departRoles = departMapper.selectDepartmentRolesByDepartmentIds(departments);
            if (departRoles != null && departRoles.size() > 0) {
                departmentRoleIds = departRoles.stream().map(DepartRole::getRoleId).collect(toList());
            }
        }
        //组合
        if (departmentRoleIds != null && departmentRoleIds.size() > 0) {
            userRoleIds.addAll(departmentRoleIds);
        }
        return userRoleIds;
    }

    /**
     * 获取用户在某公司下有效角色
     *
     * @param userId    userId
     * @param companyId companyId
     * @return 角色id集合
     */
    public List<Integer> getUserAllRoleIdsByUserIdAndCompanyId(Integer userId, Integer companyId) {
        //查询用户角色
        List<UserRole> userRoleList = userRoleMapper.selectByUserIdAndCompanyId(userId, companyId);
        List<Integer> roleIds = userRoleList.stream().map(UserRole::getRoleId).collect(toList());
        return roleIds;
    }


    /**
     * 获取在指定公司下的权限
     *
     * @param userId          用户id
     * @param companyIdString 分公司id
     * @return 权限Set
     */
    public Set<String> getAuthoritiesByUserIdAndCompanyId(Integer userId, String companyIdString) {

        Integer companyId = Integer.valueOf(companyIdString);

        //查询用户角色
        List<Integer> validRoleIds = getUserAllRoleIdsByUserIdAndCompanyId(userId, companyId);

        // Preconditions.checkArgument(validRoleIds!=null&&validRoleIds.size()>0, ApplicationErrorEnum.USER_NOT_HAVE_ANY_ROLES);
        if (validRoleIds == null || validRoleIds.size() < 1) {
            return new HashSet<>();
        }

        List<RoleAuthority> roleAuthorities = roleAuthorityMapper.selectByRoleIds(validRoleIds);
        Preconditions.checkArgument(roleAuthorities.size() > 0, ApplicationErrorEnum.USER_NOT_HAVE_ANY_AUTHORITY);
        List<Integer> authorityIds = roleAuthorities
                .stream()
                .map(RoleAuthority::getAuthorityId)
                .collect(toList());
        List<Authority> authorities = authorityMapper.selectByIds(authorityIds);
        // Preconditions.checkArgument(authorities.size() > 0,ApplicationErrorEnum.USER_NOT_HAVE_ANY_AUTHORITY);
        if (authorities == null || authorities.size() < 1) {
            return new HashSet<>();
        }
        return authorities
                .stream()
                .map(Authority::getName)
                .collect(toSet());
    }

    public int update(SUser sUser) {
        Integer userId = SecurityUtils.getCurrentUserId();
        sUser.setLastModifiedBy(userId);
        sUser.setLastModifiedTime(new Date());
        return sUserMapper.updateByPrimaryKeySelective(sUser);
    }

    public List<Integer> getValidRoleIds(List<Integer> roleIds) {
        //查询所有有效的角色
        Role roleCondition = new Role();
        roleCondition.setDeleteFlag(false);
        List<Role> roles = roleMapper.selectByObject(roleCondition);
        Preconditions.checkArgument(roles.size() > 0, ApplicationErrorEnum.USER_NOT_HAVE_ANY_ROLES);

        List<Integer> allVialdRoleIds = roles
                .stream()
                .map(Role::getId)
                .collect(toList());

        return roleIds
                .stream()
                .filter(value -> allVialdRoleIds.contains(value))
                .collect(toList());
    }

    private SUser getCurrentUserByUserId(Integer userId) {
        SUser sUser = sUserMapper.selectByPrimaryKey(userId);
        Preconditions.checkNotNull(sUser, ApplicationErrorEnum.USER_NOT_FOUND);
        return sUser;
    }


    /**
     * 返回形式为数字跟字符串
     */
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
        return strDigits[iD1] + strDigits[iD2];
    }

    /**
     * 返回形式只为数字
     */
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }

    /**
     * 转换字节数组为16进制字串
     */
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    public static String GetMD5Code(String strObj) {
        String resultString = null;
        try {
            resultString = new String(strObj);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(strObj.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

    /**
     * 停用与启用
     */
    public Result enabledAccount(EnableAccountDTO enableAccountDTO) {
        if (enableAccountDTO != null && enableAccountDTO.getAccountIds() != null && enableAccountDTO.getAccountIds().size() > 0) {
            if (enableAccountDTO.getOperation().equals(1)) {
                sUserMapper.enabledUsers(enableAccountDTO.getAccountIds());
            } else {
                sUserMapper.disabledUsers(enableAccountDTO.getAccountIds());
            }
        }
        return new Result();
    }

    /**
     * 获取不在指定部门的用户
     * 1.用户在该分公司下没有分配所属部门
     * 2.用户没有在该分公司
     */
    public Result getNoSelectUser(Integer departmentId, UserSearchDTO userSearchDTO) {
        Depart department = departMapper.getDepartmentById(departmentId);
        if (department == null || !"2".equals(department.getOrgType()) || department.getParentdepartid() == null) {
            return new Result("请选择一个部门");
        }
        Depart company = departMapper.getDepartmentById(department.getParentdepartid());
        if (company == null) {
            return new Result("请选择一个部门");
        }
        List<DepartUser> companyUsers = departMapper.allFromCompany(company.getId());

        Set<Integer> departUserIds = companyUsers.stream().map(DepartUser::getUserId).collect(toSet());

        List<SUser> otherUsers = sUserMapper.userNoInThisList(companyUsers.stream().map(DepartUser::getUserId).collect(toList()));

        //在公司下有所属部门的用户id
        List<Integer> integers = departMapper.hasBelongDepartmentUSerId(company.getId());

        departUserIds.forEach(cu -> {
            if (!integers.contains(cu)) {
                SUser user = sUserMapper.selectByPrimaryKey(cu);
                if (user != null) {
                    otherUsers.add(user);
                }

            }
        });
        List<Integer> otherUserIds = otherUsers.stream().map(SUser::getId).collect(toList());
        if (otherUserIds == null || otherUserIds.size() < 1) {
            return new Result(new ArrayList<>());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("idList", otherUserIds);
        if (userSearchDTO != null && !StringUtils.isEmpty(userSearchDTO.getSearchKey())) {
            map.put("searchKey", userSearchDTO.getSearchKey());
        }

        List<SUser> userByMap = sUserMapper.getUserByMap(map);

        return new Result(userByMap);
    }

    /**
     * 添加用户到指定组织
     */
    public Result addUsersToDepartment(UsersToDepartmentDTO usersToDepartmentDTO) {
        Depart department = departMapper.getDepartmentById(usersToDepartmentDTO.getDepartmentId());
        List<DepartUser> departUsers = usersToDepartmentDTO.getUserIdList().stream().filter(x -> {
            SUser sUser = sUserMapper.selectByPrimaryKey(x);
            return sUser != null;
        }).map(userId -> {
            DepartUser departUser = new DepartUser();
            departUser.setDepartId(usersToDepartmentDTO.getDepartmentId());
            departUser.setUserId(userId);
            departUser.setRelationType(2);
            departUser.setCompanyId(department.getParentdepartid());
            departUser.setSelectAll(0);
            return departUser;
        }).collect(toList());

        departMapper.insertUserDepartmentList(departUsers);
        return new Result();
    }


    public Result removeUsersToDepartment(UsersToDepartmentDTO usersToDepartmentDTO) {
        Depart department = departMapper.getDepartmentById(usersToDepartmentDTO.getDepartmentId());
        if (department == null || department.getParentdepartid() == null) {
            return new Result(500, "部门不存在");
        }

        //移除部门和分公司
        usersToDepartmentDTO.getUserIdList().forEach(userId -> {
            departMapper.deleteUserFromDepartmentAndCompanyId(userId, usersToDepartmentDTO.getDepartmentId(), department.getParentdepartid());
        });
        return new Result();
    }

    /**
     * 获取用户在指定公司下的信息
     */
    public Result getUserInfoInCompany(Integer userId, Integer companyId) {

        Integer currentCompanyId = companyId;
        //指定公司下角色
        List<UserRole> userRoles = userRoleMapper.getCompanyUserRole(userId, companyId);
        List<Integer> roleIds = userRoles.stream().map(UserRole::getRoleId).collect(toList());

        //所属部门
        DepartUser departUser = departMapper.selectByUserBelongDepartment(userId, companyId);
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setRoleList(roleIds);
        if (departUser != null) {
            userDTO.setDepartmentId(departUser.getDepartId());
            userDTO.setSelectAll(departUser.getSelectAll());
        }
        UserCompanySettings userCompanySettings = userCompanySettingsMapper.getByUserIdAndCompanyId(userId, companyId);
        if (userCompanySettings != null) {
            List<String> userDutyList = new ArrayList<>();
            if (userCompanySettings.getIsBusiness().equals(1)) {
                userDutyList.add(UserCompanyDutyEnum.BUSINESS.getCode());
            }
            if (userCompanySettings.getIsDocument().equals(1)) {
                userDutyList.add(UserCompanyDutyEnum.DOCUMENT.getCode());
            }
            if (userCompanySettings.getIsSales().equals(1)) {
                userDutyList.add(UserCompanyDutyEnum.SALES.getCode());
            }
            if (userCompanySettings.getIsOperation().equals(1)) {
                userDutyList.add(UserCompanyDutyEnum.OPERATION.getCode());
            }
            if (userCompanySettings.getIsService().equals(1)) {
                userDutyList.add(UserCompanyDutyEnum.SERVICE.getCode());
            }
            userDTO.setDutyList(userDutyList);
        }
        return new Result(userDTO);
    }

    public Result removeUserFromCompany(Integer userId, Integer companyId) {
        departMapper.deletUserCompanyDepart(userId, companyId);
        return new Result();
    }

    /**
     * 用户在各部门信息. 用户管辖部门情况
     * 不包括用户在该公司下的所属部门
     */
    public Result getUserCompanyDepartment(Integer userId, Integer companyId) {

        //公司下所有部门
        List<Depart> departs = departMapper.selectDepartByParentId(companyId);
        //已选部门
        List<DepartUser> departUsers = departMapper.selectByUserIdAndCompanyId(userId, companyId);
        //非所属部门id，
        List<Integer> otherDepart = departUsers.stream().filter(departUser -> departUser.getRelationType() == 1).map(DepartUser::getDepartId).collect(toList());
        //所属部门id
        List<Integer> belongIds = departUsers.stream()
                .filter(departUser -> departUser.getRelationType() == 2).map(DepartUser::getDepartId).collect(toList());
        List<Integer> selectAllIds = departUsers.stream()
                .filter(departUser -> departUser.getSelectAll() == 1).map(DepartUser::getDepartId).collect(toList());

        List<DepartmentDTO> collect = departs.stream().filter(x -> !belongIds.contains(x.getId())).map(d -> {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(d.getId());
            departmentDTO.setDepartname(d.getDepartname());
            if (otherDepart.contains(d.getId())) {
                departmentDTO.setChecked(true);
            }
            if (selectAllIds.contains(d.getId())) {
                departmentDTO.setSelectAll(true);
            } else {
                departmentDTO.setSelectAll(false);
            }
            return departmentDTO;
        }).collect(toList());
        return new Result(collect);
    }

    /**
     * 设置用户在分公司下的管辖部门
     */
    public Result setUserDepartment(List<DepartUser> departUsers, Integer userId, Integer companyId) {
        //删除所有管辖部门
        departMapper.deleteOtherDepartment(userId, companyId);
        if (departUsers != null && departUsers.size() > 0) {
            departUsers.forEach(departUser -> {
                departUser.setRelationType(1);
                departUser.setCompanyId(companyId);
                departUser.setUserId(userId);

            });
            departMapper.insertUserDepartmentList(departUsers);
        }

        return new Result();
    }

    /**
     * 获取公司下所有的用户
     */
    public Result getCompanyUsers(Integer companyId) {
        List<SUser> companyUsers = sUserMapper.getCompanyUsers(companyId);
        return new Result(companyUsers);
    }

    /**
     * 系统下所有用户
     */
    public Result getAllUsers() {
        List<SUser> companyUsers = sUserMapper.getAllUsers();
        return new Result(companyUsers);
    }

    /**
     * 根据职务类型获取用户列表
     *
     * @param dutyType
     * @return
     */
    public Result getUsersByDuty(String dutyType) {
        List<String> dutyTypeList = Arrays.asList(
                UserCompanyDutyEnum.BUSINESS.getCode(), UserCompanyDutyEnum.SALES.getCode(),
                UserCompanyDutyEnum.OPERATION.getCode(), UserCompanyDutyEnum.SERVICE.getCode(),
                UserCompanyDutyEnum.DOCUMENT.getCode()
        );
        if (StringUtils.isEmpty(dutyType) || !dutyTypeList.contains(dutyType)) {
            return new Result(400, "类型不存在");
        }
        Integer companyId = SecurityUtils.getCurrentCompanyId();
        List<UserCompanySettings> userCompanySettings = userCompanySettingsMapper.getByCompanyIdAndType(companyId, dutyType);
        List<Integer> userIds = userCompanySettings.stream().map(UserCompanySettings::getUserId).collect(toList());
        if (userIds == null || userIds.size() < 1) {
            return new Result(new ArrayList<SUser>());
        }
        List<SUser> userList = sUserMapper.getUsersByIds(userIds);
        return new Result(userList);
    }


    public Result getUsersPagesByDuty(UserGridPageRequest userGridPageRequest) {
        List<String> dutyTypeList = Arrays.asList(
                UserCompanyDutyEnum.BUSINESS.getCode(), UserCompanyDutyEnum.SALES.getCode(),
                UserCompanyDutyEnum.OPERATION.getCode(), UserCompanyDutyEnum.SERVICE.getCode(),
                UserCompanyDutyEnum.DOCUMENT.getCode()
        );
        if (StringUtils.isEmpty(userGridPageRequest.getDutyType()) || !dutyTypeList.contains(userGridPageRequest.getDutyType())) {
            return new Result(400, "类型不存在");
        }
        Integer companyId = SecurityUtils.getCurrentCompanyId();
        List<UserCompanySettings> userCompanySettings = userCompanySettingsMapper.getByCompanyIdAndType(companyId, userGridPageRequest.getDutyType());
        List<Integer> userIds = userCompanySettings.stream().map(UserCompanySettings::getUserId).collect(toList());
        if (userIds == null || userIds.size() < 1) {
            return new Result(new ArrayList<SUser>());
        }
        GridReturnData<SUser> mGridReturnData = new GridReturnData<>();
        String sortMyBatisByString = userGridPageRequest.getSortMybatisString();
        PageHelper.startPage(userGridPageRequest.getPageNum(), userGridPageRequest.getPageSize(), sortMyBatisByString);

        List<SUser> userList = sUserMapper.getUsersByIds(userIds);
        PageInfo<SUser> pageInfo = new PageInfo<SUser>(userList);
        mGridReturnData.setPageInfo(pageInfo);
        return new Result(mGridReturnData);
    }

    /**
     * 修改个人密码
     */
    public Result modifyMyPassword(ModifyPassword modifyPassword) {
        Integer userId = SecurityUtils.getCurrentUserId();
        SUser sUser = sUserMapper.selectByPrimaryKey(userId);
        if (sUser == null) {
            return new Result(500, "用户不存在");
        }
        if (StringUtils.isEmpty(modifyPassword.getOldPassword()) || StringUtils.isEmpty(modifyPassword.getNewPassword())) {
            return new Result(500, "密码不能为空");
        }
        if (modifyPassword.getOldPassword().equals(sUser.getPassword())) {
            sUserMapper.updateUserPassword(userId, modifyPassword.getNewPassword());
        } else {
            return new Result(500, "原密码错误");
        }
        return new Result();
    }

    /**
     * 修改个人信息
     */
    public Result modifyMyInfo(SUser sUser) {
        Integer userId = SecurityUtils.getCurrentUserId();
        SUser oldUser = sUserMapper.selectByPrimaryKey(userId);
        if (oldUser == null) {
            return new Result(500, "用户不存在");
        }
        SUser modifyUser = new SUser();
        if (StringUtils.isEmpty(sUser.getRealName()) || StringUtils.isEmpty(sUser.getEmail()) || StringUtils.isEmpty(sUser.getMobile())) {
            return new Result(500, "参数错误");
        }

        modifyUser.setId(userId);
        modifyUser.setRealName(sUser.getRealName());
        modifyUser.setEmail(sUser.getEmail());
        modifyUser.setMobile(sUser.getMobile());
        sUserMapper.updateByPrimaryKeySelective(modifyUser);

        return new Result();
    }
}
