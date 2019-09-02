package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.domain.task.dto.BaseMsgSendDTO;
import com.wisdom.iwcs.mapstruct.task.BaseMsgSendMapStruct;
import com.wisdom.iwcs.service.task.impl.BaseMsgSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wisdom.iwcs.common.utils.Result;


/**
 * 对BaseMsgSend的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/base_msg_send")
public class BaseMsgSendController {
    @Autowired
    BaseMsgSendService baseMsgSendService;
    @Autowired
    BaseMsgSendMapStruct baseMsgSendMapStruct;

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
        baseMsgSendService.deleteByPrimaryKey(id);

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
        baseMsgSendService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param baseMsgSendDTO {@link BaseMsgSendDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BaseMsgSendDTO baseMsgSendDTO) {
        baseMsgSendService.insert(baseMsgSendDTO);

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
        BaseMsgSendDTO baseMsgSendDTO = baseMsgSendService.selectByPrimaryKey(id);

        return new Result(baseMsgSendDTO);
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
        GridReturnData<BaseMsgSendDTO> records = baseMsgSendService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param baseMsgSendDTO {@link BaseMsgSendDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BaseMsgSendDTO baseMsgSendDTO) {
        baseMsgSendService.updateByPrimaryKey(baseMsgSendDTO);

        return new Result();
    }
}
