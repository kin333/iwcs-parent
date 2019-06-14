package com.wisdom.controller.instock;

import com.wisdom.controller.mapstruct.instock.InstockCallStraParamMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraParamDTO;
import com.wisdom.service.instock.instockImpl.InstockCallStraParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对InstockCallStraParam的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/instock_call_stra_param")
public class InstockCallStraParamController {
    @Autowired
    InstockCallStraParamService instockCallStraParamService;
    @Autowired
    InstockCallStraParamMapStruct instockCallStraParamMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        instockCallStraParamService.deleteByPrimaryKey(id);

        return new Result();
    }

    /**
     * 根据主键ID删除多条记录
     *
     * @param ids {@link List<String> }
     * @return {@link Result }
     */
    @DeleteMapping
    public Result deleteMoreByIds(@RequestBody List<String> ids) {
        instockCallStraParamService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param instockCallStraParamDTO {@link InstockCallStraParamDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody InstockCallStraParamDTO instockCallStraParamDTO) {
        instockCallStraParamService.insert(instockCallStraParamDTO);

        return new Result();
    }

    /**
     * 根据主键查询记录
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @GetMapping(value = "/{id}")
    public Result selectByPrimaryKey(@PathVariable Integer id) {
        InstockCallStraParamDTO instockCallStraParamDTO = instockCallStraParamService.selectByPrimaryKey(id);

        return new Result(instockCallStraParamDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<InstockCallStraParamDTO> records = instockCallStraParamService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param instockCallStraParamDTO {@link InstockCallStraParamDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody InstockCallStraParamDTO instockCallStraParamDTO) {
        instockCallStraParamService.updateByPrimaryKey(instockCallStraParamDTO);

        return new Result();
    }
}
