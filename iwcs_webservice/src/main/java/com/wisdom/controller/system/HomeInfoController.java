package com.wisdom.controller.system;


import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.system.HomeInfo;
import com.wisdom.iwcs.domain.system.ViewData;
import com.wisdom.iwcs.service.system.HomeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 首页信息 接口
 */
@RestController
@RequestMapping("/api/home")
public class HomeInfoController {
    @Autowired
    HomeInfoService homeInfoService;

    @PostMapping("/count")
    public Result getHomeShowDate(){
        HomeInfo homeInfo = homeInfoService.getHome();
        return new Result(homeInfo);
    }

    @PostMapping("/subViewData")
    public Result getSubViewData(){
        Map<String, List<ViewData>> viewData = homeInfoService.getViewData();
        return new Result(viewData);
    }
}
