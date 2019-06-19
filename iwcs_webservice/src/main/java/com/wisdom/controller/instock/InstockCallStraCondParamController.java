package com.wisdom.controller.instock;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraCondParamDTO;
import com.wisdom.iwcs.mapstruct.instock.InstockCallStraCondParamMapStruct;
import com.wisdom.iwcs.service.instock.instockImpl.InstockCallStraCondParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对InstockCallStraCondParam的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/instock_call_stra_cond_param")
public class InstockCallStraCondParamController {
    @Autowired
    InstockCallStraCondParamService instockCallStraCondParamService;
    @Autowired
    InstockCallStraCondParamMapStruct instockCallStraCondParamMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        instockCallStraCondParamService.deleteByPrimaryKey(id);

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
        instockCallStraCondParamService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param instockCallStraCondParamDTO {@link InstockCallStraCondParamDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody InstockCallStraCondParamDTO instockCallStraCondParamDTO) {
        instockCallStraCondParamService.insert(instockCallStraCondParamDTO);

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
        InstockCallStraCondParamDTO instockCallStraCondParamDTO = instockCallStraCondParamService.selectByPrimaryKey(id);

        return new Result(instockCallStraCondParamDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<InstockCallStraCondParamDTO> records = instockCallStraCondParamService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param instockCallStraCondParamDTO {@link InstockCallStraCondParamDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody InstockCallStraCondParamDTO instockCallStraCondParamDTO) {
        instockCallStraCondParamService.updateByPrimaryKey(instockCallStraCondParamDTO);

        return new Result();
    }
}
