package com.wisdom.controller.version;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.version.VersionDto;

import com.wisdom.iwcs.service.task.impl.MessageService;
import com.wisdom.iwcs.service.version.VersionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.util.Locale;

/*import com.wisdom.iwcs.service.version.FilePathSevice;*/


@RestController
@RequestMapping("/api/version")
public class VersionController {
    @Autowired
    private VersionService versionService;
    @Autowired
    MessageService messageService;
    /**
     * 分页查询记录
     *
     * @param gridPageRequest {@link GridPageRequest }
     * @return {@link Result }
     */
    @PostMapping(value = "/page")
    public Result selectPage(@RequestBody GridPageRequest gridPageRequest) {
        GridReturnData<VersionDto> records = versionService.selectPage(gridPageRequest);
        return new Result(records);
    }


    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file, HttpServletRequest httpServletRequest) throws FileNotFoundException {
        String bathPath = httpServletRequest.getSession().getServletContext().getRealPath("/");
        return versionService.Upload(file, bathPath);
    }

    /**
     * 更新版本
     * @param
     * @return
     */
    @GetMapping(value = "/checkVersion/{version}")
    public Result upload(@PathVariable Integer version) {
        return versionService.versionupdate(version) ;
    }
    /**
     * 更换语言
     * @param
     * @return
     */
    @GetMapping(value = "/changeLang/{lang}")
    public Result changeLang(@PathVariable String lang) {
        Locale locale;
        if ("en_US".equals(lang) || "en-US".equals(lang)) {
            locale = Locale.US;
        } else if ("zh_CN".equals(lang) || "zh-CN".equals(lang)) {
            locale = Locale.CHINA;
        } else {
            locale = LocaleContextHolder.getLocale();
        }
        messageService.setLocale(locale);
        return new Result();
    }
}
