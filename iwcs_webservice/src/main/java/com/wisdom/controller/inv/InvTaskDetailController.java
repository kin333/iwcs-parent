package com.wisdom.controller.inv;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.inv.dto.InvTaskResultDetailDTO;
import com.wisdom.iwcs.domain.inv.dto.InvTaskStartDto;
import com.wisdom.iwcs.mapstruct.inv.InvTaskResultDetailMapStruct;
import com.wisdom.iwcs.service.inv.invImpl.InvSnService;
import com.wisdom.iwcs.service.inv.invImpl.InvTaskDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 对InvTaskDetail的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/inv-task-detail")
public class InvTaskDetailController {
    @Autowired
    InvTaskDetailService invTaskDetailService;
    @Autowired
    InvTaskResultDetailMapStruct invTaskDetailMapStruct;

    @Autowired
    InvSnService invSnServiceImpl;

    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        invTaskDetailService.deleteByPrimaryKey(id);

        return new Result();
    }

    /**
     * 根据srcInvNo 查询以盘点数据
     *
     * @param srcInvNo
     * @return
     */
    @GetMapping(value = "/selectBySrcInvNo/{srcInvNo}")
    public Result selectByInvId(@PathVariable("srcInvNo") String srcInvNo) {
        return new Result(invTaskDetailService.selectBySrcInvNo(srcInvNo));
    }

    /**
     * 根据srcInvNo 查询所有盘点数据
     *
     * @param srcInvNo
     * @return
     */
    @GetMapping(value = "/selectAllBySrcInvNo/{srcInvNo}")
    public Result selectAllByInvId(@PathVariable("srcInvNo") String srcInvNo) {
        return new Result(invTaskDetailService.selectAllBySrcInvNo(srcInvNo));
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<InvTaskResultDetailDTO> records = invTaskDetailService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新盘点数量 (Sn 和普通盘点)
     *
     * @param invTaskResultDetailDTO
     * @return
     */
    @PutMapping(value = "/updateActualQty")
    public Result updateActualInventoryData(@RequestBody InvTaskResultDetailDTO invTaskResultDetailDTO) {

        return new Result(invTaskDetailService.updateActualInvData(invTaskResultDetailDTO));
    }

    /**
     * 工作台执行盘点任务
     */
    @PutMapping(value = "/InvStart")
    public Result actuallyStartInv(@RequestBody InvTaskStartDto invTaskStartDto) {

        return new Result(invTaskDetailService.startInvTask(invTaskStartDto));
    }
}
