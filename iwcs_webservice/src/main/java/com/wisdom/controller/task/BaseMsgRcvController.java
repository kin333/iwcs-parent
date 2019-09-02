package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.BaseMsgRcvDTO;
import com.wisdom.iwcs.mapstruct.task.BaseMsgRcvMapStruct;
import com.wisdom.iwcs.service.task.impl.BaseMsgRcvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wisdom.iwcs.common.utils.Result;


/**
 * 对BaseMsgRcv的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/base_msg_rcv")
public class BaseMsgRcvController {
    @Autowired
    BaseMsgRcvService baseMsgRcvService;
    @Autowired
    BaseMsgRcvMapStruct baseMsgRcvMapStruct;

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
        baseMsgRcvService.deleteByPrimaryKey(id);

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
        baseMsgRcvService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param baseMsgRcvDTO {@link BaseMsgRcvDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseMsgRcvDTO baseMsgRcvDTO) {
        baseMsgRcvService.insert(baseMsgRcvDTO);

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
        BaseMsgRcvDTO baseMsgRcvDTO = baseMsgRcvService.selectByPrimaryKey(id);

        return new Result(baseMsgRcvDTO);
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
        GridReturnData<BaseMsgRcvDTO> records = baseMsgRcvService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param baseMsgRcvDTO {@link BaseMsgRcvDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseMsgRcvDTO baseMsgRcvDTO) {
        baseMsgRcvService.updateByPrimaryKey(baseMsgRcvDTO);

        return new Result();
    }
}
