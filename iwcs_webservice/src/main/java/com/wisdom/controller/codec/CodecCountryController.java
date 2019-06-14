package com.wisdom.controller.codec;

import com.wisdom.controller.mapstruct.codec.CodecCountryMapStruct;
import com.wisdom.iwcs.common.utils.GridCountryPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.codec.dto.CodecCountryDto;
import com.wisdom.service.codec.CodecCountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 对CodecCountry的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/codeccountry")
public class CodecCountryController {
    @Autowired
    CodecCountryService codecCountryService;
    @Autowired
    CodecCountryMapStruct codecCountryMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param Integer id
     * @return Result
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        codecCountryService.deleteByPrimaryKey(id);

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
        codecCountryService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param CodecCountryDto codecCountryDto
     * @return Result
     */
    @PostMapping
    public Result insert(@RequestBody CodecCountryDto codecCountryDto) {
        codecCountryService.insert(codecCountryDto);

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
        CodecCountryDto codecCountryDto = codecCountryService.selectByPrimaryKey(id);

        return new Result(codecCountryDto);
    }

    /**
     * 分页查询记录
     *
     * @param GridPageRequest gridPageRequest
     * @return Result
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridCountryPageRequest gridCountryPageRequest) {
        GridReturnData<CodecCountryDto> records = codecCountryService.selectPage(gridCountryPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param CodecCountryDto codecCountryDto
     * @return Result
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody CodecCountryDto codecCountryDto) {
        codecCountryService.updateByPrimaryKey(codecCountryDto);

        return new Result();
    }

    @PostMapping(value = "/upload")
    public Result upload(MultipartFile file) {

        return new Result();
    }


}
