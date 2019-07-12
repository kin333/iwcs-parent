package com.wisdom.controller.task;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BasePodDetailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * 对BasePodDetailLockSourceServiceController的操作
 *
 * @author baoxun
 * @date 2019-07-12
 */
@RestController
@RequestMapping("/api/basepoddetail_lock_resource")
public class BasePodDetailLockSourceServiceController {

    @Autowired
    LokeResourceService LokeResourceService;
    /**
     * 批量更新货架资源锁记录
     *
     * @param "List<BasePodDetailDTO>"{@link BasePodDetailDTO}
     * @author baoxun
     * @date 2019-07-11
     * @return {@link Result }
     */
     @PutMapping("/updateLockSourceByPrimaryKey")
     public Result updateLockSourceByPrimaryKey(@RequestBody List<BasePodDetailDTO> BasePodDetailDTOList){
         LokeResourceService.updatePodBatchLock(BasePodDetailDTOList);
         return new Result();
    }
}
