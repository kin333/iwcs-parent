package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.MainTaskResPreHandlerDTO;
import com.wisdom.iwcs.mapstruct.task.MainTaskResPreHandlerMapStruct;
import com.wisdom.iwcs.service.task.impl.MainTaskResPreHandlerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wisdom.iwcs.common.utils.Result;


/**
 * 对MainTaskResPreHandler的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/main_task_res_pre_handler")
public class MainTaskResPreHandlerController {
    @Autowired
    MainTaskResPreHandlerService mainTaskResPreHandlerService;
    @Autowired
    MainTaskResPreHandlerMapStruct mainTaskResPreHandlerMapStruct;

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
        mainTaskResPreHandlerService.deleteByPrimaryKey(id);

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
        mainTaskResPreHandlerService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param mainTaskResPreHandlerDTO {@link MainTaskResPreHandlerDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody MainTaskResPreHandlerDTO mainTaskResPreHandlerDTO) {
        mainTaskResPreHandlerService.insert(mainTaskResPreHandlerDTO);

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
        MainTaskResPreHandlerDTO mainTaskResPreHandlerDTO = mainTaskResPreHandlerService.selectByPrimaryKey(id);

        return new Result(mainTaskResPreHandlerDTO);
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
        GridReturnData<MainTaskResPreHandlerDTO> records = mainTaskResPreHandlerService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param mainTaskResPreHandlerDTO {@link MainTaskResPreHandlerDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody MainTaskResPreHandlerDTO mainTaskResPreHandlerDTO) {
        mainTaskResPreHandlerService.updateByPrimaryKey(mainTaskResPreHandlerDTO);

        return new Result();
    }
}
