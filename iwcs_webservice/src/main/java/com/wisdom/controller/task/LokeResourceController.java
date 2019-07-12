package com.wisdom.controller.task;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 对LokeResourceController的操作
 *
 * @author baoxun
 * @date 2019-07-10
 */
@RestController
@RequestMapping("/api/lock_resource")
public class LokeResourceController {
    @Autowired
    LokeResourceService LokeResourceService;
    /**
     * 批量更新地图资源锁记录
     *
     *
     * @param "List<BaseMapBerthDTO>"{@link BaseMapBerthDTO}
     *
     * @return {@link Result }
     */
    @PutMapping("/updateByPrimaryKey")
    public Result updateByPrimaryKey(@RequestBody List<BaseMapBerthDTO> BaseMapBerthDTOList){
        //System.out.println("hhhhh!!!:"+ BaseMapBerthDTOList.size());
        LokeResourceService.updateBatchLock(BaseMapBerthDTOList);
        return new Result();
    }
}
