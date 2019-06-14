package com.wisdom.controller.inv;

import com.wisdom.controller.mapstruct.inv.InvTaskMapStruct;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.inv.InvTask;
import com.wisdom.iwcs.domain.inv.InvTaskCondDetail;
import com.wisdom.iwcs.domain.inv.dto.InvTaskDTO;
import com.wisdom.iwcs.domain.inv.dto.InvTaskSearchDto;
import com.wisdom.service.inv.invImpl.InvTaskCondDetailService;
import com.wisdom.service.inv.invImpl.InvTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对InvTask的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/inv-task")
public class InvTaskController {
    @Autowired
    InvTaskService invTaskService;
    @Autowired
    InvTaskCondDetailService invTaskCondDetailService;
    @Autowired
    InvTaskMapStruct invTaskMapStruct;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        invTaskService.deleteByPrimaryKey(id);

        return new Result();
    }

    /**
     * 新增盘点任务
     *
     * @param invTaskDTO {@link InvTaskDTO }
     * @return {@link Result }
     */
    @PostMapping("/createInvTask")
    public Result createInvTask(@RequestBody InvTaskDTO invTaskDTO) {
        return new Result(invTaskService.createInvTask(invTaskDTO));
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<InvTaskDTO> records = invTaskService.selectPage(gridPageRequest);
        return new Result(records);
    }

    /**
     * 获取盘点任务
     *
     * @param invTaskSearchDto
     * @return
     */
    @PostMapping(value = "/selectInvTask")
    public Result selectInvTask(@RequestBody InvTaskSearchDto invTaskSearchDto) {
        List<InvTask> records = invTaskService.selectInvTask(invTaskSearchDto);
        return new Result(records);
    }

    /**
     * 获取盘点条件信息
     *
     * @param invNum
     * @return
     */
    @GetMapping(value = "/selectInvTaskCondDetail/{invNum}")
    public Result selectInvTaskCondDetail(@PathVariable String invNum) {
        List<InvTaskCondDetail> records = invTaskCondDetailService.selectInvTaskCondDetail(invNum);
        return new Result(records);
    }


}
