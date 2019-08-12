package com.wisdom.controller.version;

import com.wisdom.iwcs.common.utils.GridPageRequest;
import com.wisdom.iwcs.common.utils.GridReturnData;
import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.version.VersionDto;

import com.wisdom.iwcs.service.version.VersionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/*import com.wisdom.iwcs.service.version.FilePathSevice;*/


@RestController
@RequestMapping("/api/version")
public class VersionController {
    @Autowired
private VersionService versionService;

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
    public Result upload(@RequestParam("version") Integer version, @RequestParam("information") String information, @RequestParam("file") MultipartFile file) throws FileNotFoundException {
        return versionService.Upload(version,information,file);
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
}
