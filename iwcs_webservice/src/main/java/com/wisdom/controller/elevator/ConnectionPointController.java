package com.wisdom.controller.elevator;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.elevator.dto.ConnectionPointDTO;
import com.wisdom.iwcs.mapstruct.elevator.ConnectionPointMapStruct;
import com.wisdom.iwcs.service.elevator.Impl.ConnectionPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/connection_point")
public class ConnectionPointController {
    @Autowired
    ConnectionPointService connectionPointService;
    @Autowired
    ConnectionPointMapStruct connectionPointMapStruct;

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
        connectionPointService.deleteByPrimaryKey(id);

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
        connectionPointService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param connectionPointDTO {@link ConnectionPointDTO }
     *
     * @return {@link Result }
     */

    @PostMapping
    public Result insert(@RequestBody ConnectionPointDTO connectionPointDTO) {
        connectionPointService.insert(connectionPointDTO);

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
        ConnectionPointDTO connectionPointDTO = connectionPointService.selectByPrimaryKey(id);

        return new Result(connectionPointDTO);
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
        GridReturnData<ConnectionPointDTO> records = connectionPointService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param connectionPointDTO {@link ConnectionPointDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody ConnectionPointDTO connectionPointDTO) {
        connectionPointService.updateByPrimaryKey(connectionPointDTO);

        return new Result();
    }
}
