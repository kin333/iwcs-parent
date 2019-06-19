package com.wisdom.controller.task;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.task.dto.AgvTaskDetailOutstockProcessDTO;
import com.wisdom.iwcs.domain.task.dto.SearchAgvTaskProcessDto;
import com.wisdom.iwcs.mapstruct.task.AgvTaskDetailOutstockProcessMapStruct;
import com.wisdom.iwcs.service.task.IAgvTaskDetailOutstockProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对AgvTaskDetailOutstockProcess的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/agv-task-detail-outstock-process")
public class AgvTaskDetailOutstockProcessController {
    @Autowired
    IAgvTaskDetailOutstockProcessService IAgvTaskDetailOutstockProcessService;
    @Autowired
    AgvTaskDetailOutstockProcessMapStruct agvTaskDetailOutstockProcessMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IAgvTaskDetailOutstockProcessService.deleteByPrimaryKey(id);

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
        IAgvTaskDetailOutstockProcessService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param agvTaskDetailOutstockProcessDTO {@link AgvTaskDetailOutstockProcessDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody AgvTaskDetailOutstockProcessDTO agvTaskDetailOutstockProcessDTO) {
        IAgvTaskDetailOutstockProcessService.insert(agvTaskDetailOutstockProcessDTO);

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
        AgvTaskDetailOutstockProcessDTO agvTaskDetailOutstockProcessDTO = IAgvTaskDetailOutstockProcessService.selectByPrimaryKey(id);

        return new Result(agvTaskDetailOutstockProcessDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<AgvTaskDetailOutstockProcessDTO> records = IAgvTaskDetailOutstockProcessService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 查看出库货架库存下架计划及进度（多选）
     *
     * @param searchAgvTaskProcessDto
     * @return
     */
    @PostMapping(value = "/selectAgvDetailProcess")
    public Result selectAgvDetailProcess(@RequestBody SearchAgvTaskProcessDto searchAgvTaskProcessDto) {
        return new Result(IAgvTaskDetailOutstockProcessService.selectAgvDetailProcess(searchAgvTaskProcessDto.getAgvTaskDetailOutstockProcessDTOList()));
    }


    /**
     * 更新记录
     *
     * @param agvTaskDetailOutstockProcessDTO {@link AgvTaskDetailOutstockProcessDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody AgvTaskDetailOutstockProcessDTO agvTaskDetailOutstockProcessDTO) {
        IAgvTaskDetailOutstockProcessService.updateByPrimaryKey(agvTaskDetailOutstockProcessDTO);

        return new Result();
    }
}
