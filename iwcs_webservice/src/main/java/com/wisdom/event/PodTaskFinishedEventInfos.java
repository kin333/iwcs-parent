package com.wisdom.event;

import java.util.Date;

/**
 * 货架结束事件参数
 *
 * @author ted
 * @create 2019-03-06 上午9:53
 **/
public class PodTaskFinishedEventInfos {
    private String podCode;
    private String remark;
    private Date happendedTime;

    public String getPodCode() {
        return podCode;
    }

    public void setPodCode(String podCode) {
        this.podCode = podCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getHappendedTime() {
        return happendedTime;
    }

    public void setHappendedTime(Date happendedTime) {
        this.happendedTime = happendedTime;
    }
}
