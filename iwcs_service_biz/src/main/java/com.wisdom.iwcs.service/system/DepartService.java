package com.wisdom.iwcs.service.system;

import com.wisdom.iwcs.common.utils.OrganizationTypeEnum;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.codec.BusinessCode;
import com.wisdom.iwcs.domain.system.Depart;
import com.wisdom.iwcs.domain.system.DepartRole;
import com.wisdom.iwcs.domain.system.DepartUser;
import com.wisdom.iwcs.domain.system.dto.DepartUserDto;
import com.wisdom.iwcs.domain.system.dto.MenuTreeDto;
import com.wisdom.iwcs.mapper.codec.BusinessCodeMapper;
import com.wisdom.iwcs.mapper.system.AuthorityMapper;
import com.wisdom.iwcs.mapper.system.DepartMapper;
import com.wisdom.iwcs.service.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lin on 17-9-14.
 */
@Service
public class DepartService {

    private Logger logger = LoggerFactory.getLogger(AuthorityService.class);
    @Autowired
    private AuthorityMapper authorityMapper;
    @Autowired
    private DepartMapper departMapper;
    @Autowired
    BusinessCodeMapper businessCodeMapper;


    /**
     * 添加一个组织
     *
     * @param depart 组织
     * @return int
     */
    public int add(Depart depart) {
        if (depart.getOrgCode() == null || depart.getOrgCode().length() != 4) {
            throw new BusinessException("编码不能为空且长度必须为4位");
        }
        List<Depart> departmentByCode = departMapper.getDepartmentByCode(depart.getOrgCode());
        if (departmentByCode != null && departmentByCode.size() > 0) {
            throw new BusinessException("编码不能重复");
        }
        if (OrganizationTypeEnum.COMPANY.getCode().equals(depart.getOrgType())) {
            checkCompanyExist(depart);
        } else {
            checkDepartmentExist(depart);
        }
        return departMapper.insertSelective(depart);
    }

    /**
     * 验证公司是否存在
     *
     * @param depart 组织
     */
    private void checkCompanyExist(Depart depart) {
        List<Depart> companyByName = departMapper.getCompanyByName(depart.getDepartname());
        if (depart.getId() == null) {
            if (companyByName != null && companyByName.size() > 0) {
                throw new BusinessException("重复的公司名称");
            }
        } else {
            if (companyByName != null && companyByName.size() > 0) {
                companyByName.forEach(d -> {
                    if (!d.getId().equals(depart.getId())) {
                        throw new BusinessException("重复的公司名称");
                    }
                });
            }
        }
    }

    /**
     * 验证部门是否存在
     *
     * @param depart 部门
     */
    private void checkDepartmentExist(Depart depart) {
        List<Depart> departmentByName = departMapper.getDepartmentByNameAndParent(depart.getDepartname(), depart.getParentdepartid());
        if (depart.getId() == null) {

            if (departmentByName != null && departmentByName.size() > 0) {
                throw new BusinessException("重复的部门名称");
            }
        } else {
            if (departmentByName != null && departmentByName.size() > 0) {
                departmentByName.forEach(d -> {
                    if (!d.getId().equals(depart.getId())) {
                        throw new BusinessException("重复的部门名称");
                    }
                });
            }
        }
    }

    /**
     * 更新组织
     *
     * @param depart 组织
     * @return int
     */
    public int updateDepart(Depart depart) {
        if (depart.getOrgCode() == null || depart.getOrgCode().length() != 4) {
            throw new BusinessException("编码不能为空且长度必须为4位");
        }
        List<Depart> departmentByCode = departMapper.getDepartmentByCode(depart.getOrgCode());
        if (departmentByCode != null && departmentByCode.size() > 0) {
            if (!departmentByCode.get(0).getId().equals(depart.getId())) {
                throw new BusinessException("编码不能重复");
            }
        }
        if (OrganizationTypeEnum.COMPANY.getCode().equals(depart.getOrgType())) {
            checkCompanyExist(depart);
        } else {
            checkDepartmentExist(depart);
        }
        return departMapper.updateByPrimaryKeySelective(depart);
    }

    /**
     * 删除部门
     * 有用户分配到该部门（或分公司），不能删除
     *
     * @param id 组织id
     * @return int
     */
    public int deleteDepart(Integer id) {
        Depart depart = departMapper.getDepartmentById(id);
        if (depart == null) {
            throw new BusinessException("部门或分公司不存在");
        }
        if (OrganizationTypeEnum.COMPANY.getCode().equals(depart.getOrgType())) {
            List<DepartUser> departUsers = departMapper.allFromCompany(id);
            if (departUsers != null && departUsers.size() > 0) {
                throw new BusinessException("分公司下有用户,不能删除");
            }
        }
        List<DepartUser> allDepartUserByDepartmentId = departMapper.getAllDepartUserByDepartmentId(id);
        if (allDepartUserByDepartmentId != null && allDepartUserByDepartmentId.size() > 0) {
            throw new BusinessException("部门下有用户,不能删除");
        }
        return departMapper.deleteById(id);
    }

    /**
     * 通过id获取组织信息
     *
     * @param id 组织id
     * @return 组织信息
     */
    public Depart getAllDepartById(Integer id) {
        return departMapper.selectAllDepartById(id);
    }

    /**
     * 向部门添加用户
     *
     * @param departId 部门id
     * @param ids      用户idList
     * @return 数量
     */
    public int addDepartUsers(Integer departId, List<Integer> ids) {
        int result = 0;
        for (Integer id : ids) {
            DepartUser departUser = new DepartUser();
            departUser.setDepartId(departId);
            departUser.setUserId(id);
            result = departMapper.addDepartUser(departUser) + result;
        }
        return result;
    }

    /**
     * 从部门删除用户
     *
     * @param departId 部门id
     * @param ids      用户idList
     * @return 数量
     */
    public int deleteDepartUsers(Integer departId, List<Integer> ids) {
        int result = 0;
        for (Integer id : ids) {
            DepartUser departUser = new DepartUser();
            departUser.setDepartId(departId);
            departUser.setUserId(id);
            result = departMapper.deleteDepartUser(departUser) + result;
        }
        return result;
    }

    /**
     * 获取指定组织下的用户
     * 查询哪些用户的所属部门是该部门
     *
     * @param departId 部门id
     * @return DepartUserDto List
     */
    public List<DepartUserDto> getUserById(Integer departId) {
        //查询哪些用户的所属部门是该部门
        List<Integer> userIdFromThisDepartment = departMapper.getUserIdFromThisDepartment(departId);
        if (userIdFromThisDepartment == null || userIdFromThisDepartment.size() < 1) {
            return new ArrayList<>();
        }
        List<DepartUserDto> userFromThisDepartment = departMapper.getUserFromThisDepartment(departId);
        return userFromThisDepartment;
    }

    /**
     * 获取部门角色(作废)
     */
    @Deprecated
    public List<Integer> getRoleById(Integer departId) {

        List<Integer> departRoleList = new ArrayList<Integer>();
        departRoleList = departMapper.getRoleById(departId);

        List<Integer> departRoleListWithoutDup = new ArrayList<Integer>();
        if (null != departRoleList && departRoleList.size() != 0) {
            departRoleListWithoutDup = new ArrayList<Integer>(new HashSet<Integer>(departRoleList));
        }

        return departRoleListWithoutDup;
    }

    /**
     * (作废)
     * Created by Gethiun
     * 这是 根据 组织结构 id和 角色ids 修改 该组织 角色 的 接口
     **/
    @Deprecated
    public int updateRole(Integer departId, List<Integer> ids) {
//        删除该部门的角色
        departMapper.deleteRoleByDepartId(departId);
//        添加角色 ，很据ids
        for (Integer id : ids) {
            System.out.println(id);
            DepartRole departRole = new DepartRole();
            departRole.setDepartId(departId);
            departRole.setRoleId(id);
            departMapper.addRoleToDeport(departRole);
        }
        return 1;
    }

    /**
     * 当前用户获取集团组织架构 (树型)
     * 系统管理员:获取整个集团信息
     * 普通用户:当前登录分公司
     */
    public List<MenuTreeDto> tree() {

        List<MenuTreeDto> departTree = new ArrayList<>();
        //当前登录用户id
        Integer currentLoginCompanyId = SecurityUtils.getCurrentCompanyId();
        Depart currentCompany = departMapper.selectOneById(currentLoginCompanyId);

        //是否为总公司,查看所有
        if (SecurityUtils.isSuperAdmin()) {
            List<Depart> allCompany = departMapper.getAllCompany();
            departTree = allCompany.stream().map(company -> {
                List<Depart> departs = departMapper.selectDepartByParentId(company.getId());
                List<MenuTreeDto> childMenu = departs.stream().map(this::getMenuTreeDto).collect(Collectors.toList());
                MenuTreeDto companyMenu = getMenuTreeDto(company);
                companyMenu.setChildren(childMenu);
                return companyMenu;
            }).collect(Collectors.toList());
        } else {
            //子公司登录下，仅获取本公司下组织结构
            List<Depart> departs = departMapper.selectDepartByParentId(currentLoginCompanyId);
            List<MenuTreeDto> orgMenuList = departs.stream().map(this::getMenuTreeDto).collect(Collectors.toList());
            //加入当前登录分公司
            MenuTreeDto companyMenu = getMenuTreeDto(currentCompany);
            companyMenu.setChildren(orgMenuList);
            departTree.add(companyMenu);
        }

        return departTree;
    }

    /**
     * 把部门转成树 需要的结构
     *
     * @param d 部门
     * @return menuTreeDto
     */
    private MenuTreeDto getMenuTreeDto(Depart d) {
        MenuTreeDto mtd = new MenuTreeDto();
        mtd.setId(d.getId());
        mtd.setLabel(d.getDepartname());
        mtd.setTitle(d.getDepartname());
        mtd.setDepart(d);
        return mtd;
    }


    /**
     * 获取所有分公司
     *
     * @return Result(分公司List);
     */
    public Result getAllCompany() {
        return new Result(departMapper.getAllCompanys());
    }

    /**
     * 登录时,获取分公司列表
     *
     * @return Result(分公司List);
     */
    public Result getAllUsingCompanyForLogin() {
        return new Result(departMapper.getAllUsingCompanyForLogin());
    }

    /**
     * 获取一个公司下所有的部门
     */
    public Result getCompanyDepartment(Integer companyId) {
        List<Depart> departList = departMapper.selectDepartByParentId(companyId);
        return new Result(departList);
    }

    /**
     * 获取用互在指定公司下的部门关系(管辖+所属)
     *
     * @param companyId 公司
     * @param userId    用户
     * @return 关系List
     */
    public Result getUserCompanyDepartment(Integer companyId, Integer userId) {
        List<DepartUser> departUsers = departMapper.selectByUserIdAndCompanyId(userId, companyId);
        return new Result(departUsers);
    }


    /**
     * 获取默认港口
     */
    public Result getDefaultPortCode() {
        Integer currentLoginCompanyId = SecurityUtils.getCurrentCompanyId();
        String defaultPortCode = departMapper.getDefaultPortCodeById(currentLoginCompanyId);
        if (StringUtils.isEmpty(defaultPortCode)) {
            return new Result(400, "暂无默认港口");
        }
        BusinessCode defaultPort = businessCodeMapper.getByCodeAndType(defaultPortCode, "PORTCD");
        return new Result(defaultPort);
    }

    /**
     * 获取默认港口代码
     */
    public Result getDefaultAirPortCode() {
        Integer currentLoginCompanyId = SecurityUtils.getCurrentCompanyId();
        String defaultPortCode = departMapper.getDefaultAirPortCodeById(currentLoginCompanyId);
        if (StringUtils.isEmpty(defaultPortCode)) {
            return new Result(400, "暂无默认港口");
        }
        return new Result(defaultPortCode);
    }

    /**
     * 当前登录用户的部门（所属+管辖）
     *
     * @return 部门列表
     */
    public List<Depart> getCurrentUserDepartment() {
        Integer userId = SecurityUtils.getCurrentUserId();
        Integer companyId = SecurityUtils.getCurrentCompanyId();
        List<Depart> departs = departMapper.getUserCompanyDepartments(userId, companyId);
        return departs;
    }

    /**
     * 根据Id获取部门
     *
     * @param departmentId 部门 Id
     * @return Depart
     */
    public Depart getDepartById(Integer departmentId) {
        return departMapper.getDepartmentById(departmentId);
    }

    /**
     * 根据用户Id获取部门(所属部门)
     *
     * @param userId 用户 Id
     * @return Depart
     */
    public Result getDepartByUserId(Integer userId) {
        Integer companyId = SecurityUtils.getCurrentCompanyId();
        DepartUser departUser = departMapper.selectByUserBelongDepartment(userId, companyId);
        if (departUser == null) {
            throw new BusinessException("用户没有所属部门，请检查");
        }
        Integer departmentId = departUser.getDepartId();
        return new Result(departMapper.getDepartmentById(departmentId));
    }

    public Depart selectByPrimaryKey(Integer id) {
        return departMapper.selectByPrimaryKey(id);
    }
}
