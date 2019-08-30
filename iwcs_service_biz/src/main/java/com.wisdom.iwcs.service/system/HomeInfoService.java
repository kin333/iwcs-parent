package com.wisdom.iwcs.service.system;

import com.wisdom.iwcs.domain.system.HomeInfo;
import com.wisdom.iwcs.domain.system.ViewData;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.View;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HomeInfoService {
    private final Logger logger = LoggerFactory.getLogger(HomeInfoService.class);

    @Autowired
    private BasePodDetailMapper basePodDetailMapper;
    @Autowired
    private SubTaskMapper subTaskMapper;
    @Autowired
    private MainTaskMapper mainTaskMapper;

    public HomeInfo getHome(){
        HomeInfo homeInfo = new HomeInfo();
        Integer podCount = basePodDetailMapper.selectPodCount();
        // unStart subTask
        Integer unStartSubTaskCount = subTaskMapper.selectUnStartSubTaskCount();
        // start sub task
        Integer startSubTaskCount = subTaskMapper.selectStartSubTaskCount();
        // end sub task
        Integer endSubTaskCount = subTaskMapper.selectEndSubTaskCount();
        // unStart main task
        Integer unStartMainTaskCount = mainTaskMapper.selectUnStartMainTaskCount();
        // start main task
        Integer startMainTaskCount = mainTaskMapper.selectStartMainTaskCount();
        // end main task
        Integer endMainTaskCount = mainTaskMapper.selectEndMainTaskCount();
        // unLock pod count
        Integer unLockPodCount = basePodDetailMapper.selectUnLockPodCount();
        // no have good
        Integer unStockCount = basePodDetailMapper.selectUnStockCount();


        homeInfo.setUnStartSubTaskCount(unStartSubTaskCount);
        homeInfo.setStartSubTaskCount(startSubTaskCount);
        homeInfo.setEndSubTaskCount(endSubTaskCount);

        homeInfo.setUnStartMainTaskCount(unStartMainTaskCount);
        homeInfo.setStartMainTaskCount(startMainTaskCount);
        homeInfo.setEndMainTaskCount(endMainTaskCount);

        homeInfo.setUninitializedPod(podCount);
        homeInfo.setUnStock(unStockCount);
        homeInfo.setUnLockPod(unLockPodCount);
        return homeInfo;
    }
    // 获取每一小时时间段子任务数量
    public Map<String, List<ViewData>> getViewData() {
        Map<String, List<ViewData>> map = new HashMap<>();
        List<ViewData> subViewEndData = subTaskMapper.getViewData();
        List<ViewData> mainViewEndData = mainTaskMapper.getMainViewData();
        List<ViewData> subStartViewData = subTaskMapper.getSubStartViewData();
        List<ViewData> mainStartViewData = mainTaskMapper.getMainStartViewData();
        map.put("subViewEndData", subViewEndData);
        map.put("mainViewEndData", mainViewEndData);
        map.put("mainStartData", mainStartViewData);
        map.put("substartData", subStartViewData);
        return map;
    }
}
