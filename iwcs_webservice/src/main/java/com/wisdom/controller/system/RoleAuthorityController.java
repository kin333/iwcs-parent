package com.wisdom.controller.system;

import com.google.common.base.Joiner;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.RoleAuthority;
import com.wisdom.iwcs.domain.system.dto.DataFilterRuleDto;
import com.wisdom.iwcs.domain.system.dto.RoleAuthDTO;
import com.wisdom.iwcs.service.system.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by devin on 17-1-13.
 */
@RestController
public class RoleAuthorityController {

    @Autowired
    private RoleAuthorityService roleAuthorityService;

    @PostMapping(value = "/role-authorities/")
    public Result add(@RequestBody RoleAuthority roleAuthorities) {
        int num = roleAuthorityService.add(roleAuthorities);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    @DeleteMapping(value = "/role-authorities/role/{roleId}/menu/{menuId}/")
    public Result deleteAuthority(@PathVariable Integer roleId, @PathVariable Integer menuId, HttpServletRequest request) {
        Preconditions.checkNotNull(roleId, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        Preconditions.checkNotNull(menuId, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        RoleAuthority roleAuthority = new RoleAuthority();
        roleAuthority.setRoleid(roleId);
        roleAuthority.setAuthorityId(menuId);
        int num = roleAuthorityService.removePhysicalByObject(roleAuthority);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    @DeleteMapping(value = "/role-authorities/role/{roleId}/authorityId/{authorityId}")
    public Result deleteRoleAuthority(@PathVariable Integer roleId, @PathVariable Integer authorityId, HttpServletRequest request) {
        Preconditions.checkNotNull(roleId, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        Preconditions.checkNotNull(authorityId, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        RoleAuthority roleAuthority = new RoleAuthority();
        roleAuthority.setRoleid(roleId);
        roleAuthority.setAuthorityId(authorityId);
        int num = roleAuthorityService.removePhysicalByObject(roleAuthority);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    @PutMapping(value = "/role-authorities/role/{roleId}/menu/{menuId}/")
    public Result getAuthorityByRole(@PathVariable Integer roleId, @PathVariable Integer menuId, @RequestBody Integer[] dataRules, HttpServletRequest request) {
        Preconditions.checkNotNull(roleId, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        Preconditions.checkNotNull(menuId, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        Preconditions.checkNotNull(dataRules, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        String dataRuleStr = Joiner.on(",").skipNulls().join(dataRules);
        RoleAuthority roleAuthority = new RoleAuthority();
        roleAuthority.setRoleid(roleId);
        roleAuthority.setAuthorityId(menuId);
        roleAuthority.setDataRule(dataRuleStr);
        int num = roleAuthorityService.updateByObject(roleAuthority);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    @GetMapping(value = "/role-authorities/operation/role/{roleId}/menu/{menuId}")
    public Result getAuthorityOperation(@PathVariable Integer roleId, @PathVariable Integer menuId) {
        Preconditions.checkNotNull(roleId, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        Preconditions.checkNotNull(menuId, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);

//        List<SOperationDto> operationDtoList = roleAuthorityService.getMenuButtons(roleId, menuId);

        return roleAuthorityService.getMenuButtons(roleId, menuId);
    }

    @GetMapping(value = "/role-authorities/data-rule/role/{roleId}/menu/{menuId}")
    public Result getAuthorityDataRule(@PathVariable Integer roleId, @PathVariable Integer menuId) {
        Preconditions.checkNotNull(roleId, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        Preconditions.checkNotNull(menuId, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);

        List<DataFilterRuleDto> dataFilterRuleDtoList = roleAuthorityService.getValidDataRule(roleId, menuId);

        return new Result(dataFilterRuleDtoList);
    }

    @PostMapping(value = "/role-authorities/update")
    public Result updateRoleAuth(@RequestBody RoleAuthDTO roleAuthDTO) {
        return roleAuthorityService.updateRoleAuth(roleAuthDTO);
    }
}
