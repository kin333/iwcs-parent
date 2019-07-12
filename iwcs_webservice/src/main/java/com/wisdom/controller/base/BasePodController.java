package com.wisdom.controller.base;

import com.wisdom.base.annotation.SystemInterfaceLog;
import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.BasePodDTO;
import com.wisdom.iwcs.domain.base.dto.ShowPodInfoCondDTO;
import com.wisdom.iwcs.mapstruct.base.BasePodMapStruct;
import com.wisdom.iwcs.service.base.IBasePodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceCode.SHOW_POD_CODE;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.InterfaceName.SHOW_POD_NAME;
import static com.wisdom.iwcs.common.utils.InterfaceLogConstants.SrcClientCode.SRC_OUTER;

/**
 * 对BasePod的操作
 *
 * @author Enter your name here...
 * @version Enter version here..., 17/10/11
 */
@RestController
@RequestMapping("/api/base-pod")
public class BasePodController {
    @Autowired
    IBasePodService IBasePodService;
    @Autowired
    BasePodMapStruct basePodMapStruct;
    /**
     * 根据主键ID删除
     *
     * @param id {@link Integer }
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        IBasePodService.deleteByPrimaryKey(id);

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
        IBasePodService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     * @param basePodDTO {@link BasePodDTO }
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody BasePodDTO basePodDTO) {
        IBasePodService.insert(basePodDTO);

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
        BasePodDTO basePodDTO = IBasePodService.selectByPrimaryKey(id);

        return new Result(basePodDTO);
    }

    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<BasePodDTO> records = IBasePodService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     * @param basePodDTO {@link BasePodDTO }
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody BasePodDTO basePodDTO) {
        IBasePodService.updateByPrimaryKey(basePodDTO);

        return new Result();
    }

    /**
     * 工作台获取货架信息
     *
     * @param requestDTO
     * @return
     */
    @PostMapping(value = "/showPodInfo")
    @SystemInterfaceLog(methodCode = SHOW_POD_CODE, methodName = SHOW_POD_NAME, methodThansfer = SRC_OUTER)
    public Result showPodInfo(@RequestBody ShowPodInfoCondDTO requestDTO) {
        return IBasePodService.showPodInfo(requestDTO);
    }
}
