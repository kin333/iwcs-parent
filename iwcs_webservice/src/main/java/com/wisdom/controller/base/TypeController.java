package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.CodeAndName;
import com.wisdom.iwcs.mapper.base.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 包含一些返回类型的接口
 *
 * @author han
 */
@RestController
@RequestMapping("/api/type")
public class TypeController {

    @Autowired
    TypeMapper typeMapper;

    /**
     * 根据仓库编码返回仓位类型编码和仓位类型名称
     *
     * @param whCode 仓库编码
     * @return
     */
    @GetMapping(value = {"/getBincodeType/{whCode}", "/getBincodeType"})
    public Result getBincodeType(@PathVariable(required = false) String whCode) {
        List<CodeAndName> types = typeMapper.getBincodeType(whCode);
        return new Result(types);
    }

    /**
     * 根据仓库编码返回货架类型编码和货架类型名称
     *
     * @param whCode 仓库编码
     * @return
     */
    @GetMapping(value = {"/getPodType/{whCode}", "/getPodType"})
    public Result getPodType(@PathVariable(required = false) String whCode) {
        List<CodeAndName> types = typeMapper.getPodType(whCode);
        return new Result(types);
    }

    /**
     * 根据仓库编码返回库区类型编码和库区类型名称
     *
     * @param whCode 仓库编码
     * @return
     */
    @GetMapping(value = {"/getWhAreaType/{whCode}", "/getWhAreaType"})
    public Result getWhAreaType(@PathVariable(required = false) String whCode) {
        List<CodeAndName> types = typeMapper.getWhAreaType(whCode);
        return new Result(types);
    }

    /**
     * 返回仓库类型编码和仓库类型名称
     *
     * @return
     */
    @GetMapping("/getWhType")
    public Result getWhType() {
        List<CodeAndName> types = typeMapper.getWhType();
        return new Result(types);
    }

}
