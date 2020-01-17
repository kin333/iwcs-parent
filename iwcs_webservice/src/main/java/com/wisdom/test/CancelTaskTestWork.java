package com.wisdom.test;

import com.alibaba.fastjson.JSONArray;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.base.dto.BasePodAndMapDTO;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.SubTask;
import com.wisdom.iwcs.domain.upstream.mes.MesCancelTaskRequest;
import com.wisdom.iwcs.domain.upstream.mes.MesResult;
import com.wisdom.iwcs.mapper.task.SubTaskMapper;
import com.wisdom.iwcs.service.base.baseImpl.BasePodAndMapService;
import com.wisdom.iwcs.service.task.impl.MainTaskService;
import com.wisdom.iwcs.service.task.impl.MesRequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 取消任务自动化测试
 * @author han
 */
@Component
public class CancelTaskTestWork extends BaseAutoTestWorker {
    private final Logger logger = LoggerFactory.getLogger(CancelTaskTestWork.class);
    @Autowired
    MainTaskService mainTaskService;
    @Autowired
    MesRequestService mesRequestService;
    @Autowired
    BasePodAndMapService basePodAndMapService;
    @Autowired
    SubTaskMapper subTaskMapper;

    /**
     * 概率,任务被取消的概率为  1/PROB
     */
    private static int PROB = 10;
    @Override
    void createTask() {
        logger.info("取消任务自动化测试开始");
        Random random = new Random();
        //查询所有主任务
        List<MainTask> allUnDispatchedTask = mainTaskService.getAllUnDispatchedTask();
        if (allUnDispatchedTask.size() <= 0) {
            return;
        }
        for (MainTask mainTask : allUnDispatchedTask) {
            if (random.nextInt(PROB) == 0) {
                logger.info("自动化任务开始取消任务:{}", mainTask.getMainTaskNum());
                MesCancelTaskRequest mesCancelTaskRequest = new MesCancelTaskRequest();
                mesCancelTaskRequest.setTaskCode(mainTask.getMainTaskNum());
                //任务被取消的概率为1/PROB
                MesResult mesResult = mesRequestService.publicCancelTask(mesCancelTaskRequest);
                //如果需要绑定货架
                if (mesResult.getData() != null) {
                    bindPod(mesResult, mainTask);
                }
            }
        }
    }

    /**
     * 绑定货架
     * @param mesResult
     * @param mainTask
     */
    private void bindPod(MesResult mesResult, MainTask mainTask) {
        List<String> pods = (ArrayList<String>)mesResult.getData();
        //目前自动化测试仅支持绑定一个货架
        if (pods.size() == 1) {
            logger.info("任务{}的货架{}需要绑定", mainTask.getMainTaskNum(), pods.get(0));
            List<SubTask> subTasks = subTaskMapper.selectByMainTaskNumAndPodCode(mainTask.getMainTaskNum(), pods.get(0));
            String point = "";
            if (subTasks.size() == 0) {
                String staticViaPaths = mainTask.getStaticViaPaths();
                List<String> points = JSONArray.parseArray(staticViaPaths, String.class);
                point = points.get(0);
            } else {
                point = subTasks.get(subTasks.size() - 1).getStartAlias();
            }
            BasePodAndMapDTO basePodAndMapDTO = new BasePodAndMapDTO();
            basePodAndMapDTO.setPodCode(pods.get(0));
            basePodAndMapDTO.setPoint(point);
            for (int i = 0; i < 10; i++) {
                try {
                    basePodAndMapService.bind(basePodAndMapDTO);
                    break;
                } catch (BusinessException e) {
                    logger.error("任务{}绑定异常,重试{}:", mainTask.getMainTaskNum(), i + 1, e);
                }
            }
            logger.info("任务{}的货架{}被绑定到点位{}上", mainTask.getMainTaskNum(), pods.get(0), point);
        } else {
            logger.error("需要恢复的货架数量超出自动化测试支持范围!{}", pods.toString());
        }
    }
}
