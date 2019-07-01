package com.wisdom.test;

import com.rabbitmq.client.*;
import com.wisdom.config.RabbitConfig;
import com.wisdom.iwcs.common.utils.RabbitMQUtil;
import com.wisdom.iwcs.commonDto.fliterConOptions.LayerConTypeEnum;
import com.wisdom.iwcs.commonDto.fliterCondition.PodFliterCondition;
import com.wisdom.iwcs.service.common.IPodCal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * 货架计算测试
 *
 * @author ted
 * @create 2019-03-13 上午10:19
 **/

@RestController
@RequestMapping("api/test/podCal")
public class PodCalTestController {

    @Resource
    private IPodCal podCal;

    @PostMapping("/calPodByPodFliter")
    public List<String> calPodByConTest(@RequestBody PodFliterCondition podFliterCondition) {
        List<String> pods = podCal.calPodByPodFliterCondition(podFliterCondition);

        return pods;
    }

    @PostMapping("/calPodByPodFliter/testEnum")
    public void calPodByConTest(@RequestParam LayerConTypeEnum layerConTypeEnum) {
        System.out.println(layerConTypeEnum);
    }

}
