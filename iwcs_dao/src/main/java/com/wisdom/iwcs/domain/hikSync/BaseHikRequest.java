package com.wisdom.iwcs.domain.hikSync;

import lombok.Getter;
import lombok.Setter;

/**
 * 海康请求的基础类
 * @author han
 *
 */
@Getter
@Setter
public class BaseHikRequest {
    /**
     * 请求编号，每个请求都要一个唯一编号， 同一个请求重复提交， 使用同一编号
     */
    private String reqCode;

    /**
     * 请求时间戳 格式: “yyyy-MM-dd HH:mm:ss”
     */
    private String reqTime;

    /**
     * 客户端编号，如PDA，HCWMS等
     */
    private String clientCode;

    /**
     * 令牌号, 由调度系统颁发
     */
    private String tokenCode;
}
