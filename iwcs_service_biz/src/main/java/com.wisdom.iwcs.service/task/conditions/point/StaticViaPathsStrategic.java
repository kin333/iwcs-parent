package com.wisdom.iwcs.service.task.conditions.point;

import com.alibaba.fastjson.JSONArray;
import com.wisdom.iwcs.common.utils.exception.BusinessException;
import com.wisdom.iwcs.domain.task.MainTask;
import com.wisdom.iwcs.domain.task.dto.AutoCreateBaseInfo;
import com.wisdom.iwcs.mapper.task.MainTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 静态站点集合策略实现类
 * @author han
 */
@Component
public class StaticViaPathsStrategic implements IGetPointStrategic {
    @Autowired
    MainTaskMapper mainTaskMapper;
    @Override
    public String getPoint(AutoCreateBaseInfo autoCreateBaseInfo) {
        //获取主任务的站点集合
        MainTask mainTask = mainTaskMapper.selectByMainTaskNum(autoCreateBaseInfo.getMainTaskNum());
        String staticViaPaths = mainTask.getStaticViaPaths();
        List<String> points = JSONArray.parseArray(staticViaPaths, String.class);
        int index = Integer.valueOf(autoCreateBaseInfo.getValue()) - 1;
        if (index > points.size()) {
            throw new BusinessException("站点集合数值超长");
        }
        //返回需要的指定站点
        return points.get(index);
    }
}
