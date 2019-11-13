package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.SubTaskTyp;
import com.wisdom.iwcs.domain.task.dto.SubTaskTypDTO;
import com.wisdom.iwcs.mapstruct.task.SubTaskTypMapStruct;
import com.wisdom.iwcs.service.task.impl.SubTaskTypService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 对SubTaskTyp的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/ts_sub_task_typ")
public class SubTaskTypController {
    @Autowired
    SubTaskTypService SubTaskTypService;
    @Autowired
    SubTaskTypMapStruct SubTaskTypMapStruct;

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
        SubTaskTypService.deleteByPrimaryKey(id);

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
        SubTaskTypService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param SubTaskTypDTO {@link SubTaskTypDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody SubTaskTypDTO SubTaskTypDTO) {
        int num = SubTaskTypService.insert(SubTaskTypDTO);

        if (num == 400) {
            return  new Result(400,"该子任务类型编号" + SubTaskTypDTO.getSubTaskTypCode() + "已存在");
        }

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
        SubTaskTypDTO SubTaskTypDTO = SubTaskTypService.selectByPrimaryKey(id);

        return new Result(SubTaskTypDTO);
    }

    /**
     * 根据subTaskCode查询
     */
    @PostMapping("/getSubTaskTYpeByCode")
    public Result selectSubTaskTypeByCode(@RequestBody SubTaskTyp subTaskTyp) {
        SubTaskTyp subTaskType = SubTaskTypService.selectSubTaskTypeByCode(subTaskTyp);
        return  new Result(subTaskType);
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
        GridReturnData<SubTaskTypDTO> records = SubTaskTypService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param SubTaskTypDTO {@link SubTaskTypDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody SubTaskTypDTO SubTaskTypDTO) {
        SubTaskTypService.updateByPrimaryKey(SubTaskTypDTO);

        return new Result();
    }
}
