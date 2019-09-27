package com.wisdom.controller.test;

import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 模拟MES的Controller
 * @author han
 */
@RestController
@RequestMapping
public class SimulationMesController {

    @PostMapping(value = {"/api/wisdom/autoProductionLine/supplyAndRecyle/agvProcessNotify",
            "/api/wisdom/autoProductionLine/emptyRecyleTask /agvProcessNotify"})
    public MesResultInfo agvProcessNotify(@RequestBody MesAgvChangeRequest mesAgvChangeRequest) {
        MesResultInfo mesResultInfo = new MesResultInfo();
        if ("2".equals(mesAgvChangeRequest.getTaskSta())) {
            mesResultInfo.setCode(MesResult.NG);
//            mesResultInfo.setNum("2");
        } else if("7".equals(mesAgvChangeRequest.getTaskSta())) {
            mesResultInfo.setCode(MesResult.NG);
//            mesResultInfo.setEmptyRecyleWb("214");
//            mesResultInfo.setRecyleCount("2");
        }

        return mesResultInfo;
    }
}

@Getter
@Setter
class MesResultInfo extends MesResult{
    /**
     * 到达供料点的供料数量
     */
    private String num;
    /**
     * 回收点
     */
    private String emptyRecyleWb;
    /**
     * 回收空料框数量
     */
    private String recyleCount;
}

@Getter
@Setter
class MesAgvChangeRequest {
    private String taskSta;
}
