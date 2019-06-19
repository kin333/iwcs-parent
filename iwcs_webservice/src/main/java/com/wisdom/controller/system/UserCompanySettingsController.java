package com.wisdom.controller.system;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.system.dto.UserCompanySettingsDto;
import com.wisdom.iwcs.mapstruct.system.UserCompanySettingsMapStruct;
import com.wisdom.iwcs.service.system.UserCompanySettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对UserCompanySettings的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/usercompanysettings")
public class UserCompanySettingsController {
    @Autowired
    UserCompanySettingsService userCompanySettingsService;
    @Autowired
    UserCompanySettingsMapStruct userCompanySettingsMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param Integer id
     * @return Result
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        userCompanySettingsService.deleteByPrimaryKey(id);

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
        userCompanySettingsService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param UserCompanySettingsDto userCompanySettingsDto
     * @return Result
     */
    @PostMapping
    public Result insert(@RequestBody UserCompanySettingsDto userCompanySettingsDto) {
        userCompanySettingsService.insert(userCompanySettingsDto);

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
        UserCompanySettingsDto userCompanySettingsDto = userCompanySettingsService.selectByPrimaryKey(id);

        return new Result(userCompanySettingsDto);
    }

    /**
     * 分页查询记录
     *
     * @param GridPageRequest gridPageRequest
     * @return Result
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<UserCompanySettingsDto> records = userCompanySettingsService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param UserCompanySettingsDto userCompanySettingsDto
     * @return Result
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody UserCompanySettingsDto userCompanySettingsDto) {
        userCompanySettingsService.updateByPrimaryKey(userCompanySettingsDto);

        return new Result();
    }
}
