package com.wisdom.controller;

import com.wisdom.iwcs.common.utils.Result;
import com.wisdom.iwcs.domain.base.BaseMapBerth;
import com.wisdom.iwcs.domain.base.BasePodDetail;
import com.wisdom.iwcs.domain.base.dto.BaseMapBerthDTO;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.mapper.base.BaseMapBerthMapper;
import com.wisdom.iwcs.mapper.base.BasePodDetailMapper;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import com.wisdom.iwcs.service.task.impl.MainTaskService;
import com.wisdom.iwcs.service.task.maintask.MainTaskWorker;
import com.wisdom.iwcs.service.task.scheduler.WcsTaskScheduler;
import com.wisdom.iwcs.service.task.template.IwcsPublicService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    @Autowired
    MainTaskService mainTaskService;


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
        //需要更新的地图信息
        List<BaseMapBerthDTO> updateMapInfo = new ArrayList<>();
        for (BaseMapBerth baseMapBerth : baseMapBerths) {
            if (StringUtils.isNotBlank(baseMapBerth.getMapCode())) {
                try {
                    //调用海康接口 查找地码对应的货架号
                    List<String> podCodes = iwcsPublicService.selectPodCodeByBerCode(baseMapBerth.getBerCode());
                    if (podCodes == null || podCodes.size() <= 0) {
                        continue;
                    }
                    if (!podCodes.get(0).equals(baseMapBerth.getPodCode())) {
                        //如果数据库数据与海康不一致,则提示
                        System.out.println("更新地图数据:" + baseMapBerth.getBerCode()
                                    + "  本地数据货架为:" + baseMapBerth.getPodCode()
                                    + "  海康数据库货架为:" + podCodes.get(0));
                    }
                    BaseMapBerthDTO baseMapBerthDTO = new BaseMapBerthDTO();
                    baseMapBerthDTO.setPodCode(podCodes.get(0));
                    baseMapBerthDTO.setBerCode(baseMapBerth.getBerCode());
                    updateMapInfo.add(baseMapBerthDTO);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }
        if (updateMapInfo.size() <= 0) {
            return new Result(400, "地图没有更新任何数据");
        }
        //清空地图中的货架号数据
        baseMapBerthMapper.updateAllCleanPodCode();
        //更新
        baseMapBerthMapper.updateListByBerCode(updateMapInfo);

        return new Result();
    }
    /**
     * 根据海康的信息更新我们数据库货架对应地图的所有的信息
     * 货架号  -->  地码
     * @return
     */
    @GetMapping("/updateAllPodInfo")
    public Result updateAllPodInfo() {
        //1.获取数据库中所有的货架信息
        List<BasePodDetail> basePodDetails = basePodDetailMapper.selectAll();
        List<BasePodDetail> updatePodDetails = new ArrayList<>();
        for (BasePodDetail basePodDetail : basePodDetails) {
            List<String> berCodes = null;
            try {
                //2.调用海康接口 查找货架号对应的地码
                berCodes = iwcsPublicService.selectBerCodeByPodCode(basePodDetail.getPodCode());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (berCodes == null || berCodes.size() <= 0) {
                continue;
            }
            //3.拼接地图其他信息
            String berCode = berCodes.get(0);
            String coox = berCode.substring(0, 6);
            String cooy = berCode.substring(berCode.length() - 6);
            String mapCode = berCode.substring(6, 8);
            while (true) {
                if ("0".equals(coox.substring(0, 1))) {
                    coox = coox.substring(1);
                } else {
                    break;
                }
            }
            //提示更新的数据
            if (!berCode.equals(basePodDetail.getBerCode())) {
                //如果数据库数据与海康不一致,则提示
                System.out.println("更新货架数据:" + basePodDetail.getPodCode()
                        + "  本地数据地码为:" + basePodDetail.getBerCode()
                        + "  海康数据库货架为:" + berCode);
            }
            //4.合成数据集合
            BasePodDetail newBasePodDetail = new BasePodDetail();
            newBasePodDetail.setPodCode(basePodDetail.getPodCode());
            newBasePodDetail.setCoox(coox);
            newBasePodDetail.setCooy(cooy);
            newBasePodDetail.setMapCode(mapCode);
            newBasePodDetail.setBerCode(berCode);
            updatePodDetails.add(newBasePodDetail);
        }
        //5.清库,更新
        basePodDetailMapper.updateCleanMapInfo();
        basePodDetailMapper.updateMapByPodCode(updatePodDetails);


        return new Result();
    }

    /**
     * 根据海康的信息更新我们数据库货架和地图信息
     * @return
     */
    @GetMapping("/updateAllInfo")
    public Result updateAllInfo() {
        updateAllPodInfo();
        updateAllMapInfo();
        return new Result();
    }

    @GetMapping("/testMainTask")
    public void testMainTask(){
        mainTaskService.loopMaintTask("M196550067523584");
    }



}
