package com.wisdom.controller.system;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.system.dto.GroupDto;
import com.wisdom.iwcs.mapstruct.system.GroupMapStruct;
import com.wisdom.iwcs.service.system.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对Group的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/group")
public class GroupController {
    @Autowired
    GroupService groupService;
    @Autowired
    GroupMapStruct groupMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param Integer id
     * @return Result
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        groupService.deleteByPrimaryKey(id);

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
        groupService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param GroupDto groupDto
     * @return Result
     */
    @PostMapping
    public Result insert(@RequestBody GroupDto groupDto) {
        groupService.insert(groupDto);

        return new Result();
    }

    /**
     * 根据主键查询记录
     *
     * @param Integer id
     * @return Result
     */
    @GetMapping(value = "/{id}")
    public Result selectByPrimaryKey(@PathVariable Integer id) {
        GroupDto groupDto = groupService.selectByPrimaryKey(id);

        return new Result(groupDto);
    }

    /**
     * 分页查询记录
     *
     * @param GridPageRequest gridPageRequest
     * @return Result
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<GroupDto> records = groupService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param GroupDto groupDto
     * @return Result
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody GroupDto groupDto) {
        groupService.updateByPrimaryKey(groupDto);

        return new Result();
    }
}
