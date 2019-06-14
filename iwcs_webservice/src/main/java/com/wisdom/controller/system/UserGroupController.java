package com.wisdom.controller.system;

import com.wisdom.controller.mapstruct.system.UserGroupMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.system.dto.UserGroupDto;
import com.wisdom.service.system.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对UserGroup的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/usergroup")
public class UserGroupController {
    @Autowired
    UserGroupService userGroupService;
    @Autowired
    UserGroupMapStruct userGroupMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param Integer id
     * @return Result
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        userGroupService.deleteByPrimaryKey(id);

        return new Result();
    }

    /**
     * 根据主键ID删除多条记录
     *
     * @param List<String> ids
     * @return Result
     */
    @DeleteMapping
    public Result deleteMoreByIds(@RequestBody List<String> ids) {
        userGroupService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param UserGroupDto userGroupDto
     * @return Result
     */
    @PostMapping
    public Result insert(@RequestBody UserGroupDto userGroupDto) {
        userGroupService.insert(userGroupDto);

        return new Result();
    }

    @PostMapping("/list")
    public Result insertList(@RequestBody List<UserGroupDto> userGroupDtoList) {
        userGroupService.insertBatch(userGroupDtoList);

        return new Result();
    }

    /**
     * 根据主键查询记录
     *
     * @return Result
     */
    @GetMapping(value = "/{id}")
    public Result selectByPrimaryKey(@PathVariable Integer id) {
        UserGroupDto userGroupDto = userGroupService.selectByPrimaryKey(id);

        return new Result(userGroupDto);
    }

    /**
     * 分页查询记录
     *
     * @return Result
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<UserGroupDto> records = userGroupService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param UserGroupDto userGroupDto
     * @return Result
     */
    @PutMapping
    public Result updateByPrimaryKeySelective(@RequestBody UserGroupDto userGroupDto) {
        userGroupService.updateByPrimaryKeySelective(userGroupDto);

        return new Result();
    }

    @PostMapping(value = "/users")
    public Result getGroupUsers(@RequestBody UserGroupDto userGroupDto) {
        return userGroupService.getGroupUsers(userGroupDto);
    }
}
