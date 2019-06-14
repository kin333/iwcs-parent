package com.wisdom.controller.system;

import com.google.common.collect.Maps;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.Role;
import com.wisdom.iwcs.domain.system.dto.CompanyRoleDTO;
import com.wisdom.iwcs.domain.system.dto.RoleDTO;
import com.wisdom.service.system.RoleService;
import com.wisdom.service.system.SUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private SUserService userService;

    /**
     * 删除
     *
     * @param ids
     * @param response
     * @return
     */
    @DeleteMapping(value = "/roles/")
    public Result deleteByPrimaryKey(@RequestBody List<Integer> ids, HttpServletResponse response) {
        int num = roleService.removeLogicalById(ids);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    /**
     * 根据Role对象添加
     *
     * @param role
     * @param response
     * @return
     */
    @PostMapping(path = "/roles/")
    public Result insert(@RequestBody Role role, HttpServletResponse response) {
        int num = roleService.add(role);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }


    /**
     * 通过id选择数据
     *
     * @param id
     * @param response
     * @return
     */
    @GetMapping(value = "/roles/{id}/")
    public Result selectByPrimaryKey(@PathVariable Integer id, HttpServletResponse response) {
        Role roleCondition = new Role();
        roleCondition.setId(id);
        Role role = roleService.getOne(roleCondition);
        Preconditions.checkNotNull(role, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);
        Map<String, Role> data = Maps.newHashMapWithExpectedSize(1);
        data.put("role", role);
        return new Result(data);
    }

    /**
     * 通过id 更新数据库
     *
     * @return
     */
    @PutMapping(value = "/roles/{id}")
    public Result updateByPrimaryKey(@PathVariable Integer id, @RequestBody Role role) {
        Preconditions.checkNotNull(id, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        role.setId(id);
        int num = roleService.update(role);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    @PutMapping(value = "/roles/{id}/available/")
    public Result available(@PathVariable Integer id) {
        Preconditions.checkNotNull(id, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        Role role = new Role();
        role.setId(id);
        role.setDeleteFlag(false);
        int num = roleService.update(role);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    @PutMapping(value = "/roles/{id}/unavailable/")
    public Result unavailable(@PathVariable Integer id) {
        Preconditions.checkNotNull(id, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        Role role = new Role();
        role.setId(id);
        role.setDeleteFlag(true);
        int num = roleService.update(role);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    @PostMapping(value = "/roles/", params = {"pageNum", "pageSize"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public Result getRoleGridList(HttpServletRequest request, @RequestBody RoleDTO roleDTO, @RequestParam Integer pageNum, @RequestParam Integer pageSize) {

        GridReturnData data = roleService.list(roleDTO, pageNum, pageSize);
        return new Result(data);
    }

    @GetMapping(value = "/allRoles", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result getAllRoles() {
        return roleService.getAllRoles();
    }

//    @GetMapping(value = "/roles/",params = {"all"})
//    public Result getAllRoleWithNoPage(){
//        Role role = new Role();
//        role.setDeleteFlag(false);
//        List<Role> roles =  roleService.list(role);
//        Map<String,Object> data = Maps.newHashMapWithExpectedSize(1);
//        data.put("roles",roles);
//        data.put("roleSelectIds",getSelectRoleIdsByCurrentUser());
//        return new Result(data);
//    }
//
//    private List<Integer> getSelectRoleIdsByCurrentUser(){
//        Integer userId = SecurityUtils.getCurrentUserId();
//        SUser user = userService.getOneByUserId(userId);
//        String roleIdsStr = user.getRoleIds();
//        if(Strings.isNullOrEmpty(roleIdsStr)){
//            return new ArrayList<>(1);
//        }
//        return Splitter
//                .on(",")
//                .trimResults()
//                .omitEmptyStrings()
//                .splitToList(roleIdsStr)
//                .stream()
//                .map(s -> Integer.valueOf(s))
//                .collect(Collectors.toList());
//    }

    /**
     * 用户指定公司下的角色
     **/
    @PostMapping("/company/role")
    public Result getCompanyRole(@RequestBody CompanyRoleDTO companyRoleDTO) {
        return roleService.getCompanyRole(companyRoleDTO);
    }
}
