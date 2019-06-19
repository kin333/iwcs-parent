package com.wisdom.controller.system;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.system.dto.UserDefinedSettingsDto;
import com.wisdom.iwcs.mapstruct.system.UserDefinedSettingsMapStruct;
import com.wisdom.iwcs.service.system.UserDefinedSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对UserDefinedSettings的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/userdefinedsettings")
public class UserDefinedSettingsController {
    @Autowired
    UserDefinedSettingsService userDefinedSettingsService;
    @Autowired
    UserDefinedSettingsMapStruct userDefinedSettingsMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param Integer id
     * @return Result
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        userDefinedSettingsService.deleteByPrimaryKey(id);

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
        userDefinedSettingsService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param UserDefinedSettingsDto userDefinedSettingsDto
     * @return Result
     */
    @PostMapping
    public Result insert(@RequestBody UserDefinedSettingsDto userDefinedSettingsDto) {
        userDefinedSettingsService.insert(userDefinedSettingsDto);

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
        UserDefinedSettingsDto userDefinedSettingsDto = userDefinedSettingsService.selectByPrimaryKey(id);

        return new Result(userDefinedSettingsDto);
    }

    /**
     * 分页查询记录
     *
     * @param GridPageRequest gridPageRequest
     * @return Result
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<UserDefinedSettingsDto> records = userDefinedSettingsService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param UserDefinedSettingsDto userDefinedSettingsDto
     * @return Result
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody UserDefinedSettingsDto userDefinedSettingsDto) {
        userDefinedSettingsService.updateByPrimaryKey(userDefinedSettingsDto);
        return new Result();
    }

    /**
     * 查询
     *
     * @param UserDefinedSettingsDto userDefinedSettingsDto
     * @return Result
     */
    @PostMapping(value = "/selectByName")
    public Result selectByName(@RequestBody UserDefinedSettingsDto userDefinedSettingsDto) {
        List<UserDefinedSettingsDto> userDefinedSettingsList = userDefinedSettingsService.selectByName(userDefinedSettingsDto);
        return new Result(userDefinedSettingsList);
    }
}
