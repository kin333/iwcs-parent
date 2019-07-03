package com.wisdom.controller.system;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.DepartUser;
import com.wisdom.iwcs.domain.system.SUser;
import com.wisdom.iwcs.domain.system.dto.*;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.system.SUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum.COMMON_FAIL;
import static com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS;

/**
 * Created by lenovo on 17-8-16.
 */
@RestController
@RequestMapping("/api/sUser")
public class SUserController {

    @Autowired
    private SUserService userService;

    @GetMapping(value = "/currentUserInfo")
    private Result getCurrentUserInfo() {
        return userService.getCurrentUserInfo();
    }

    /**
     * 单条数据删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        return userService.deleteByPrimaryKey(id);
    }

    /**
     * 创建账号
     *
     * @param record
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result insertSelective(@RequestBody UserDTO record) {
        return userService.insertSelective(record);
    }

    /**
     * 按主键查找
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result selectByPrimaryKey(@PathVariable Integer id) {
        return userService.selectByPrimaryKey(id);
    }

    /**
     * 修改账号
     *
     * @param record
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result updateByPrimaryKeySelective(@RequestBody UserDTO record) {
        return userService.updateByPrimaryKeySelective(record);
    }

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result selectAll(HttpServletRequest request, @RequestBody UserGridPageRequest gridPageRequest) {

        Result result = userService.selectByPage(gridPageRequest);
        return result;
    }

    @RequestMapping(value = "/deleteByIds", method = RequestMethod.POST)
    public Result deleteByIds(@RequestBody List<Integer> ids) {
        return userService.deleteByIds(ids);
    }


    @PutMapping(value = "/{id}/available/")
    public Result available(@PathVariable Integer id) {
        Preconditions.checkNotNull(id, COMMON_REQUEST_PARAMETER_LESS);
        SUser sUser = new SUser();
        sUser.setId(id);
        sUser.setDeleteFlag(0);
        int num = userService.update(sUser);
        Preconditions.checkArgument(num > 0, COMMON_FAIL);
        return new Result();
    }

    @PutMapping(value = "/{id}/unavailable/")
    public Result unavailable(@PathVariable Integer id) {
        Preconditions.checkNotNull(id, COMMON_REQUEST_PARAMETER_LESS);
        SUser sUser = new SUser();
        sUser.setId(id);
        sUser.setDeleteFlag(1);
        int num = userService.update(sUser);
        Preconditions.checkArgument(num > 0, COMMON_FAIL);
        return new Result();
    }

    @PostMapping(value = "/enable")
    public Result enabledAccount(@RequestBody EnableAccountDTO enableAccountDTO) {
        return userService.enabledAccount(enableAccountDTO);
    }

    /**
     * 获取不在指定部门的用户
     */
    @PostMapping(value = "/noSelected/{departmentId}")
    public Result getNoSelectUser(@PathVariable Integer departmentId, @RequestBody UserSearchDTO searchDTO) {
        return userService.getNoSelectUser(departmentId, searchDTO);
    }

    @PostMapping(value = "/moveToDepartment")
    public Result addUsersToDepartment(@RequestBody UsersToDepartmentDTO usersToDepartmentDTO) {
        return userService.addUsersToDepartment(usersToDepartmentDTO);
    }

    @PostMapping(value = "/removeToDepartment")
    public Result removeUsersToDepartment(@RequestBody UsersToDepartmentDTO usersToDepartmentDTO) {
        return userService.removeUsersToDepartment(usersToDepartmentDTO);
    }

    @GetMapping(value = "/belongInfo/{userId}")
    public Result getUserInfoInCompany(@PathVariable Integer userId) {
        return userService.getUserInfoInCompany(userId);
    }

    @GetMapping(value = "/removeUserFromCompany/{userId}/{companyId}")
    public Result removeUserFromCompany(@PathVariable Integer userId, @PathVariable Integer companyId) {
        return userService.removeUserFromCompany(userId, companyId);
    }

    @GetMapping(value = "/userCompanyDepartment/{userId}/{companyId}")
    public Result getUserCompanyDepartment(@PathVariable Integer userId, @PathVariable Integer companyId) {
        return userService.getUserCompanyDepartment(userId, companyId);
    }

    @PutMapping(value = "/userCompanyDepartment/{userId}/{companyId}")
    public Result setUserDepartment(@RequestBody List<DepartUser> departUsers, @PathVariable Integer userId, @PathVariable Integer companyId) {

        return userService.setUserDepartment(departUsers, userId, companyId);
    }

    @GetMapping(value = "/companyUsers/{companyId}")
    public Result getCompanyUsers(@PathVariable Integer companyId) {
        return userService.getCompanyUsers(companyId);
    }

    @GetMapping(value = "/currentCompany/users/all")
    public Result getCurrentCompanyUsers() {
        return userService.getCompanyUsers(SecurityUtils.getCurrentCompanyId());
    }

    /**
     * tmp
     * 获取所有员工
     */
    @GetMapping(value = "/all")
    public Result getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * 根据职务获取当前公司用户
     */
    @GetMapping(value = "/duty/{dutyType}")
    public Result getUsersByDuty(@PathVariable String dutyType) {

        return userService.getUsersByDuty(dutyType);
    }

    /**
     * 根据职务获取当前公司用户(分页)
     */
    @PostMapping(value = "/duty/pages")
    public Result getUsersPagesByDuty(@RequestBody UserGridPageRequest userGridPageRequest) {

        return userService.getUsersPagesByDuty(userGridPageRequest);
    }

    /**
     * 修改个人密码
     */
    @PutMapping(value = "/password/my")
    public Result modifyMyPassword(@RequestBody ModifyPassword modifyPassword) {

        return userService.modifyMyPassword(modifyPassword);
    }

    @PutMapping(value = "/person_info/my")
    public Result modifyMyInfo(@RequestBody SUser sUser) {

        return userService.modifyMyInfo(sUser);
    }
    @PutMapping(value = "/reset_password")
    public Result resetPassword(@RequestBody List<Integer> userIdList) {
        return userService.resetUsersPassword(userIdList);
    }
}
