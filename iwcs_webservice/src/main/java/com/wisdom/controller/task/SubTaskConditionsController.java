package com.wisdom.controller.task;


import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.SubTaskConditionDTO;
import com.wisdom.iwcs.mapstruct.task.SubTaskConditionMapStruct;
import com.wisdom.iwcs.service.task.intf.ISubTaskConditionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/ts_sub_task_conditions")
public class SubTaskConditionsController {

    @Autowired
    ISubTaskConditionsService ISubTaskConditionsService;
    @Autowired
    SubTaskConditionMapStruct subTaskConditionMapStruct;

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
     * @param tsSubTaskConditionDTO {@link SubTaskConditionDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody SubTaskConditionDTO tsSubTaskConditionDTO) {
        ISubTaskConditionsService.insert(tsSubTaskConditionDTO);

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
        SubTaskConditionDTO tsSubTaskConditionDTO = ISubTaskConditionsService.selectByPrimaryKey(id);

        return new Result(tsSubTaskConditionDTO);
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
        GridReturnData<SubTaskConditionDTO> records = ISubTaskConditionsService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param tsSubTaskConditionDTO {@link SubTaskConditionDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody SubTaskConditionDTO tsSubTaskConditionDTO) {
        ISubTaskConditionsService.updateByPrimaryKey(tsSubTaskConditionDTO);

        return new Result();
    }
}
