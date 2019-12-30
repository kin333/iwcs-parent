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


    /**
     * 解绑
     * @param basePodAndMapDTO
     * @return
     */
    @PostMapping("/untying")
    public Result untying(@RequestBody BasePodAndMapDTO basePodAndMapDTO) {
        basePodAndMapService.untying(basePodAndMapDTO);
        return new Result();
    }

    /**
     * 绑定
     * @param basePodAndMapDTO
     * @return
     */
    @PostMapping("/bind")
    public Result bind(@RequestBody BasePodAndMapDTO basePodAndMapDTO) {
        basePodAndMapService.bind(basePodAndMapDTO);
        return new Result();
    }


}
