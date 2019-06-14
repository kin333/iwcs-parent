package com.wisdom.controller.task;

import com.wisdom.controller.mapstruct.task.AgvTaskOutstockStockMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.AgvTaskOutstockStockDTO;
import com.wisdom.service.task.IAgvTaskOutstockStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对AgvTaskOutstockStock的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/agv-task-outstock-stock")
public class AgvTaskOutstockStockController {
    @Autowired
    IAgvTaskOutstockStockService IAgvTaskOutstockStockService;
    @Autowired
    AgvTaskOutstockStockMapStruct agvTaskOutstockStockMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IAgvTaskOutstockStockService.deleteByPrimaryKey(id);

        return new Result();
    }


    /**
     * 新增记录
     *
     * @param agvTaskOutstockStockDTO {@link AgvTaskOutstockStockDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody AgvTaskOutstockStockDTO agvTaskOutstockStockDTO) {
        IAgvTaskOutstockStockService.insert(agvTaskOutstockStockDTO);

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
        AgvTaskOutstockStockDTO agvTaskOutstockStockDTO = IAgvTaskOutstockStockService.selectByPrimaryKey(id);

        return new Result(agvTaskOutstockStockDTO);
    }

    /**
     * 根据taskNo获取出库任务库存呼叫明细
     *
     * @param taskNo
     * @return
     */
    @GetMapping(value = "/selectAgvTaskOutStockByTaskNo/{taskNo}")
    public Result selectAgvTaskOutStockByTaskNo(@PathVariable String taskNo) {
        return new Result(IAgvTaskOutstockStockService.selectAgvTaskOutStockByTaskNo(taskNo));
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<AgvTaskOutstockStockDTO> records = IAgvTaskOutstockStockService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param agvTaskOutstockStockDTO {@link AgvTaskOutstockStockDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody AgvTaskOutstockStockDTO agvTaskOutstockStockDTO) {
        IAgvTaskOutstockStockService.updateByPrimaryKey(agvTaskOutstockStockDTO);

        return new Result();
    }
}
