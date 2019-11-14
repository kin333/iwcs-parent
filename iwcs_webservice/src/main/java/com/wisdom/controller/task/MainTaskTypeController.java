package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.dto.MainTaskTypeAndAreaCode;
import com.wisdom.iwcs.domain.task.MainTaskType;
import com.wisdom.iwcs.domain.task.TaskModal;
import com.wisdom.iwcs.domain.task.dto.MainTaskTypeDTO;
import com.wisdom.iwcs.mapstruct.task.MainTaskTypeMapStruct;
import com.wisdom.iwcs.service.task.impl.MainTaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * 对MainTaskType的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/main_task_type")
public class MainTaskTypeController {
    @Autowired
    MainTaskTypeService mainTaskTypeService;
    @Autowired
    MainTaskTypeMapStruct mainTaskTypeMapStruct;

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
        mainTaskTypeService.deleteByPrimaryKey(id);

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
        mainTaskTypeService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param mainTaskTypeDTO {@link MainTaskTypeDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody MainTaskTypeDTO mainTaskTypeDTO) {
        int num = mainTaskTypeService.insert(mainTaskTypeDTO);

        if (num == 400) {
            return new Result(400,"该主任务类型编号：" + mainTaskTypeDTO.getMainTaskTypeCode() + "已存在！");
        }
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
        MainTaskTypeDTO mainTaskTypeDTO = mainTaskTypeService.selectByPrimaryKey(id);

        return new Result(mainTaskTypeDTO);
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
        GridReturnData<MainTaskTypeDTO> records = mainTaskTypeService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param mainTaskTypeDTO {@link MainTaskTypeDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody MainTaskTypeDTO mainTaskTypeDTO) {
        mainTaskTypeService.updateByPrimaryKey(mainTaskTypeDTO);

        return new Result();
    }

    /**
     * 查询所有任务
     * @param
     * @return
     */
    @GetMapping(value = "/getAllTaskType/{areaCode}")
    public Result selectAllTaskType(@PathVariable String areaCode) {
        List<MainTaskTypeAndAreaCode> mainTaskType = mainTaskTypeService.selectAllTaskType(areaCode);
        return new Result(mainTaskType);
    }

    /**
     * 根据主任务code删除
     */
    @PostMapping("/deleteMainTaskType")
    public Result deleteMainTaskType(@RequestBody TaskModal taskModal){
        mainTaskTypeService.deleteMainTaskType(taskModal);
        return new Result();
    }

    /**
     * 根据主任务类型号查询
     */
    @PostMapping("/getMainTypeByMainCode")
    public Result selectMainTypeByMainCode(@RequestBody MainTaskType mainCode) {

        MainTaskType mainTaskTypeDTO = mainTaskTypeService.selectMainTypeByMainCode(mainCode);
        return new Result(mainTaskTypeDTO);
    }
    /**
     * 查询所有主任务类型
     */
    @PostMapping("/getMainTypeAll")
    public Result selectAll() {

        List<MainTaskType> mainTaskTypeList = mainTaskTypeService.selectAll();

        return new Result(mainTaskTypeList);
    }
}
