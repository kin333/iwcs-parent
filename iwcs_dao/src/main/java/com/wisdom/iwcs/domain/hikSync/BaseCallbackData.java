package com.wisdom.iwcs.domain.hikSync;

import lombok.Getter;
import lombok.Setter;

/**
 * agvCallback 回调中的data的基本类型
 * @author han
 */
@Getter
@Setter
public class BaseCallbackData {

    /**
     * 任务的执行时间
     */
    private String time;
    /**
     * 任务类型
     */
    private String iwcsBizType;
}
