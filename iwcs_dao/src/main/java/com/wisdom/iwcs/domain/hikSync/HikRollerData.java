package com.wisdom.iwcs.domain.hikSync;

import lombok.Getter;
import lombok.Setter;

/**
 * Hik发送滚筒滚动信息时的data里的信息
 * @author han
 */
@Getter
@Setter
public class HikRollerData {
    /**
     * 发送空箱数量
     */
    private String sendNull;
    /**
     * 接收空箱数量
     */
    private String rcvNull;
    /**
     * 发送满箱数量
     */
    private String sendFull;
    /**
     * 接收满箱数量
     */
    private String rcvFull;

    public HikRollerData() {
        this.sendNull = "";
        this.rcvNull = "";
        this.sendFull = "";
        this.rcvFull = "";
    }
}

