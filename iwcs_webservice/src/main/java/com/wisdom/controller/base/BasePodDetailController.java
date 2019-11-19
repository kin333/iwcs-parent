package com.wisdom.controller.base;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMap;
import com.wisdom.iwcs.domain.base.BasePod;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.BasePodDetailDTO;
import com.wisdom.iwcs.mapstruct.base.BasePodDetailMapStruct;
import com.wisdom.iwcs.service.base.IBasePodDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.SAVE_INSTOCK;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.SAVE_INSTOCK_DESC;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_PDA;

/**
 * 对BasePodDetail的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-pod-detail")
public class BasePodDetailController {
    @Autowired
    IBasePodDetailService IBasePodDetailService;
    @Autowired
    BasePodDetailMapStruct basePodDetailMapStruct;
    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBasePodDetailService.deleteByPrimaryKey(id);

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
        IBasePodDetailService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param basePodDetailDTO {@link BasePodDetailDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BasePodDetailDTO basePodDetailDTO) {
        IBasePodDetailService.insert(basePodDetailDTO);

        return new Result();
    }
    /**
     * 根据锁状态 返回货架号
     * @return
     */
    @GetMapping(value = "/selectByInLock")
    public Result selectByInLock() {
        List<BasePodDetailDTO> basePodDetail = IBasePodDetailService.selectByInLock();

        return new Result(basePodDetail);
    }
    /**
     * 根据主键查询记录
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @GetMapping(value = "/{id}")
    public Result selectByPrimaryKey(@PathVariable Integer id) {
        BasePodDetailDTO basePodDetailDTO = IBasePodDetailService.selectByPrimaryKey(id);

        return new Result(basePodDetailDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BasePodDetailDTO> records = IBasePodDetailService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param basePodDetailDTO {@link BasePodDetailDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BasePodDetailDTO basePodDetailDTO) {
        IBasePodDetailService.updateByPrimaryKey(basePodDetailDTO);

        return new Result();
    }

    /**
     * 更新货架空满
     * @param
     * @return
     */
    @PostMapping(value = "/saveInStock")
    @SystemInterfaceLog(methodCode = SAVE_INSTOCK, methodName = SAVE_INSTOCK_DESC, methodThansfer = SRC_PDA)
    public Result savePodInStock(@RequestBody BasePodDetailDTO basePodDetailDTO) {
        IBasePodDetailService.savePodInStock(basePodDetailDTO);
        return new Result();
    }

    @PostMapping("/getPodByPodCode")
    public Result selectPodByPodCode(@RequestBody BasePodDetailDTO basePodDetailDTO) {

        BasePodDetail basePodDetail = IBasePodDetailService.selectPodByPodCode(basePodDetailDTO);

        return new Result(basePodDetail);
    }

    @PostMapping("/getPodData")

    public Result selectPodData(@RequestBody BasePodDetailDTO basePodDetailDTO) {

        BasePodDetail basePodDetail = IBasePodDetailService.selectPodData(basePodDetailDTO);

        return new Result(basePodDetail);
    }
}
