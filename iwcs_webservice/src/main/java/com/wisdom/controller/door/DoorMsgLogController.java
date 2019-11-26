package com.wisdom.controller.door;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.door.dto.DoorMsgLogDTO;
import com.wisdom.iwcs.mapstruct.door.DoorMsgLogMapStruct;
import com.wisdom.iwcs.service.door.impl.DoorMsgLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对DoorMsgLog的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/door_msg_log")
public class DoorMsgLogController {
    @Autowired
    DoorMsgLogService doorMsgLogService;
    @Autowired
    DoorMsgLogMapStruct doorMsgLogMapStruct;

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
        doorMsgLogService.deleteByPrimaryKey(id);

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
        doorMsgLogService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param doorMsgLogDTO {@link DoorMsgLogDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody DoorMsgLogDTO doorMsgLogDTO) {
        doorMsgLogService.insert(doorMsgLogDTO);

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
        DoorMsgLogDTO doorMsgLogDTO = doorMsgLogService.selectByPrimaryKey(id);

        return new Result(doorMsgLogDTO);
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
        GridReturnData<DoorMsgLogDTO> records = doorMsgLogService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param doorMsgLogDTO {@link DoorMsgLogDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody DoorMsgLogDTO doorMsgLogDTO) {
        doorMsgLogService.updateByPrimaryKey(doorMsgLogDTO);

        return new Result();
    }
}
