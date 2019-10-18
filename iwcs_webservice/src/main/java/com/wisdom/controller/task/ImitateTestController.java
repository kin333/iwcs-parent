package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.domain.task.dto.ImitateTestDTO;
import com.wisdom.iwcs.mapstruct.task.ImitateTestMapStruct;
import com.wisdom.iwcs.service.task.wcsSimulator.NomalTaskCreateWorker;
import com.wisdom.iwcs.service.task.wcsSimulator.RollerEmptyTaskCreateWorker;
import com.wisdom.iwcs.service.task.wcsSimulator.RollerTaskCreateWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wisdom.iwcs.service.task.impl.ImitateTestService;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
/**
 * 对imitateTest的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/imitate_test")
public class ImitateTestController {
    @Autowired
    ImitateTestService ImitateTestService;

    /**
     * 根据主键ID删除
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        ImitateTestService.deleteByPrimaryKey(id);

        return new Result();
    }

    /**
     * 根据主键ID删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return {@link Result }
     */
    @DeleteMapping
    public Result deleteMoreByIds(@RequestBody List<String> ids) {
        ImitateTestService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param ImitateTestDTO {@link ImitateTestDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody ImitateTestDTO ImitateTestDTO) {
        ImitateTestService.insert(ImitateTestDTO);

        return new Result();
    }

    /**
     * 根据主键查询记录
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link Result }
     */
    @GetMapping(value = "/{id}")
    public Result selectByPrimaryKey(@PathVariable Integer id) {
        ImitateTestDTO ImitateTestDTO = ImitateTestService.selectByPrimaryKey(id);

        return new Result(ImitateTestDTO);
    }

    /**
     * 分页查询记录
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<ImitateTestDTO> records = ImitateTestService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param ImitateTestDTO {@link ImitateTestDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody ImitateTestDTO ImitateTestDTO) {
        ImitateTestService.updateByPrimaryKey(ImitateTestDTO);

        return new Result();
    }
}
