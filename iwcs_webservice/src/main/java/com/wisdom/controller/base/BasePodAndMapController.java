package com.wisdom.controller.base;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BasePodAndMapDTO;
import com.wisdom.iwcs.service.base.baseImpl.BasePodAndMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional(rollbackFor = Exception.class)
@RequestMapping("/api/wisdom/podDetailAndMapBerth")
public class BasePodAndMapController {
    @Autowired
    BasePodAndMapService basePodAndMapService;


    @PostMapping
    public Result updatePodDetailAndMapBerth(@RequestBody BasePodAndMapDTO basePodAndMapDTO) {
        basePodAndMapService.updatePodAndMap(basePodAndMapDTO);
        return new Result();
    }


}