package com.wisdom.controller.system;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.Depart;
import com.wisdom.iwcs.domain.system.dto.DepartUserDto;
import com.wisdom.iwcs.domain.system.dto.MenuTreeDto;
import com.wisdom.iwcs.service.security.SecurityUtils;
import com.wisdom.iwcs.service.system.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Gethiun on 17-1-13.
 * 这是 组织部门的 controller
 */
@RestController
@RequestMapping("/api/depart")
public class DepartController {

    @Autowired
    private DepartService departService;

    /**
     * Created by Gethiun
     * 这是 获取组织结构的 接口
     **/
    @GetMapping(value = "/getAllDepart")
    public Result getAllDepart() {
        List<MenuTreeDto> departTree = departService.tree();
        return new Result(departTree);
    }


    /**
     * Created by Gethiun
     * 这是 根据部门的Id 获取组织结构的 详细信息 的 接口
     **/
    @GetMapping(value = "/getAllDepartById", params = "departId")
    public Result getAllDepartById(@RequestParam Integer departId) {
        Depart depart = departService.getAllDepartById(departId);
        return new Result(depart);
    }

    /**
     * Created by Gethiun
     * 这是 添加 组织结构的 接口
     **/
    @PostMapping(value = "/addDepart")
    public Result add(@RequestBody Depart depart, HttpServletRequest request) {
        int num = departService.add(depart);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    /**
     * Created by Gethiun
     * 这是 添加 组织结构的 接口
     **/
    @PostMapping(value = "/updateDepart")
    public Result updateDepart(@RequestBody Depart depart, HttpServletRequest request) {
        int num = departService.updateDepart(depart);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    /**
     * Created by Gethiun
     * 这是 删除 组织结构的 接口
     **/
    @DeleteMapping(value = "/deleteDepart/{id}")
    public Result deleteDepart(@PathVariable Integer id, HttpServletRequest request) {
        int num = departService.deleteDepart(id);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    /**
     * Created by Gethiun
     * 这是 根据 组织结构 id 获取 该组织 用户 的 接口
     **/
    @GetMapping(value = "/getUserById", params = "departId")
    public Result getUserById(@RequestParam Integer departId) {
        List<DepartUserDto> departUserList = departService.getUserById(departId);
        return new Result(departUserList);
    }

    /**
     * Created by Gethiun
     * 这是 给 组织结构  添加 成员 的 接口
     **/
    @PutMapping(value = "/addDepartUsers/{departId}")
    public Result addDepartUsers(@PathVariable Integer departId, @RequestBody List<Integer> ids, HttpServletResponse response) {
        int num = departService.addDepartUsers(departId, ids);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    /**
     * Created by Gethiun
     * 这是 给 组织结构  删除 成员 的 接口
     **/
    @DeleteMapping(value = "/deleteDepartUsers/{departId}")
    public Result deleteDepartUsers(@PathVariable Integer departId, @RequestBody List<Integer> ids, HttpServletResponse response) {
        int num = departService.deleteDepartUsers(departId, ids);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }


    /**
     * 获取所有公司
     */
    @GetMapping(value = "/allCompany")
    public Result getAllCompany() {
        return departService.getAllCompany();
    }

    /**
     * 获取所有正常使用的公司列表
     */
    @GetMapping(value = "/getAllUsingCompanyForLogin")
    public Result getAllUsingCompanyForLogin() {
        return departService.getAllUsingCompanyForLogin();
    }

    /**
     * 指定公司下所有部门
     *
     * @param companyId
     * @return
     */
    @GetMapping(value = "/company/department/{companyId}")
    public Result getCompanyDepartment(@PathVariable Integer companyId) {
        return departService.getCompanyDepartment(companyId);
    }

    /**
     * 指定用户指定公司下所选部门
     *
     * @param companyId
     * @return
     */
    @GetMapping(value = "/company/user/department/{userId}/{companyId}")
    public Result getUserCompanyDepartment(@PathVariable Integer userId, @PathVariable Integer companyId) {
        return departService.getUserCompanyDepartment(userId, companyId);
    }

    /**
     * 获取默认港口
     */
    @GetMapping(value = "/defaultPort")
    public Result getDefaultPortCode() {
        return departService.getDefaultPortCode();
    }


    /**
     * 获取默认港口
     */
    @GetMapping(value = "/defaultAirPort")
    public Result getDefaultAirPortCode() {
        return departService.getDefaultAirPortCode();
    }

    @GetMapping(value = "/currentCompany/department/all")
    public Result getCurrentCompanyAllDepartment() {
        return departService.getCompanyDepartment(SecurityUtils.getCurrentCompanyId());
    }


    /**
     * 用户在所有公司下的部门
     */
    @GetMapping(value = "/getCurrentUserDepartment")
    public Result getCurrentUserDepartment() {
        List<Depart> currentUserDepartmentList = departService.getCurrentUserDepartment();
        return new Result(currentUserDepartmentList);
    }

    /**
     * 根据用户Id获取部门
     *
     * @param userId 用户 Id
     * @return Depart
     */
    @GetMapping(value = "/getDepartByUserId/{userId}")
    public Result getDepartByUserId(@PathVariable Integer userId) {
        return departService.getDepartByUserId(userId);
    }

    @GetMapping(value = "/{departmentId}")
    public Result getDepartmentById(@PathVariable Integer departmentId) {
        return new Result(departService.selectByPrimaryKey(departmentId));
    }


}
