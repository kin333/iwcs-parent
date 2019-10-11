package com.wisdom.controller.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import  com.wisdom.iwcs.mapstruct.task.imitateTestMapStruct;
import com.wisdom.iwcs.domain.task.dto.imitateTestDTO;
import com.wisdom.iwcs.service.task.impl.imitateTestService;
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
public class imitateTestController {
    @Autowired
    imitateTestService   imitateTestService;
    @Autowired
    imitateTestMapStruct imitateTestMapStruct;

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
        imitateTestService.deleteByPrimaryKey(id);

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
        imitateTestService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param imitateTestDTO {@link imitateTestDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody imitateTestDTO imitateTestDTO) {
        imitateTestService.insert(imitateTestDTO);

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
        imitateTestDTO imitateTestDTO = imitateTestService.selectByPrimaryKey(id);

        return new Result(imitateTestDTO);
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
        GridReturnData<imitateTestDTO> records = imitateTestService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param imitateTestDTO {@link imitateTestDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody imitateTestDTO imitateTestDTO) {
        imitateTestService.updateByPrimaryKey(imitateTestDTO);

        return new Result();
    }
}
