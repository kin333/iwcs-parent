package com.wisdom.controller.inv;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.inv.InvTaskBincodeProcess;
import com.wisdom.iwcs.service.inv.invImpl.InvTaskBincodeProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 对invTaskBincodeProcess的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/invTaskBincodeProcess")
public class InvTaskBincodeProcessController {

    @Autowired
    InvTaskBincodeProcessService invTaskBincodeProcessService;

    /**
     * 获取盘点任务进度
     *
     * @return
     */
    @GetMapping("/selectInvTaskBincodeProcess/{srcInvNo}")
    public Result selectInvTaskBincodeProcess(@PathVariable String srcInvNo) {
        List<InvTaskBincodeProcess> list = invTaskBincodeProcessService.selectInvTaskBincodeProcess(srcInvNo);
        return new Result(list);
    }

}
