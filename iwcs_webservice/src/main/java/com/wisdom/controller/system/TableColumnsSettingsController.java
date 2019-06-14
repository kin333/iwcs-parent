package com.wisdom.controller.system;

import com.wisdom.controller.mapstruct.system.TableColumnsSettingsMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.system.dto.TableColumnsSettingsDto;
import com.wisdom.service.system.TableColumnsSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对TableColumnsSettings的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/tablecolumnssettings")
public class TableColumnsSettingsController {
    @Autowired
    TableColumnsSettingsService tableColumnsSettingsService;
    @Autowired
    TableColumnsSettingsMapStruct tableColumnsSettingsMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param Integer id
     * @return Result
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        tableColumnsSettingsService.deleteByPrimaryKey(id);

        return new Result();
    }

    /**
     * 根据tableName
     *
     * @param Integer id
     * @return Result
     */
    @DeleteMapping(value = "/delByTableName")
    public Result deleteByTableName(@RequestBody TableColumnsSettingsDto tableColumnsSettingsDto) {
        tableColumnsSettingsService.deleteByTableName(tableColumnsSettingsDto);
        return new Result();
    }

    /**
     * 根据tableName
     *
     * @param Integer id
     * @return Result
     */
    @DeleteMapping(value = "/delByLayout")
    public Result deleteByLayout(@RequestBody TableColumnsSettingsDto tableColumnsSettingsDto) {
        tableColumnsSettingsService.deleteByLayout(tableColumnsSettingsDto);
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
        tableColumnsSettingsService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param TableColumnsSettingsDto tableColumnsSettingsDto
     * @return Result
     */
    @PostMapping
    public Result insert(@RequestBody TableColumnsSettingsDto tableColumnsSettingsDto) {
        tableColumnsSettingsService.insert(tableColumnsSettingsDto);
        return new Result();
    }

    /**
     * 查询记录
     *
     * @param Body
     * @return Result
     */
    @PostMapping(value = "/select")
    public Result selectByTableName(@RequestBody TableColumnsSettingsDto tableColumnsSettingsDto) {
        List<TableColumnsSettingsDto> tableColumnsSettingsDtoList = tableColumnsSettingsService.selectSelective(tableColumnsSettingsDto);
        return new Result(tableColumnsSettingsDtoList);
    }

    /**
     * 查询布局记录
     *
     * @param Body
     * @return Result
     */
    @PostMapping(value = "/selectLayout")
    public Result selectLayout(@RequestBody TableColumnsSettingsDto tableColumnsSettingsDto) {
        List<TableColumnsSettingsDto> tableColumnsSettingsDtoList = tableColumnsSettingsService.selectLayout(tableColumnsSettingsDto);
        return new Result(tableColumnsSettingsDtoList);
    }

    /**
     * 根据主键查询记录
     *
     * @param Integer id
     * @return Result
     */
    @GetMapping(value = "/{id}")
    public Result selectByPrimaryKey(@PathVariable Integer id) {
        TableColumnsSettingsDto tableColumnsSettingsDto = tableColumnsSettingsService.selectByPrimaryKey(id);

        return new Result(tableColumnsSettingsDto);
    }

    /**
     * 分页查询记录
     *
     * @param GridPageRequest gridPageRequest
     * @return Result
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<TableColumnsSettingsDto> records = tableColumnsSettingsService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param TableColumnsSettingsDto tableColumnsSettingsDto
     * @return Result
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody TableColumnsSettingsDto tableColumnsSettingsDto) {
        tableColumnsSettingsService.updateByPrimaryKey(tableColumnsSettingsDto);

        return new Result();
    }
}
