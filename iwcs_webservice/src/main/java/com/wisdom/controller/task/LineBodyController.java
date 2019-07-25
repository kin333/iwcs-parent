package com.wisdom.controller.task;

import java.util.List;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.linebody.dto.LineBodyDTO;
import com.wisdom.iwcs.mapstruct.task.LineBodyMapStruct;
import com.wisdom.iwcs.service.linebody.impl.LineBodyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 对LineBody的操作
 *
 *
 * @version        Enter version here..., 17/10/11
 * @author         Enter your name here...
 */
@RestController
@RequestMapping("/api/line_body")
public class LineBodyController {
    @Autowired
    LineBodyService lineBodyService;
    @Autowired
    LineBodyMapStruct lineBodyMapStruct;

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
        lineBodyService.deleteByPrimaryKey(id);

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
        lineBodyService.deleteMore(ids);

        return new Result();
    }

    /**
     * 新增记录
     *
     *
     * @param lineBodyDTO {@link LineBodyDTO }
     *
     * @return {@link Result }
     */
    @PostMapping
    public Result insert(@RequestBody LineBodyDTO lineBodyDTO) {
        lineBodyService.insert(lineBodyDTO);

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
        LineBodyDTO lineBodyDTO = lineBodyService.selectByPrimaryKey(id);

        return new Result(lineBodyDTO);
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
        GridReturnData<LineBodyDTO> records = lineBodyService.selectPage(gridPageRequest);

        return new Result(records);
    }

    /**
     * 更新记录
     *
     *
     * @param lineBodyDTO {@link LineBodyDTO }
     *
     * @return {@link Result }
     */
    @PutMapping
    public Result updateByPrimaryKey(@RequestBody LineBodyDTO lineBodyDTO) {
        lineBodyService.updateByPrimaryKey(lineBodyDTO);

        return new Result();
    }
}
