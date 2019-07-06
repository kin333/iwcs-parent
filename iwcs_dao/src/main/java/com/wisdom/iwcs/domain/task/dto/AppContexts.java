package com.wisdom.iwcs.domain.task.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * iwcs 上下文类
 * @authot han
 */
@Getter
@Setter
public class AppContexts {
    /**
     * 请求编号
     */
    private String reqCode;
    /**
     * 请求时间戳，格式: “yyyy-MM-dd HH:mm:ss”
     */
    private String reqTime;
    /**
     * 客户端编号
     */
    private String clientCode;
    /**
     * 令牌号
     */
    private String tokenCode;
}
