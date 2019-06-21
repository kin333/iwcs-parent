package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.TsSubTaskTypDTO;
import com.wisdom.iwcs.mapstruct.task.TsSubTaskTypMapStruct;
import com.wisdom.iwcs.service.task.TsSubTaskTypService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 对TsSubTaskTyp的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/ts_sub_task_typ")
public class TsSubTaskTypController {
    @Autowired
    TsSubTaskTypService tsSubTaskTypService;
    @Autowired
    TsSubTaskTypMapStruct tsSubTaskTypMapStruct;

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
        tsSubTaskTypService.deleteByPrimaryKey(id);

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
        tsSubTaskTypService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param tsSubTaskTypDTO {@link TsSubTaskTypDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody TsSubTaskTypDTO tsSubTaskTypDTO) {
        tsSubTaskTypService.insert(tsSubTaskTypDTO);

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
        TsSubTaskTypDTO tsSubTaskTypDTO = tsSubTaskTypService.selectByPrimaryKey(id);

        return new Result(tsSubTaskTypDTO);
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
        GridReturnData<TsSubTaskTypDTO> records = tsSubTaskTypService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param tsSubTaskTypDTO {@link TsSubTaskTypDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody TsSubTaskTypDTO tsSubTaskTypDTO) {
        tsSubTaskTypService.updateByPrimaryKey(tsSubTaskTypDTO);

        return new Result();
    }
}
