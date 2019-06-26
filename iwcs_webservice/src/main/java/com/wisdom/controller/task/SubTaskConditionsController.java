package com.wisdom.controller.task;


import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.SubTaskConditionsDTO;
import com.wisdom.iwcs.mapstruct.task.SubTaskConditionsMapStruct;
import com.wisdom.iwcs.service.task.ISubTaskConditionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ts_sub_task_conditions")
public class SubTaskConditionsController {

    @Autowired
    ISubTaskConditionsService ISubTaskConditionsService;
    @Autowired
    SubTaskConditionsMapStruct subTaskConditionsMapStruct;

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
        ISubTaskConditionsService.deleteByPrimaryKey(id);

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
        ISubTaskConditionsService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param tsSubTaskConditionsDTO {@link SubTaskConditionsDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody SubTaskConditionsDTO tsSubTaskConditionsDTO) {
        ISubTaskConditionsService.insert(tsSubTaskConditionsDTO);

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
        SubTaskConditionsDTO tsSubTaskConditionsDTO = ISubTaskConditionsService.selectByPrimaryKey(id);

        return new Result(tsSubTaskConditionsDTO);
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
        GridReturnData<SubTaskConditionsDTO> records = ISubTaskConditionsService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param tsSubTaskConditionsDTO {@link SubTaskConditionsDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody SubTaskConditionsDTO tsSubTaskConditionsDTO) {
        ISubTaskConditionsService.updateByPrimaryKey(tsSubTaskConditionsDTO);

        return new Result();
    }
}
