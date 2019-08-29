package com.wisdom.iwcs.service.task.conditions.point;

import com.alibaba.fastjson.JSONArray;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 静态站点集合策略实现类
 * @author han
 */
@Component
public class StaticViaPathsStrategic implements IGetPointStrategic {
    private static final Logger logger = LoggerFactory.getLogger(StaticViaPathsStrategic.class);
    @Autowired
    MainTaskMapper mainTaskMapper;
    @Override
    public String getPoint(AutoCreateBaseInfo autoCreateBaseInfo) {
        logger.info("主任务{}开始进行静态站点集合策略", autoCreateBaseInfo.getMainTaskNum());
        //获取主任务的站点集合
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(autoCreateBaseInfo.getMainTaskNum());
        String staticViaPaths = mainTask.getStaticViaPaths();
        List<String> points = JSONArray.parseArray(staticViaPaths, String.class);
        int index = Integer.valueOf(autoCreateBaseInfo.getValue()) - 1;
        if (index > points.size()) {
            throw new BusinessException("站点集合数值超长");
        }
        logger.info("主任务{}静态站点集合策略获取完成,返回点位值为{}", autoCreateBaseInfo.getMainTaskNum(), points.get(index));
        //返回需要的指定站点
        return points.get(index);
    }
}
