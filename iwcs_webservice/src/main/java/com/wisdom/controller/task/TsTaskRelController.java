package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.TsTaskRelDTO;
import com.wisdom.iwcs.mapstruct.task.TsTaskRelMapStruct;
import com.wisdom.iwcs.service.task.TsTaskRelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 对TsTaskRel的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/ts_task_rel")
public class TsTaskRelController {
    @Autowired
    TsTaskRelService tsTaskRelService;
    @Autowired
    TsTaskRelMapStruct tsTaskRelMapStruct;

    /**
     * 根据主键ID删除
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result  deleteByPrimaryKey(@PathVariable Integer id) {
        tsTaskRelService.deleteByPrimaryKey(id);

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
        tsTaskRelService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param tsTaskRelDTO {@link TsTaskRelDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody TsTaskRelDTO tsTaskRelDTO) {
        tsTaskRelService.insert(tsTaskRelDTO);

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
        TsTaskRelDTO tsTaskRelDTO = tsTaskRelService.selectByPrimaryKey(id);

        return new Result(tsTaskRelDTO);
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
        GridReturnData<TsTaskRelDTO> records = tsTaskRelService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param tsTaskRelDTO {@link TsTaskRelDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody TsTaskRelDTO tsTaskRelDTO) {
        tsTaskRelService.updateByPrimaryKey(tsTaskRelDTO);

        return new Result();
    }
}
