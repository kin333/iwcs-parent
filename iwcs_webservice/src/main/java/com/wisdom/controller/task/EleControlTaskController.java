package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.EleControlTaskDTO;
import com.wisdom.iwcs.mapstruct.task.EleControlTaskMapStruct;
import com.wisdom.iwcs.service.task.impl.EleControlTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 对EleControlTask的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/ele_control_task")
public class EleControlTaskController {
    @Autowired
    EleControlTaskService eleControlTaskService;
    @Autowired
    EleControlTaskMapStruct eleControlTaskMapStruct;

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
        eleControlTaskService.deleteByPrimaryKey(id);

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
        eleControlTaskService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param eleControlTaskDTO {@link EleControlTaskDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody EleControlTaskDTO eleControlTaskDTO) {
        eleControlTaskService.insert(eleControlTaskDTO);

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
        EleControlTaskDTO eleControlTaskDTO = eleControlTaskService.selectByPrimaryKey(id);

        return new Result(eleControlTaskDTO);
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
        GridReturnData<EleControlTaskDTO> records = eleControlTaskService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param eleControlTaskDTO {@link EleControlTaskDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody EleControlTaskDTO eleControlTaskDTO) {
        eleControlTaskService.updateByPrimaryKey(eleControlTaskDTO);

        return new Result();
    }
}
