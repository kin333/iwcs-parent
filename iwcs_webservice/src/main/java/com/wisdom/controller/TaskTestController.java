package com.wisdom.controller;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.service.task.maintask.MainTaskWorker;
import com.wisdom.iwcs.service.task.scheduler.WcsTaskScheduler;
import com.wisdom.iwcs.service.task.template.IwcsPublicService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Devin
 * @date 2018-05-15.
 */
@RestController
@RequestMapping("/api/test/wcsTask")
public class TaskTestController {

    @Autowired
    private MainTaskMapper mainTaskMapper;
    @Autowired
    IwcsPublicService iwcsPublicService;
    @Autowired
    BaseMapBerthMapper baseMapBerthMapper;
    @Autowired
    BasePodDetailMapper basePodDetailMapper;


    @GetMapping("/startWcsTaskScheduler")
    public Result startWcsTaskScheduler() {
        WcsTaskScheduler wcsTaskScheduler = new WcsTaskScheduler();
        wcsTaskScheduler.dispatchMaintask();
        return new Result();
    }


    @GetMapping("/startMainTask/{mainTaskId}")
    public Result startSubtask(@PathVariable Long mainTaskId) {
        MainTask mainTask = mainTaskMapper.selectByPrimaryKey(mainTaskId);
        if (mainTask != null) {
            MainTaskWorker mainTaskWorker = new MainTaskWorker(null, mainTask, null);
            Thread thread = new Thread(mainTaskWorker);
            thread.start();
            return new Result("任务已启动");
        } else {
            return new Result(99, "无效的主任务id");
        }
    }

    /**
     * 根据海康的信息更新我们数据库地图对应货架的所有的信息
     * 地码  -->  货架号
     * @return
     */
    @GetMapping("/updateAllMapInfo")
    public Result updateAllMapInfo() {
        List<BaseMapBerth> baseMapBerths = baseMapBerthMapper.selectAll();
        for (BaseMapBerth baseMapBerth : baseMapBerths) {
            if ("CC".equals(baseMapBerth.getMapCode()) || "AA".equals(baseMapBerth.getMapCode()) || "AB".equals(baseMapBerth.getMapCode())) {

                try {
                    //查找地码对应的货架号
                    List<String> podCodes = iwcsPublicService.selectPodCodeByBerCode(baseMapBerth.getBerCode());
                    if (podCodes == null || podCodes.size() <= 0) {
                        continue;
                    }
                    baseMapBerthMapper.updatePodCodeByBerCode(podCodes.get(0), baseMapBerth.getBerCode());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }

        return new Result();
    }
    /**
     * 根据海康的信息更新我们数据库货架对应地图的所有的信息
     * 货架号  -->  地码
     * @return
     */
    @GetMapping("/updateAllPodInfo")
    public Result updateAllPodInfo() {
        List<String> podCodes = basePodDetailMapper.selectInitPod();
        for (String podCode : podCodes) {
            List<String> berCodes = null;
            try {
                berCodes = iwcsPublicService.selectBerCodeByPodCode(podCode);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (berCodes == null || berCodes.size() <= 0) {
                continue;
            }
            String berCode = berCodes.get(0);
            String coox = berCode.substring(0, 6);
            String cooy = berCode.substring(berCode.length() - 6);
            String mapCode = berCode.substring(6, 8);
            BasePodDetail basePodDetail = new BasePodDetail();
            basePodDetail.setPodCode(podCode);
            basePodDetail.setCoox(coox);
            basePodDetail.setCooy(cooy);
            basePodDetail.setMapCode(mapCode);
            basePodDetail.setBerCode(berCode);
            while (true) {
                if ("0".equals(coox.substring(0, 1))) {
                    coox = coox.substring(1);
                } else {
                    break;
                }
            }
            basePodDetailMapper.updateMapByPodCode(basePodDetail);
            System.out.println(podCode + "----" + berCode);
        }


        return new Result();
    }



}
