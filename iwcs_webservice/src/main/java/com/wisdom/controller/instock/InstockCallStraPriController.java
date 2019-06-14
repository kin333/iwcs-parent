package com.wisdom.controller.instock;

import com.wisdom.controller.mapstruct.instock.InstockCallStraPriMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.instock.dto.InstockCallStraPriDTO;
import com.wisdom.service.instock.instockImpl.InstockCallStraPriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对InstockCallStraPri的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/instock_call_stra_pri")
public class InstockCallStraPriController {
    @Autowired
    InstockCallStraPriService instockCallStraPriService;
    @Autowired
    InstockCallStraPriMapStruct instockCallStraPriMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        instockCallStraPriService.deleteByPrimaryKey(id);

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
        instockCallStraPriService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param instockCallStraPriDTO {@link InstockCallStraPriDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody InstockCallStraPriDTO instockCallStraPriDTO) {
        instockCallStraPriService.insert(instockCallStraPriDTO);

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
        InstockCallStraPriDTO instockCallStraPriDTO = instockCallStraPriService.selectByPrimaryKey(id);

        return new Result(instockCallStraPriDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<InstockCallStraPriDTO> records = instockCallStraPriService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param instockCallStraPriDTO {@link InstockCallStraPriDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody InstockCallStraPriDTO instockCallStraPriDTO) {
        instockCallStraPriService.updateByPrimaryKey(instockCallStraPriDTO);

        return new Result();
    }
}
