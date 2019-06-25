package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.MainTaskDTO;
import com.wisdom.iwcs.mapstruct.task.MainTaskMapStruct;
import com.wisdom.iwcs.service.task.MainTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 对MainTask的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/main_task")
public class MainTaskController {
    @Autowired
    MainTaskService mainTaskService;
    @Autowired
    MainTaskMapStruct mainTaskMapStruct;

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
        mainTaskService.deleteByPrimaryKey(id);

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
        mainTaskService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param mainTaskDTO {@link MainTaskDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody MainTaskDTO mainTaskDTO) {
        mainTaskService.insert(mainTaskDTO);

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
        MainTaskDTO mainTaskDTO = mainTaskService.selectByPrimaryKey(id);

        return new Result(mainTaskDTO);
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
        GridReturnData<MainTaskDTO> records = mainTaskService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param mainTaskDTO {@link MainTaskDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody MainTaskDTO mainTaskDTO) {
        mainTaskService.updateByPrimaryKey(mainTaskDTO);

        return new Result();
    }
}
