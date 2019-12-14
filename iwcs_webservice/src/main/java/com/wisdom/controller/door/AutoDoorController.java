package com.wisdom.controller.door;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.door.AutoDoor;
import com.wisdom.iwcs.domain.door.dto.AutoDoorDTO;
import com.wisdom.iwcs.mapstruct.door.AutoDoorMapStruct;
import com.wisdom.iwcs.service.door.impl.AutoDoorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 对AutoDoor的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/auto_door")
public class AutoDoorController {
    @Autowired
    AutoDoorService autoDoorService;
    @Autowired
    AutoDoorMapStruct autoDoorMapStruct;

    /**
     * 根据主键ID删除
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link Result }
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteByPrimaryKey(@PathVariable Integer id) {
        autoDoorService.deleteByPrimaryKey(id);

        return new Result();
    }

    /**
     * 根据主键ID删除多条记录
     *
     *
     * @param ids {@link List<String> }
     *
     * @return {@link Result }
     */
    @DeleteMapping
    public Result deleteMoreByIds(@RequestBody List<String> ids) {
        autoDoorService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param autoDoorDTO {@link AutoDoorDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody AutoDoorDTO autoDoorDTO) {
        autoDoorService.insert(autoDoorDTO);

        return new Result();
    }

    /**
     * 根据主键查询记录
     *
     *
     * @param id {@link Integer }
     *
     * @return {@link Result }
     */
    @GetMapping(value = "/{id}")
    public Result selectByPrimaryKey(@PathVariable Integer id) {
        AutoDoorDTO autoDoorDTO = autoDoorService.selectByPrimaryKey(id);

        return new Result(autoDoorDTO);
    }

    /**
     * 分页查询记录
     *
     *
     * @param gridPageRequest {@link GridPageRequest }
     *
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<AutoDoorDTO> records = autoDoorService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param autoDoorDTO {@link AutoDoorDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody AutoDoorDTO autoDoorDTO) {
        autoDoorService.updateByPrimaryKey(autoDoorDTO);

        return new Result();
    }

    @PostMapping("/getNumBy")
    public Result getNormalNum(@RequestBody AutoDoorDTO autoDoorDTO) {
        List<AutoDoorDTO> autoDoorDTOList = autoDoorService.getNormalNum(autoDoorDTO);

        return new Result(autoDoorDTOList);
    }
    @PostMapping("/getDataByCode")
    public Result selectDataByCode(@RequestBody AutoDoorDTO autoDoorDTO) {
        AutoDoorDTO doorDTOList = autoDoorService.selectDataByCode(autoDoorDTO);

        return new Result(doorDTOList);
    }
    @PostMapping("/updateDoorModel")
    public Result updateDoorModel(@RequestBody AutoDoorDTO autoDoor) {

        autoDoorService.updateDoorModel(autoDoor);

        return new Result();
    }
}
