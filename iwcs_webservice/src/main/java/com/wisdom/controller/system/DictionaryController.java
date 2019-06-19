package com.wisdom.controller.system;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.system.Dictionary;
import com.wisdom.iwcs.service.system.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhanglisen on 2016/12/28.
 */
@RestController
@RequestMapping("/api/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 查询单个字典
     *
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/{id}/show", method = RequestMethod.GET)
    public Result selectByPrimaryKey(@PathVariable Integer id, HttpServletResponse response) {
        Result result = dictionaryService.selectByPrimaryKey(id);
        return result;
    }

    /**
     * 返回所有字典
     *
     * @param response
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result selectAll(HttpServletResponse response) {
        Result result = dictionaryService.selectAll();
        return result;
    }

    /**
     * 分页条件查询字典
     *
     * @param gridPageRequest
     * @return
     */
    @RequestMapping(value = "/{dictType}/pager", method = RequestMethod.POST)
    public Result selectAll(@RequestBody GridPageRequest gridPageRequest, @PathVariable String dictType) {
        Result result = dictionaryService.selectByDictTypePager(gridPageRequest, dictType);
        return result;
    }

    /**
     * 1. 分页条件查询 所有Type的字典
     *
     * @param gridPageRequest
     * @return
     */
    @RequestMapping(value = "/basetype/list", method = RequestMethod.POST)
    public Result selectBaseTypeList(@RequestBody GridPageRequest gridPageRequest) {
        Result result = dictionaryService.selectBaseTypeList(gridPageRequest);
        return result;
    }

    /**
     * 2. 某Type类型下的所有子字典
     *
     * @param dictType
     * @return
     */
    @RequestMapping(value = "/{dictType}/list", method = RequestMethod.GET)
    public Result getDicsByType(@PathVariable String dictType) {
        Result result = dictionaryService.selectByDictType(dictType);
        return result;
    }

    /**
     * 3. 添加字典/类型
     *
     * @param dictionary
     * @param response
     * @return
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public Result insert(@RequestBody Dictionary dictionary, HttpServletResponse response) {
        Result result = dictionaryService.insert(dictionary);
        return result;
    }

    /**
     * 4. 批量更新字典名称、code
     *
     * @param dictionary
     * @param response
     * @return
     */
    @RequestMapping(value = "/basetype/update", method = RequestMethod.PUT)
    public Result updateBaseDictionary(@RequestBody Dictionary dictionary, HttpServletResponse response) {
        Result result = dictionaryService.updateBaseDictionary(dictionary);
        return result;
    }

    /**
     * 5. 更新单个字典
     *
     * @param dictionary
     * @param response
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result updateByPrimaryKey(@RequestBody Dictionary dictionary, HttpServletResponse response) {
        Result result = dictionaryService.updateByPrimaryKeySelective(dictionary);
        return result;
    }

    /**
     * 6. 批量删除字典
     *
     * @param dictionary
     * @param response
     * @return
     */
    @RequestMapping(value = "/basetype/delete", method = RequestMethod.PUT)
    public Result deleteBaseDictionary(@RequestBody Dictionary dictionary, HttpServletResponse response) {
        Result result = dictionaryService.deleteBaseDictionary(dictionary);
        return result;
    }

    /**
     * 7. 删除单个字典
     *
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public Result deleteByPrimaryKey(@PathVariable Integer id, HttpServletResponse response) {
        Result result = dictionaryService.deleteByPrimaryKey(id);
        return result;
    }
}
