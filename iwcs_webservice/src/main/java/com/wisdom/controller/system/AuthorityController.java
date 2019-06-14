package com.wisdom.controller.system;

import com.google.common.collect.Maps;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.common.utils.exception.ApplicationErrorEnum;
import com.wisdom.iwcs.common.utils.exception.Preconditions;
import com.wisdom.iwcs.domain.system.Authority;
import com.wisdom.iwcs.domain.system.RoleAuthority;
import com.wisdom.iwcs.domain.system.dto.AuthorityDto;
import com.wisdom.iwcs.domain.system.dto.DataFilterRuleDto;
import com.wisdom.iwcs.domain.system.dto.MenuTreeDto;
import com.wisdom.service.system.AuthorityService;
import com.wisdom.service.system.RoleAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Created by devin on 17-1-13.
 */
@RestController
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;
    @Autowired
    private RoleAuthorityService roleAuthorityService;

    @PostMapping(value = "/authorities/")
    public Result add(@RequestBody AuthorityDto authorityDto, HttpServletRequest request) {
        int num = authorityService.add(authorityDto);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    @PutMapping(value = "/authorities/{id}/")
    public Result updateAuthority(@PathVariable Integer id, @RequestBody AuthorityDto authorityDto, HttpServletRequest request) {
        Preconditions.checkNotNull(id, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        authorityDto.setId(id);
        int num = authorityService.update(authorityDto);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    @DeleteMapping(value = "/authorities/{id}/{authType}")
    public Result updateAuthority(@PathVariable Integer id, @PathVariable Integer authType, HttpServletRequest request) {
        Preconditions.checkNotNull(id, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        int num = authorityService.deleteByAuthorityId(id, authType);
        Preconditions.checkArgument(num > 0, ApplicationErrorEnum.COMMON_FAIL);
        return new Result();
    }

    @GetMapping(value = "/authorities/", params = "roleId")
    public Result authorityTree(HttpServletRequest request, @RequestParam Integer roleId) {
        List<MenuTreeDto> authorityTree = authorityService.tree(roleId);
        List<Integer> selectIds = this.getSelectAuthorityIdsByRoleId(roleId);
        Map<String, Object> data = Maps.newHashMapWithExpectedSize(2);
        data.put("authority", authorityTree);
        data.put("selectIds", selectIds);
        return new Result(data);
    }

    @GetMapping(value = "/authorities/{id}/")
    public Result authorityOne(@PathVariable Integer id) {
        Preconditions.checkNotNull(id, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        Authority authority = authorityService.getOneById(id);
        Preconditions.checkNotNull(authority, ApplicationErrorEnum.COMMON_DATA_NOT_FOUND);
        Map<String, Object> data = Maps.newHashMapWithExpectedSize(1);
        data.put("authority", authority);
        return new Result(data);
    }

    @GetMapping(value = "/authorities-operation/{menuId}")
    public Result getControlByMenuId(@PathVariable Integer menuId) {
        Preconditions.checkNotNull(menuId, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        return authorityService.getAuthByParentId(menuId);
    }


    @GetMapping(value = "/authorities-datarule/{menuId}")
    public Result getDataRuleByMenuId(@PathVariable Integer menuId) {
        Preconditions.checkNotNull(menuId, ApplicationErrorEnum.COMMON_REQUEST_PARAMETER_LESS);
        List<DataFilterRuleDto> dataFilterRuleDtoList = authorityService.getDataRuleByMenuId(menuId);
        return new Result(dataFilterRuleDtoList);
    }

    private List<Integer> getSelectAuthorityIdsByRoleId(Integer id) {
        List<RoleAuthority> roleAuthorities = roleAuthorityService.listByRoleId(id);
        List<Integer> selectIds = roleAuthorities
                .stream()
                .map(RoleAuthority::getAuthorityId)
                .collect(toList());
        return selectIds;
    }

}
