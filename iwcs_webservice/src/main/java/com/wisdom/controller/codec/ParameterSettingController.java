package com.wisdom.controller.codec;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.codec.dto.ParameterSettingDto;
import com.wisdom.iwcs.mapstruct.codec.ParameterSettingMapStruct;
import com.wisdom.iwcs.service.codec.ParameterSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对ParameterSetting的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/parametersetting")
public class ParameterSettingController {
    @Autowired
    ParameterSettingService parameterSettingService;
    @Autowired
    ParameterSettingMapStruct parameterSettingMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param Integer id
     * @return Result
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        parameterSettingService.deleteByPrimaryKey(id);

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
        parameterSettingService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param ParameterSettingDto parameterSettingDto
     * @return Result
     */
    @PostMapping
    public Result insert(@RequestBody ParameterSettingDto parameterSettingDto) {
        parameterSettingService.insert(parameterSettingDto);

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
        ParameterSettingDto parameterSettingDto = parameterSettingService.selectByPrimaryKey(id);

        return new Result(parameterSettingDto);
    }

    /**
     * 分页查询记录
     *
     * @param GridPageRequest gridPageRequest
     * @return Result
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<ParameterSettingDto> records = parameterSettingService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param ParameterSettingDto parameterSettingDto
     * @return Result
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody ParameterSettingDto parameterSettingDto) {
        parameterSettingService.updateByPrimaryKey(parameterSettingDto);

        return new Result();
    }
}
