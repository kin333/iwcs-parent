package com.wisdom.iwcs.domain.system;

import java.util.Date;

public class ViewData {

    // 任务时间段数量
    private Integer dateList;
    // 任务时间
    private Date hours;

    public Integer getDateList() {
        return dateList;
    }

    public void setDateList(Integer dateList) {
        this.dateList = dateList;
    }

    public Date getHours() {
        return hours;
    }

    public void setHours(Date hours) {
        this.hours = hours;
    }
}
