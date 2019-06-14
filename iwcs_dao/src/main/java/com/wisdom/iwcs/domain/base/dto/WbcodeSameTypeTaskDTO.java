package com.wisdom.iwcs.domain.base.dto;

import com.wisdom.iwcs.domain.task.WbAgvTask;

/**
 * @Author: cecilia.yang
 * @Date: 2019/3/19 18:07
 */
public class WbcodeSameTypeTaskDTO {

    private boolean haveSameTypeTask;

    private WbAgvTask wbAgvTask;

    @Override
    public String toString() {
        return "WbcodeSameTypeTaskDTO{" +
                "haveSameTypeTask=" + haveSameTypeTask +
                ", wbAgvTask=" + wbAgvTask +
                '}';
    }

    public boolean isHaveSameTypeTask() {
        return haveSameTypeTask;
    }

    public void setHaveSameTypeTask(boolean haveSameTypeTask) {
        this.haveSameTypeTask = haveSameTypeTask;
    }

    public WbAgvTask getWbAgvTask() {
        return wbAgvTask;
    }

    public void setWbAgvTask(WbAgvTask wbAgvTask) {
        this.wbAgvTask = wbAgvTask;
    }
}
