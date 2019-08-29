package com.wisdom.iwcs.service.task.conditions.pod;

import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 主任务单获取
 * @author han
 */
@Component
public class MainTaskRelatedStrategic implements IGetPodStrategic{
    private static final Logger logger = LoggerFactory.getLogger(MainTaskRelatedStrategic.class);
    @Autowired
    MainTaskMapper mainTaskMapper;

    @Override
    public String getPod(AutoCreateBaseInfo autoCreateBaseInfo) {
        String mainTaskNum = autoCreateBaseInfo.getMainTaskNum();
        logger.info("主任务{}开始进行主任务单获取策略", mainTaskNum);
        //查询主任务单
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(mainTaskNum);
        String staticPodCode = mainTask.getStaticPodCode();

        logger.info("主任务{}主任务单获取策略获取完成,返回值为{}", mainTaskNum, staticPodCode);
        return staticPodCode;
    }
}
