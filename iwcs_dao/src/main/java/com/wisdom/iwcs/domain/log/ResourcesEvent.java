package com.wisdom.iwcs.domain.log;

import lombok.Getter;
import lombok.Setter;

/**
 * 资源类消息事件信息类
 */
@Getter
@Setter
public class ResourcesEvent extends BaseQueueInfo{
    /**
     * 地图编码
     */
    private String mapCode;
    /**
     * 作业区域
     */
    private String areaCode;
    /**
     * 事件类型(释放储位/释放货架)
     */
    private String resourcesType;
}
