package com.wisdom.iwcs.domain.inv.dto;

import com.wisdom.iwcs.domain.inv.InvTask;
import lombok.Data;

import java.util.Date;

@Data
public class InvTaskSearchDto extends InvTask {

    private Date creatStartTime;

    private Date creatEndTime;

    public Date getCreatStartTime() {
        return creatStartTime;
    }

    public void setCreatStartTime(Date creatStartTime) {
        this.creatStartTime = creatStartTime;
    }

    public Date getCreatEndTime() {
        return creatEndTime;
    }

    public void setCreatEndTime(Date creatEndTime) {
        this.creatEndTime = creatEndTime;
    }
}
