package com.wisdom.test;

import com.wisdom.iwcs.commonDto.fliterConOptions.LayerConTypeEnum;
import com.wisdom.iwcs.commonDto.fliterCondition.PodFliterCondition;
import com.wisdom.iwcs.service.common.IPodCal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
