package com.wisdom.controller.log;

import com.wisdom.controller.mapstruct.log.InterfaceLogMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.log.dto.InterfaceLogDTO;
import com.wisdom.service.log.IInterfaceLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对InterfaceLog的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/interface-log")
public class InterfaceLogController {
    @Autowired
    IInterfaceLogService IInterfaceLogService;
    @Autowired
    InterfaceLogMapStruct interfaceLogMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IInterfaceLogService.deleteByPrimaryKey(id);

        return new Result();
    }

    /**
     * 根据主键ID删除多条记录
     *
     * @param ids {@link List<String> }
     * @return {@link Result }
     */
    @DeleteMapping
    public Result deleteMoreByIds(@RequestBody List<String> ids) {
        IInterfaceLogService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param interfaceLogDTO {@link InterfaceLogDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody InterfaceLogDTO interfaceLogDTO) {
        IInterfaceLogService.insert(interfaceLogDTO);

        return new Result();
    }

    /**
     * 根据主键查询记录
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @GetMapping(value = "/{id}")
    public Result selectByPrimaryKey(@PathVariable Integer id) {
        InterfaceLogDTO interfaceLogDTO = IInterfaceLogService.selectByPrimaryKey(id);

        return new Result(interfaceLogDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<InterfaceLogDTO> records = IInterfaceLogService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param interfaceLogDTO {@link InterfaceLogDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody InterfaceLogDTO interfaceLogDTO) {
        IInterfaceLogService.updateByPrimaryKey(interfaceLogDTO);

        return new Result();
    }
}
