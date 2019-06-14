package com.wisdom.service.instock;

import com.wisdom.iwcs.common.utils.Result;

public interface ICycleCallPodService {

    /**
     * 循环呼叫货架
     *
     * @param wbCode,areaCode
     * @return
     */
    Result cycleCallPods(String wbCode, String areaCode, String callType);
}
