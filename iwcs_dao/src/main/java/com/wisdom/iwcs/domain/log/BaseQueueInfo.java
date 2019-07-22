package com.wisdom.iwcs.domain.log;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 基础的队列信息传输类
 * @author han
 */
@Setter
@Getter
public class BaseQueueInfo {
    private String subTaskNum;

    private Date createTime;
}
